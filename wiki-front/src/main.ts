import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import * as Icons from '@ant-design/icons-vue';
import axios from 'axios';

import JSONBIG from 'json-bigint';
import {Tool} from "@/util/tool";

axios.defaults.baseURL = process.env.VUE_APP_SERVER;

axios.defaults.transformResponse = [
  function (data) {
    const json = JSONBIG({
      storeAsString: true
    })
    return JSONBIG.parse(data)
  }
]
/**
 * axios拦截器
 */
axios.interceptors.request.use(config => {
  console.log('请求参数:', config);
  const token = store.state.user.token;
  if (Tool.isNotEmpty(token)) {
    config.headers.token = token;
    console.log("请求headers增加token:", token);
  }
  return config;
}, error => {
  return Promise.reject(error);
})
axios.interceptors.response.use(res => {
  console.log('返回结果:', res);
  return res;
}, error => {
  console.log('返回错误:', error);
  return Promise.reject(error);
})

const app = createApp(App);
app.use(store).use(router).use(Antd).mount('#app')

// 全局使用图标
const icons: any = Icons;
for (const i in icons) {
  app.component(i, icons[i])
}

console.log('环境', process.env.NODE_ENV)
