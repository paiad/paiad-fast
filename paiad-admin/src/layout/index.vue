<script setup lang="ts">
import Logo from './logo/index.vue'
import Menu from './menu/index.vue'
import useUserStore from '../store/modules/user.ts'

let userStore = useUserStore()
// let $route = useRoute()
</script>

<template>
  <div class="layout-container">
    <!-- 左侧菜单 -->
    <div class="layout-slider">
      <Logo></Logo>
      <!-- 展示菜单 -->
      <el-scrollbar class="scrollbar">
        <!-- 菜单组件 -->
        <el-menu background-color="#ffffff" text-color="#3f9fd0">
          <!-- 动态路由菜单 -->
          <Menu :menuList="userStore.menuRoutes" />
        </el-menu>
      </el-scrollbar>
    </div>
    <!-- 顶部导航 -->
    <div class="layout-tabbar">111</div>
    <!-- 内容展示区域 -->
    <div class="layout-main">222</div>
  </div>
</template>

<style scoped lang="scss">
.layout-container{
  width: 100%;
  height: 100vh;
  background: #3f9fd0;
  .layout-slider{
      width: $base-menu-width;
      height: 100vh;
      background: $base-menu-background;
      .scrollbar{
          width: 100%;
          height: calc(100vh - $base-menu-logo-height);
          .el-menu{
              border-right: none
          }
      }
  }
  .layout-tabbar{
    position: fixed;
    width: calc(100% - $base-menu-width);
    height: $base-tabbar-height;
    background: #ff4d51;
    top: 0px;
    left: $base-menu-width;
  }
  .layout-main{
    position: absolute;
    width: calc(100% - $base-menu-width);
    height: calc(100vh - $base-tabbar-height);
    background: yellow;
    left: $base-menu-width;
    top: $base-tabbar-height;
    padding: 20px;
    overflow: auto;
  }
}

</style>
