// src/components/index.ts

import SvgIcon from './SvgIcon/index.vue'
import Pagination from './Pagination/index.vue'
import type { App } from 'vue'

// 将组件统一放入对象中，便于批量注册
const allGlobalComponent = {
  SvgIcon,
  Pagination,
}

// 对外暴露插件对象
export default {
  install(app: App) {
    // 遍历注册所有全局组件
    for (const [key, component] of Object.entries(allGlobalComponent)) {
      app.component(key, component)
    }
  }
}
