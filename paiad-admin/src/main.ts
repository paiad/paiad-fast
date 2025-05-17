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

console.log(import.meta.env)
app.mount('#app')
