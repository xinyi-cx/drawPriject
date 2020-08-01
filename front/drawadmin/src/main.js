// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/global.css'
// 导入字体图标库
import 'vue-awesome/icons'
import Icon from 'vue-awesome/components/Icon'

import axios from 'axios'
// 设置请求的根路径
// http://localhost:8011/tt-manage/userReward/listForDraw
axios.defaults.baseURL = 'http://localhost:8011/tt-manage/';
// axios请求拦截
axios.interceptors.request.use(config => {
  config.headers.Authorization = window.sessionStorage.getItem('token');
  return config;
})
Vue.prototype.$http = axios;
/** 挂载到vue的原型对象上 这样 vue的每个组件都可通过this直接访问到$http,从而发起ajax请求 */

Vue.use(ElementUI);
Vue.component('icon', Icon);
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
