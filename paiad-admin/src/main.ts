import { createApp } from 'vue'
// 引入element-ui
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
//国际化(zh)
import zhCn from 'element-plus/es/locale/lang/zh-cn'
//svg配置
import 'virtual:svg-icons-register'
//引入全局样式
import './styles/index.scss'

//引入pinia
import pinia from './store'

const app = createApp(App)

app.use(ElementPlus, {
  locale: zhCn,
})

// import SvgIcon from './components/SvgIcon/index.vue'
// app.component('SvgIcon', SvgIcon)

//引入自定义插件对象(全局组件)
import GlobalComponents from './components'
import router from './router'
app.use(GlobalComponents)



//测试接口
// import axios from 'axios'
// axios({
//   url: 'http://localhost:9090/api/user/login',
//   method:'POST',
//   data:{
//     username: 'admin',
//     password: '123456'
//   }
//
// })

//注册路由
app.use(router)
//安装仓库
app.use(pinia)

// iconify 图标库
import { Icon } from '@iconify/vue'

//路由鉴权守卫
import './permission.ts'

//暗黑模式
import 'element-plus/theme-chalk/dark/css-vars.css'
app.component('Icon', Icon)
app.mount('#app')
