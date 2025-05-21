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

const handleInfo = () => {
  ElMessage({
    message: `当前用户：${username.value}，角色：${role.value}`,
    type: 'success',
  })
}
</script>

<template>
  <el-card class="user-page-card" shadow="hover">
    <template #header>
      <div class="card-header">
        <span>👤 用户页面</span>
        <el-button type="primary" size="small" @click="handleInfo">查看用户信息</el-button>
      </div>
    </template>

    <div class="user-info">
      <p><strong>用户名：</strong>{{ username }}</p>
      <p>
        <strong>角色：</strong>
        <el-tag :type="role === 'admin' ? 'danger' : 'success'" effect="light">
          {{ role }}
        </el-tag>
      </p>
    </div>

    <el-alert
      class="role-tip"
      title="只有具有 user（包含 admin）角色的用户才能访问本页面"
      type="info"
      :closable="false"
      show-icon
    />
  </el-card>
</template>

<style scoped>
.user-page-card {
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

.user-info p {
  font-size: 16px;
  color: #555;
  margin: 10px 0;
}

.role-tip {
  margin-top: 20px;
}
</style>
