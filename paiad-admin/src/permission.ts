import router from '@/router'
import setting from './setting'
import 'nprogress/nprogress.css'
import nprogress from 'nprogress'
import pinia from './store'
import useUserStore from './store/modules/user'

nprogress.configure({ showSpinner: false })
const userStore = useUserStore(pinia)

// 全局前置守卫
router.beforeEach(async (to: any, from: any, next: any) => {
  document.title = to.meta.title + ` | ${setting.title}`
  nprogress.start()
  const token = userStore.token
  const username = userStore.username
  const role = userStore.role
  console.log(role)
  // console.log(username, token)
  if (token) {
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
      if (username) {
        console.log(to.meta.role)
        if (to.meta.roles && !to.meta.roles.includes(role)) {
          return next('/404') // 或 next(false) 拒绝进入，或者重定向到无权限页
        }
        next()
      } else {
        try {
          await userStore.userInfo()
          next();
          // // 添加异步路由
          // asyncRoute.forEach((route) => {
          //   router.addRoute(route)
          // })
          // next({ ...to, replace: true })
        } catch (error) {
          await userStore.userLogout()
          next({ path: '/login', query: { redirect: to.path } })
        }
      }
    }
  } else {
    if (to.path == '/login') {
      next()
    } else {
      next({ path: '/login', query: { redirect: to.path } })
    }
  }
})

// 全局后置守卫
router.afterEach((route) => {
  nprogress.done()
})
