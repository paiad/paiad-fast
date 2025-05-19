<script setup lang="ts">
import setting from '@/setting'
</script>
<template>
  <div class="logo" v-if="setting.isLogoShow">
    <img :src="setting.logo" alt="" class="image"/>
<!--    <span class="title">{{ setting.title }}</span>-->
  </div>
</template>
<style lang="scss" scoped>
.logo {
  display: flex;
  justify-content: center;
  align-items: center;
  height: $base-menu-logo-height;
  padding-top: 5px;
  .image{
    width: 30px;
    height: 30px;
  }
}
</style>
