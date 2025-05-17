<template>
  <div class="login-container">
    <ParticlesBg
      class="particles-bg"
      :quantity="1 + 1 == 2 ? 314 + 271 : 314"
      :ease="100"
      :color="1 + 2 == 3 ? '#4ca5dc' : '#41b291'"
      :staticity="10"
      refresh
    />

    <div class="login-panel">
      <h2 class="title">欢迎登录</h2>
      <el-form :model="loginForm" :rules="rules" ref="formRef" label-position="top" class="form">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="loginForm.username" placeholder="请输入用户名">
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="loginForm.password" placeholder="请输入密码" show-password="false">
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="login-button" @click="login">登 录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import ParticlesBg from '../../components/Inspira/ParticlesBg.vue'
// Import icons
import { User, Lock } from '@element-plus/icons-vue'

const formRef = ref()
const loginForm = reactive({
  username: 'admin',
  password: '123456',
})

const rules = {
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
}

//登录
const login = () => {
  formRef.value?.validate((valid: boolean) => {
    if (valid) {
      ElMessage.success('登录成功！')
      // TODO: 执行登录逻辑
    }
  })
}
</script>

<style scoped>
.login-container {
  position: relative;
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(to bottom, #e8f4fd, #f6fbff);
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
}

.particles-bg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 0;
}

.login-panel {
  position: relative;
  z-index: 1;
  width: 360px;
  padding: 40px 30px;
  border-radius: 16px;
  background-color: #ffffffdd;
  box-shadow: 0 8px 30px rgba(0, 128, 192, 0.1);
  backdrop-filter: blur(6px);
  text-align: center;
}

.title {
  font-size: 24px;
  font-weight: 600;
  color: #2c3e50;
  margin-bottom: 30px;
}

.form {
  text-align: left;
}

/* You might want to add some padding or adjust input styles
   if the default spacing with icons isn't perfect */
.el-input :deep(.el-input__prefix) {
  /* Example: Add some spacing */
  margin-right: 8px;
}


.login-button {
  width: 100%;
  background-color: #41b291;
  border-color: #41b291;
  font-weight: bold;
  transition: background-color 0.3s;
}

.login-button:hover {
  background-color: #379f80;
  border-color: #379f80;
}
</style>
