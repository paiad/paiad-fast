<script setup lang="ts">
defineProps(['menuList'])
</script>

<script lang="ts">
export default {
  name: 'Menu'
}
</script>
<template>
  <template v-for="(item, index) in menuList" :key="item.path">
    <!-- 没有子路由 -->
    <template v-if="!item.children">
      <el-menu-item :index="item.path" v-if="item.meta.isShow">
        <template #title>
          <el-icon>
            <Icon :icon="item.meta.icon" />
          </el-icon>
          <span>{{ item.meta.title }}</span>
        </template>
      </el-menu-item>
    </template>
    <!-- 有子路由但只有一个 -->
    <template v-if="item.children && item.children.length === 1">
      <el-menu-item :index="item.children[0].path" v-if="item.children[0].meta.isShow">
        <el-icon>
          <Icon :icon="item.children[0].meta.icon" />
        </el-icon>
        <template #title>
          <span>{{ item.children[0].meta.title }}</span>
        </template>
      </el-menu-item>
    </template>
    <!-- 有子路由且大于一个 -->
    <el-sub-menu :index="item.path"  v-if="item.children && item.children.length > 1">
      <template #title>
        <el-icon>
          <Icon :icon="item.meta.icon" />
        </el-icon>
        <span>{{ item.meta.title }}</span>
      </template>
      <!-- 看子路由有没有 child -->
      <Menu :menuList="item.children"></Menu>
    </el-sub-menu>
  </template>
</template>
<style lang="scss" scoped>
.el-menu-item:hover{
  border-radius: 12px;
  background-color: $base-menu-bg-hover-color;
}

::v-deep(.el-menu-item:hover) {
  background-color: $base-menu-bg-hover-color !important;
}

::v-deep(.el-sub-menu__title:hover) {
  border-radius: 12px;
  background-color: $base-menu-bg-hover-color !important;
}

</style>
