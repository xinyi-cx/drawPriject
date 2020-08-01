import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Admin from '@/components/Admin'
import QueryList from '@/components/QueryList'
import IndexMobile from '@/components/IndexMobile'
import Login from '@/components/login'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
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

router.beforeEach((to, from, next) => {
  // to代表将要访问的路径
  // from 从哪个页面来
  // next 是个放行函数
  if (to.path === '/') next();
  // 获取token
  const tokenStr = window.sessionStorage.getItem('user');
  if (!tokenStr) return next('/');
  next();
})




export default router;