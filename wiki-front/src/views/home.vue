<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
        mode="inline"
        :style="{ height: '100%', borderRight: 0 }"
        @click="handleClick"
      >
        <a-menu-item key="welcome">
          <MailOutlined />
          <span>欢迎</span>
        </a-menu-item>
        <a-sub-menu v-for="item in level1" :key="item.id">
          <template v-slot:title>
            <span><user-outlined />{{item.name}}</span>
          </template>
          <a-menu-item v-for="child in item.children" :key="child.id">
            <MailOutlined /><span>{{child.name}}</span>
          </a-menu-item>
        </a-sub-menu>
        <a-menu-item key="tip" :disabled="true">
          <span>以上菜单在分类管理配置</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout-content
      :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <div class="welcome" v-show="isShowWelcome">
        <the-welcome></the-welcome>
      </div>
      <a-list v-show="!isShowWelcome" item-layout="vertical" size="large" :grid="{ gutter: 20, column: 3 }" :data-source="ebooks">
        <template #renderItem="{ item }">
          <a-list-item key="item.name">
            <template #actions>
              <span>
                <component v-bind:is="'FileOutlined'" style="margin-right: 8px" />
                {{ item.docCount }}
              </span>
              <span>
                <component v-bind:is="'UserOutlined'" style="margin-right: 8px" />
                {{ item.viewCount }}
              </span>
              <span>
                <component v-bind:is="'LikeOutlined'" style="margin-right: 8px" />
                {{ item.voteCount }}
              </span>
            </template>
            <a-list-item-meta :description="item.description">
              <template #title>
                <router-link :to="'/doc?ebookId=' + item.id">
                  {{ item.name }}
                </router-link>
              </template>
              <template #avatar><a-avatar :src="item.cover" /></template>
            </a-list-item-meta>
          </a-list-item>
        </template>
      </a-list>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, reactive, toRef } from 'vue';
//import {LaptopOutlined, NotificationOutlined, UserOutlined, StarOutlined, LikeOutlined, MessageOutlined} from "@ant-design/icons-vue"; // @ is an alias to /src
import axios from 'axios';

import { message } from "ant-design-vue";
import {Tool} from "@/util/tool";
import TheWelcome from '@/components/the-welcome.vue';

export default defineComponent({
  name: 'Home',
  /*components: {
    NotificationOutlined, LaptopOutlined, UserOutlined,
    StarOutlined,
    LikeOutlined,
    MessageOutlined,
  },*/
  components: {
    TheWelcome
  },
  setup() {
    const ebooks = ref();
    //const ebooks = reactive({books: []})

    const level1 =  ref();
    let categorys: any;
    /**
     * 查询所有分类
     **/
    const handleQueryCategory = () => {
      axios.get("/category/all").then((response) => {
        const data = response.data;
        if (data.code === 0) {
          categorys = data.data;
          console.log("原始数组：", categorys);

          level1.value = [];
          level1.value = Tool.array2Tree(categorys, 0);
          console.log("树形结构：", level1.value);
        } else {
          message.error(data.message);
        }
      });
    };

    const pagination = {
      onChange: (page: number) => {
        console.log(page);
      },
      pageSize: 10,
    };
    // const actions: Record<string, string>[] = [
    //   { type: 'StarOutlined', text: '156' },
    //   { type: 'LikeOutlined', text: '156' },
    //   { type: 'MessageOutlined', text: '2' },
    // ];

    const isShowWelcome = ref(true);
    let categoryId = 0;

    const handleQueryEbook = () => {
      axios.get("/ebook/list", {
        params: {
          pageNum: 1,
          pageSize: 10,
          categoryId: categoryId
        }
      }).then((res) => {
        const data = res.data;
        ebooks.value = data.data.records;
        //ebooks.books = data.data;
      });
    };

    const handleClick = (e: any ) => {
      //console.log("menu click");
      isShowWelcome.value = e.key === 'welcome';
      categoryId = e.key;
      handleQueryEbook();
    };

    onMounted(() => {
      handleQueryCategory();
    });

    return {
      ebooks,
      //ebooks: toRef(ebooks, "books")
      pagination,
      // actions,

      handleClick,
      level1,
      isShowWelcome
    }
  }
});
</script>

<style scoped>
  .ant-avatar {
    width: 50px;
    height: 50px;
    line-height: 50px;
    border-radius: 8%;
    margin: 5px 0;
  }
</style>
