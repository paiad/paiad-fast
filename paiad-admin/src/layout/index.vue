<script setup lang="ts">
import Logo from './logo/index.vue'
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
          <el-menu-item index="1">首页</el-menu-item>
          <el-menu-item index="2">数据大屏</el-menu-item>
          <el-sub-menu index="3">
            <template #title>
              权限管理
            </template>
            <el-menu-item index="2-1">用户管理</el-menu-item>
            <el-menu-item index="2-2">角色管理</el-menu-item>
            <el-menu-item index="2-3">菜单管理</el-menu-item>
          </el-sub-menu>
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
