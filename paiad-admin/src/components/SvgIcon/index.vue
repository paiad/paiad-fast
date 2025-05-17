<template>
  <svg :style="{ width, height}">
    <use :xlink:href="`#icon-${name}`" :fill="color"></use>
  </svg>
</template>

<script setup lang="ts">
const { name, color = '', width = '16px', height = '16px' } = defineProps<{
  name: string
  color?: string
  width?: string
  height?: string
}>()
</script>

<style scoped>
</style>