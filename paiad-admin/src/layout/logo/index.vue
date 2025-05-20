<script setup lang="ts">
import setting from '@/setting'
</script>
<template>
  <div class="logo">
    <img :src="setting.logo" alt="" class="image" v-if="setting.isLogoShow"/>
    <span class="title" v-if="setting.isTitleShow">{{ setting.title }}</span>
  </div>
</template>
<style lang="scss" scoped>
.logo {
  display: flex;
  justify-content: center;
  align-items: center;
  height: $base-menu-logo-height;
  padding-top: 5px;
  background: #f6f9fc;
  border-radius: 6px;
  .image{
    width: 30px;
    height: 30px;
  }
  .title{
    margin-left: 8px;
    font-size: 16px;
    font-weight: 600;
    color: #333;
    white-space: nowrap;
  }
}
</style>
