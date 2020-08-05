import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import Welcome from '@/components/Welcome'
import Code from '@/components/draw/Code'
import Draw from '@/components/draw/Draw'
import WinningList from '@/components/user/WinningList'
import UserCode from '@/components/user/UserCode'
import UserList from '@/components/user/UserList'
import VipCode from '@/components/vip/VipCode'
import VipDraw from '@/components/vip/VipDraw'

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
        { path: '/code', component: Code },
        { path: '/draw', component: Draw },
        { path: '/userCode', component: UserCode },
        { path: '/winList', component: WinningList },
        { path: '/userList', component: UserList }, 
        { path: '/vipCode', component: VipCode },
        { path: '/vipDraw', component: VipDraw }
      ]
    }
  ]
})

// 路由导航守卫控制页面访问权限
// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  // to代表将要访问的路径
  // from 从哪个页面来
  // next 是个放行函数
  if (to.path === '/login') next();
  // 获取token
  const tokenStr = window.sessionStorage.getItem('token');
  if (!tokenStr) return next('/login')
  next();
})


export default router