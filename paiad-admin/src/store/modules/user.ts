import { defineStore } from 'pinia'
import type { LoginFormData, LoginResponseData } from '../../api/user/type.ts'
import { reqLogin } from '../../api/user'
import type { UserState } from './types/type.ts'
import { GET_TOKEN, SET_TOKEN } from '../../utils/token.ts'


const useUserStore = defineStore('User', {
  // 小仓库存储数据的地方
  state: ():UserState => {
    return {
        token:GET_TOKEN(),
    }
  },
  // 异步|逻辑的地方
  actions: {
    //用户登录方法
    async userLogin(data: LoginFormData) {
      const res: LoginResponseData = await reqLogin(data)
      // console.log(res)
      if(res.code == 200){
        this.token = res.data.token
        SET_TOKEN((res.data.token as string))
        return 'ok'
      }else{
        return Promise.reject(new Error(res.msg))
      }
    },
  },
  getters: {},
})

export default useUserStore
