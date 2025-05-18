<script setup lang="ts">
import { ref } from 'vue'

let flag = ref(true)
</script>

<template>
    <!-- 路由组件出口的位置 -->
    <router-view v-slot="{ Component }">
      <transition name="fade">
        <component :is="Component" v-if="flag" />
      </transition>
    </router-view>
</template>

<style lang="scss" scoped>
.fade-enter-from {
  opacity: 0;
  transform: scale(0);
}
.fade-enter-active {
  transition: all 0.3s;
}

.fade-enter-to {
  opacity: 1;
  transform: scale(1);
}
</style>
