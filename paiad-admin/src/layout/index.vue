<script setup lang="ts">
import Logo from './logo/index.vue'
import Menu from './menu/index.vue'
import TabBar from './tabbar/index.vue'
import Main from './main/index.vue'
import useLayOutSettingStore from '@/store/modules/setting'
import useUserStore from '@/store/modules/user'
import { useRoute } from 'vue-router'

let userStore = useUserStore()
let $route = useRoute()

let LayOutSettingStore = useLayOutSettingStore()
</script>
<template>
  <el-container class="layout-container" style="height: 100vh">
    <el-aside
      width="200px"
      :class="{ isCollapse: LayOutSettingStore.isCollapse ? true : false }"
    >
      <el-scrollbar>
        <el-menu
          :default-active="$route.path"
          active-text-color="#3f9fd0"
          background-color="#fff"
          text-color="#708070"
          :collapse="LayOutSettingStore.isCollapse"
          class="custom-menu"
          router
        >
          <!-- 动态路由菜单 -->
          <Logo />
          <Menu :menuList="userStore.menuRoutes" />
        </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container class="container">
      <TabBar style="width: 100%" />
      <el-main
        :style="{
          left: !LayOutSettingStore.isCollapse ? '200px' : '64px',
          width: LayOutSettingStore.isCollapse
            ? 'calc(100% - 64px)'
            : 'calc(100% - 200px)',
        }"
      >
        <el-scrollbar>
          <Main />
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>
<style lang="scss" scoped>
.layout-container {
  height: 100%;
}
.layout-container .el-menu {
  border-right: none;
}
.layout-container .el-main {
  position: absolute;
  padding: 0px;
  left: 200px;
  top: 60px;
  transition: all 0.3s;
  width: calc(100% - $base-menu-width);
  height: calc(100vh - 60px);
}

.el-aside {
  background-color: #ffffff !important;
  box-shadow: rgb(178, 178, 178) 0px 10px 3px 0px;
  transition: all 0.3s;
}

.el-header {
  padding-left: 15px;
  margin-left: 1.2px;
  background-color: $base-header-bg-color !important;
  box-shadow: rgb(229, 227, 227) 0px 1px 1px 0px;
  z-index: 999;
}
.isCollapse {
  width: 64px;
}

@media screen and (max-width: 768px) {
  .el-aside {
    position: fixed;
    left: -200px;
    top: 0;
    bottom: 0;
    z-index: 1000;

    &.isCollapse {
      left: 0;
    }
  }

  .el-main {
    left: 0 !important;
    width: 100% !important;
    display: flex !important;
    justify-content: center !important;
    align-items: center !important;
  }

  .container {
    margin-left: 0;
  }
}
</style>
