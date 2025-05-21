<script setup lang="ts">
import { ElMessage } from 'element-plus'

// 按钮事件处理
const handleAdd = () => {
  ElMessage.success('添加用户成功！')
}

const handleDelete = () => {
  ElMessage.success('删除用户成功！')
}

const handleQuery = () => {
  ElMessage.success('查询用户信息成功！')
}

const handleUpdate = () => {
  ElMessage.success('更新用户成功！')
}
</script>

<template>
  <div class="button-page">
    <h2>权限按钮演示 🎯</h2>
    <p class="description">不同级别的用户能看到不同的按钮（基于 <code>v-permission</code> 指令）</p>

    <div class="button-group">
      <el-button
        type="primary"
        v-permission="['user:add']"
        @click="handleAdd"
      >
        添加用户
      </el-button>

      <el-button
        type="danger"
        v-permission="['user:delete', 'admin']"
        @click="handleDelete"
      >
        删除用户
      </el-button>

      <el-button
        type="success"
        v-permission="['user:get']"
        @click="handleQuery"
      >
        查询用户
      </el-button>

      <el-button
        type="warning"
        v-permission="['user:update']"
        @click="handleUpdate"
      >
        更新用户
      </el-button>
    </div>
  </div>
</template>

<style scoped>
.button-page {
  padding: 24px;
  max-width: 600px;
  margin: 40px auto;
  background-color: #f9fafb;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  font-family: "Segoe UI", sans-serif;
}

h2 {
  color: #333;
  margin-bottom: 12px;
}

.description {
  font-size: 15px;
  color: #666;
  margin-bottom: 20px;
  background: #eef2f7;
  padding: 12px;
  border-left: 4px solid #409eff;
}

.button-group {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
}
</style>
