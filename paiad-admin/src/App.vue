<script setup lang="ts">
import {Plus} from '@element-plus/icons-vue'
</script>

<template>
  <div>
    <el-button type="primary" size="default" @click="" :icon="Plus"></el-button>
    <SvgIcon name="home" color="red" width="50px" height="50px"></SvgIcon>
    <h1>123</h1>
  </div>
</template>

<style scoped lang="scss">
div {
  h1 {
    color: $color;
  }
}
</style>
