<script setup lang="ts">
import { ref, onMounted } from 'vue'
import useUserStore from '@/store/modules/user'

const userStore = useUserStore()

const username = ref('')
const role = ref('')

onMounted(() => {
  username.value = userStore.username
  role.value = userStore.role
})
</script>

<template>
  <div class="user-page">
    <h2>欢迎来到用户页面 🎉</h2>
    <p><strong>用户名：</strong>{{ username }}</p>
    <p><strong>角色：</strong>{{ role }}</p>
    <div class="tip">
      只有具有 <code>user(include admin)</code> 角色的用户才能看到本页面。
    </div>
  </div>
</template>

<style scoped>
.user-page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  min-height: 0vh; /* 让页面高度撑满视口，实现垂直居中 */
  padding: 24px;
  background-color: #f9fafb;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0,0,0,0.05);
  font-family: "Segoe UI", sans-serif;
  text-align: center;
}


h2 {
  color: #333;
  margin-bottom: 12px;
}

p {
  font-size: 16px;
  color: #555;
}

.tip {
  margin-top: 20px;
  font-size: 14px;
  color: #888;
  background: #eef2f7;
  padding: 10px;
  border-left: 4px solid #409eff;
}
</style>
