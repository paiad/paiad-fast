<script setup lang="ts">
const url = 'https://www.cnblogs.com/'  // 👈 你要展示的博客网址
</script>

<template>
  <div class="iframe-container">
    <iframe :src="url"></iframe>
  </div>
</template>

<style scoped>
.iframe-container {
  width: 100%;
  height: 85vh;
  overflow: hidden;
}
iframe {
  width: 100%;
  height: 100%;
  border: none;
}
</style>
