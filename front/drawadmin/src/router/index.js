import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import Welcome from '@/components/Welcome'
import Money from '@/components/draw/Money'
import Draw from '@/components/draw/Draw'
import QueryList from '@/components/draw/QueryList'
import Upload from '@/components/draw/Upload'
 
Vue.use(Router)

const router = new Router({
  routes: [
    { path: '/', redirect: '/login' },
    { path: '/login', component: Login },
    {
      path: '/home', 
      component: Home, 
      redirect: '/welcome',
      children: [
        { path: '/welcome', component: Welcome },
        { path: '/money', component: Money },
        { path: '/draw', component: Draw },
        { path: '/upload', component: Upload },
        { path: '/query', component: QueryList }
      ]
    }
  ]
})

// 路由导航守卫控制页面访问权限
// 挂载路由导航守卫
/**
  router.beforeEach((to, from, next) => {
  // to代表将要访问的路径
  // from 从哪个页面来
  // next 是个放行函数
  if(to.path === '/login') next();
  // 获取token
  const tokenStr = window.sessionStorage.getItem('token');
  if(!tokenStr) return next('/login')
})
 */


export default router