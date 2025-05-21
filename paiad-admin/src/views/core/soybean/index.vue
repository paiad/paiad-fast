<script setup lang="ts">
const url = 'https://elp.soybeanjs.cn'
</script>

<template>
  <div class="iframe-container">
    <iframe :src="url"></iframe>
  </div>
</template>

<style scoped>
.iframe-container {
  width: 100%;
  height: 90vh;
  overflow: hidden;
}
iframe {
  width: 100%;
  height: 100%;
  border: none;
}
</style>
