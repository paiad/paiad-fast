<script setup lang="ts">
import { onMounted } from 'vue'
import request from '@/utils/request.ts'

interface LoginResponse {
  code: number
  data: any
  message: string
}

onMounted(() => {
  request({
    url: '/user/login',
    method: 'POST',
    data: {
      username: 'admin',
      password: '123456'
    }
  }).then((res: LoginResponse) => {
    console.log(res)
  })
})
</script>

<template>

</template>

<style scoped lang="scss">

</style>
