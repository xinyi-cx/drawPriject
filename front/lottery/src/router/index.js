import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Admin from '@/components/Admin'
import QueryList from '@/components/QueryList'
import IndexMobile from '@/components/IndexMobile'
import Login from '@/components/login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/mobile',
      name: 'IndexMobile',
      component: IndexMobile
    }, {
      path: '/admin',
      name: 'Admin',
      component: Admin
    }, {
      path: '/query',
      name: 'QueryList',
      component: QueryList
    }
  ]
})
