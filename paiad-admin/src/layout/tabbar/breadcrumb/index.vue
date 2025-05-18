<script setup lang="ts">
import { ArrowRight, Fold, Expand } from '@element-plus/icons-vue'
import useLayOutSettingStore from '@/store/modules/setting'
import { useRoute } from 'vue-router'
let LayOutSettingStore = useLayOutSettingStore()
const changeIcon = () => {
  LayOutSettingStore.isCollapse = !LayOutSettingStore.isCollapse
}
let $route = useRoute()
</script>
<template>
  <el-icon
    style="margin-right: 10px; cursor: pointer"
    size="20px"
    @click="changeIcon"
  >
    <component :is="LayOutSettingStore.isCollapse ? Expand : Fold"></component>
  </el-icon>
  <el-breadcrumb separator="/" :separator-icon="ArrowRight">
    <el-breadcrumb-item
      v-for="(item, index) in $route.matched"
      :key="index"
      v-show="item.meta.title"
      :to="item.path"
    >
      <el-icon style="vertical-align: middle; transform: translateY(-2px);">
        <Icon :icon="item.meta.icon" />
      </el-icon>
      <span style="margin-left: 9px">
        {{ item.meta.title }}
      </span>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>
<style lang="scss" scoped></style>
