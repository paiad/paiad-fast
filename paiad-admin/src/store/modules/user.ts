import { defineStore } from 'pinia'
import type { LoginFormData, LoginResponseData } from '../../api/user/type.ts'
import { reqLogin } from '../../api/user'


const useUserStore = defineStore('User', {
  // 小仓库存储数据的地方
  state: () => {
    return {
        token:localStorage.getItem('paiad-token'),
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
        localStorage.setItem("paiad-token", res.data.token)
        return 'ok'
      }else{
        return Promise.reject(new Error(res.msg))
      }
    },
  },
  getters: {},
})

export default useUserStore
