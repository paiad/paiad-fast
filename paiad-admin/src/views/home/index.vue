<template>
  <div>
    <h1>我是1级路由：展示登录后的数据</h1>
  </div>
</template>

<script setup lang="ts">
import {onMounted} from 'vue'
import useUserStore from '@/store/modules/user.ts'

let useStore = useUserStore();
onMounted(()=>{
  useStore.userInfo();
})
</script>

<style scoped>

</style>
