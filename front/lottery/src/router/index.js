import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Admin from '@/components/Admin'
import QueryList from '@/components/QueryList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
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
