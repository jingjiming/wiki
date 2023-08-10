<template>
  <a-layout>
    <a-layout-content
      :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <p>
        <a-form layout="inline" :model="param">
          <a-form-item>
            <a-input v-model:value="param.name" placeholder="名称">
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="handleQuery({pageNum: 1, pageSize: pagination.pageSize})">查询</a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="add">新增</a-button>
          </a-form-item>
        </a-form>
      </p>
      <a-table :columns="columns" :data-source="ebooks"
               :pagination = "pagination"
               :loading="loading"
               :row-key="record => record.id"
               @change = "handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{ text, record }">
          <a-span size="small">
            <a-button type="primary" @click="edit(record)">编辑</a-button>
            <a-popconfirm
              title="删除后不可恢复，确认删除？"
              ok-text="是"
              cancel-text="否"
              @confirm="del(record.id)"
            >
              <a-button type="danger">删除</a-button>
            </a-popconfirm>
          </a-span>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal
    title="电子书表单"
    v-model:visible="modalVisible"
    :confirm-loading="modalLoading"
    @ok="handleModalOk"
  >
    <a-form :model="ebook" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="封面">
        <a-input v-model:value="ebook.cover" />
      </a-form-item>
      <a-form-item label="名称">
        <a-input v-model:value="ebook.name" />
      </a-form-item>
      <a-form-item label="分类1">
        <a-input v-model:value="ebook.categoryParentId" />
      </a-form-item>
      <a-form-item label="分类2">
        <a-input v-model:value="ebook.categoryId" />
      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="ebook.description" type="textarea" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios';
import { message } from 'ant-design-vue';
import { Tool } from "@/util/tool";

export default defineComponent({
  name: 'AdminEbook',
  setup() {
    const param = ref();
    param.value = {};
    const ebooks = ref();
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0
    });
    const loading = ref(false);
    const columns = [
      {
        title: '封面',
        dataIndex: 'cover',
        slots: {customRender: 'cover'}
      },
      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '分类一',
        key: 'category1Id',
        dataIndex: 'categoryParentId'
      },
      {
        title: '分类二',
        key: 'category2Id',
        dataIndex: 'categoryId'
      },
      {
        title: '文档数',
        dataIndex: 'docCount'
      },
      {
        title: '阅读数',
        dataIndex: 'viewCount'
      },
      {
        title: '点赞数',
        dataIndex: 'voteCount'
      },
      {
        title: 'Action',
        key: 'action',
        slots: {customRender: 'action'}
      }
    ];

    /**
     * 数据查询
     **/
    const handleQuery = (params: any) => {
      loading.value = true;
      // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
      ebooks.value = [];
      axios.get("/ebook/list", {
        params: {
          pageNum: params.pageNum,
          pageSize: params.pageSize,
          name: param.value.name
        }
      }).then((response) => {
        loading.value = false;
        const data = response.data;
        if (data.code == 0) {
          ebooks.value = data.data.records;

          // 重置分页按钮
          pagination.value.current = params.pageNum;
          pagination.value.total = data.data.total;
        } else {
          message.error(data.message);
        }
      });
    };

    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      console.log("看看自带的分页参数都有啥：" + pagination);
      handleQuery({
        pageNum: pagination.current,
        pageSize: pagination.pageSize
      });
    };

    // -------------- 表单 -------------
    const ebook = ref({});
    const modalVisible = ref<boolean>(false);
    const modalLoading = ref<boolean>(false);
    /**
     * 编辑
     * @param record
     */
    const edit = (record: any) => {
      modalVisible.value = true;
      ebook.value = Tool.copy(record)
    };
    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true;
      ebook.value = {}
    };

    const del = (id: bigint) => {
      axios.delete("/ebook/delete/" + id).then((response) => {
        const data = response.data;
        if (data.code == 0) {
          // 重新加载列表
          handleQuery({
            pageNum: pagination.value.current,
            pageSize: pagination.value.pageSize,
          });
        }
      });
    }

    const handleModalOk = (e: MouseEvent) => {
      modalLoading.value = true;
      console.log(e);
      // 保存数据
      axios.post("/ebook/add", ebook.value).then((response) => {
        modalLoading.value = false;
        const data = response.data;
        if (data.code == 0) {
          modalVisible.value = false;
          // 重新加载列表
          handleQuery({
            pageNum: pagination.value.current,
            pageSize: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }
      });
    };

    onMounted(() => {
      handleQuery({pageNum: 1, pageSize: 10});
    });

    return {
      param,
      ebooks,
      pagination,
      columns,
      loading,
      handleTableChange,
      handleQuery,

      edit,
      add,

      ebook,
      modalVisible,
      modalLoading,
      handleModalOk,

      del
    }
  }
});
</script>

<style scoped>
img {
  width: 50px;
  height: 50px;
}
</style>