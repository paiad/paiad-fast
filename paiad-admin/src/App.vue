<script setup lang="ts">
import { onMounted } from 'vue'
import { reqLogin } from '@/api/user'

onMounted(() => {
    reqLogin({username:"admin", password:"123456"})
})
</script>

<template>

</template>

<style scoped lang="scss">

</style>
