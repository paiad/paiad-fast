// https://vite.dev/config/
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import vueDevTools from 'vite-plugin-vue-devtools'

// 添加 SVG 图标插件
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'

export default defineConfig({
  plugins: [
      vue(),
      vueDevTools(),
      createSvgIconsPlugin({
      iconDirs: [path.resolve(__dirname, 'src/assets/icons')],
      symbolId: 'icon-[dir]-[name]'
    })
  ],
  css: {
    preprocessorOptions: {
      scss: {
        additionalData: `@use "@/styles/variable.scss" as *;`,
      },
    },
  },
  resolve: {
    alias: {
      '@': path.resolve('./src')
    }
  },
  //配置跨域
  server: {
    proxy: {
      '/dev-api': {
        target: 'http://localhost:9090',
        changeOrigin: true,
        // http://localhost:5173/dev-api/user/login => http://localhost:9090/api/user/login
        rewrite: (path) => path.replace(/^\/dev-api/, '/api'),
      }
    }
  }
})
