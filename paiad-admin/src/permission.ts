import router from '@/router'
import setting from './setting'
import 'nprogress/nprogress.css'
import nprogress from 'nprogress'
import pinia from './store'
import useUserStore from './store/modules/user.ts'

nprogress.configure({ showSpinner: false })
const userStore = useUserStore(pinia)

// 检查是否有权限访问目标路径
function hasAccessToPath(routes: any[], path: string): boolean {
  return routes.some(route => {
    if (path === route.path || path.startsWith(route.path)) return true
    if (route.children) return hasAccessToPath(route.children, path)
    return false
  })
}

// 全局前置守卫
router.beforeEach(async (to: any, from: any, next: any) => {
  document.title = to.meta.title + ` | ${setting.title}`
  nprogress.start()
  const token = userStore.token
  const username = userStore.username

  if (token) {
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
      if (username) {
        const role = userStore.role
        if (to.meta.roles && !to.meta.roles.includes(role)) {
          return next('/404') // 或 next(false) 拒绝进入，或者重定向到无权限页
        }
        next()
      } else {
        try {
          await userStore.userInfo()
          const menuRoutes = userStore.menuRoutes
          const redirectPath = (to.query.redirect || to.fullPath) as string

          const hasPermission = hasAccessToPath(menuRoutes, redirectPath)

          if (hasPermission) {
            next(redirectPath)
          } else {
            next('/login')
          }
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
