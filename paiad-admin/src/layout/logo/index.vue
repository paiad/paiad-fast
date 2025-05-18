<script setup lang="ts">
import setting from '@/setting'
</script>
<template>
  <div class="logo" v-if="setting.logoHidden">
    <img :src="setting.logo" alt="" class="image"/>
<!--    <span class="title">{{ setting.title }}</span>-->
  </div>
</template>
<style lang="scss" scoped>
.logo {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  height: $base-menu-logo-height;
  .image{
    width: 30px;
    height: 30px;
  }
}
</style>
