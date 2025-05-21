<script setup lang="ts">
import { ref, onMounted } from 'vue'
import useUserStore from '@/store/modules/user'
import { ElMessage } from 'element-plus'

const userStore = useUserStore()
const username = ref('')
const role = ref('')

onMounted(() => {
  username.value = userStore.username
  role.value = userStore.role
})

const showUserInfo = () => {
  ElMessage({
    message: `用户名：${username.value}，角色：${role.value}`,
    type: 'success',
    grouping: true
  })
}
</script>

<template>
  <el-card class="user-card" shadow="hover">
    <template #header>
      <div class="card-header">
        <span>👑 管理员面板</span>
        <el-button size="small" type="primary" @click="showUserInfo">显示用户信息</el-button>
      </div>
    </template>

    <div class="info-block">
      <p><strong>用户名：</strong>{{ username }}</p>
      <p><strong>角色：</strong>{{ role }}</p>
    </div>

    <el-alert
      title="只有具有 admin 角色的用户才能看到本页面"
      type="success"
      effect="light"
      :closable="false"
      class="mt-4"
    />
  </el-card>
</template>

<style scoped>
.user-card {
  max-width: 600px;
  margin: 40px auto;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  border-radius: 12px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 18px;
  color: #2c3e50;
}

.info-block {
  font-size: 16px;
  color: #444;
  line-height: 1.8;
}

.mt-4 {
  margin-top: 20px;
}
</style>
