import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import IndexMobile from '@/components/IndexMobile'

Vue.use(Router)

let flag = navigator.userAgent.match(
  /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i
);

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: flag ? '/mobile' : '/index',
    },
    {
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/mobile',
      name: 'IndexMobile',
      component: IndexMobile
    }
  ]
})

export default router;