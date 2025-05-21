import { defineStore } from 'pinia'
import type { LoginFormData, LoginResponseData } from '../../api/user/type.ts'
import { reqLogin, reqUserInfo } from '../../api/user'
import type { UserState } from './types/type.ts'
import { GET_TOKEN, REMOVE_TOKEN, SET_TOKEN } from '../../utils/token.ts'
import { constantRoute } from '@/router/routes.ts'
import { anyRoute, asyncRoute, coreRoute } from '../../router/routes.ts'
import router from '../../router'
//深拷贝
import cloneDeep from 'lodash/cloneDeep'

//过滤异步路由
export function filterAsyncRoute(routes:any, role:any) {
  const res:any = []

  routes.forEach(route => {
    const tmp = { ...route }

    // 判断当前路由是否限制了角色访问
    const routeRoles = tmp.meta?.roles
    if (!routeRoles || routeRoles.includes(role)) {
      // 如果有子路由，递归处理
      if (tmp.children && tmp.children.length > 0) {
        tmp.children = filterAsyncRoute(tmp.children, role)
      }
      res.push(tmp)
    }
  })

  return res
}


const useUserStore = defineStore('User', {
  // 小仓库存储数据的地方
  state: (): UserState => {
    return {
      token: GET_TOKEN(),
      menuRoutes: constantRoute,
      userid: -1,
      username: '',
      nickname: '',
      avatar: '',
      role:'',
      permission:'',
    }
  },
  // 异步|逻辑的地方
  actions: {
    //用户登录方法
    async userLogin(data: LoginFormData) {
      const res: LoginResponseData = await reqLogin(data)
      // console.log(res)
      if (res.code == 200) {
        this.token = res.data.token
        SET_TOKEN((res.data.token as string))
        return 'ok'
      } else {
        return Promise.reject(new Error(res.msg))
      }
    },

    //获取用户信息
    async userInfo() {
      let res = await reqUserInfo()
      // console.log(res)
      if (res.code === 200) {
        this.userid = res.data.id as number
        this.username = res.data.username as string
        this.nickname = res.data.nickname as string
        this.avatar = res.data.avatar as string
        this.role = res.data.role as string
        this.permission = res.data.permission as string
        // 根据角色过滤动态路由
        const userAsyncRoute = filterAsyncRoute(cloneDeep(asyncRoute), res.data.role)
        const userCoreRoute = filterAsyncRoute(cloneDeep(coreRoute), res.data.role)
        const allRoutes = [
          ...constantRoute,
          ...userAsyncRoute,
          ...userCoreRoute,
          ...anyRoute
        ]
        this.menuRoutes = allRoutes

        // 注册到路由器中
        allRoutes.forEach((route: any) => {
          router.addRoute(route)
        })

        return 'ok'
      } else {
        return Promise.reject(new Error(res.message))
      }
    },
    //退出登录
    async userLogout() {
      this.token = ''
      this.userid = -1
      this.username = ''
      this.nickname = ''
      this.avatar = ''
      this.role = ''
      this.permission = ''
      this.menuRoutes = []
      //清楚：token
      REMOVE_TOKEN();
    }
  },

  getters: {}
})

export default useUserStore
