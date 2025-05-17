import { createApp } from 'vue'
// 引入element-ui
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
//国际化(zh)
import zhCn from 'element-plus/es/locale/lang/zh-cn'

const app = createApp(App)

app.use(ElementPlus, {
  locale: zhCn,
})

//svg配置
import 'virtual:svg-icons-register'
// import SvgIcon from './components/SvgIcon/index.vue'
// app.component('SvgIcon', SvgIcon)

//引入自定义插件对象(全局组件)
import GlobalComponents from './components'
app.use(GlobalComponents)

import './styles/index.scss'
app.mount('#app')
