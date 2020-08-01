// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import Blob from 'excel/Blob.js'
import Export2Excel from 'excel/Export2Excel.js'

Vue.use(ElementUI);

import './assets/css/reset.css'
import 'babel-polyfill'

Vue.config.productionTip = false
Vue.prototype.$axios = axios
axios.defaults.baseURL = 'http://192.168.0.101:8848/tt-manage/';
// axios请求拦截
// axios.interceptors.request.use(config => {
//   config.headers.Authorization = window.sessionStorage.getItem('token');
//   return config;
// })
Vue.prototype.$http = axios;


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
