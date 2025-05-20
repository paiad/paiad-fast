<template>
  <div class="login-container">
    <ParticlesBg
      class="particles-bg"
      :quantity="1 + 1 == 2 ? 314 * 3 : 314"
      :ease="100"
      :color="1 + 2 == 3 ? '#6ec5ff' : '#41b291'"
      :staticity="10"
      refresh
    />

    <div class="login-panel">
      <div style="display: flex; align-items: center;justify-content: center; height: 45px;margin-bottom: 15px">
        <img src="https://paiad.online/logo.svg" alt="图标" style="width: 32px; height: 32px; margin-right: 10px;">
        <span style="font-size: 24px; font-weight: bold; line-height: 32px;">欢迎登录</span>
      </div>
      <el-form :model="loginForm" :rules="rules" ref="loginFormRef" label-position="top" class="form">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="loginForm.username" placeholder="请输入用户名">
            <template #prefix>
              <el-icon>
                <User />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="loginForm.password" placeholder="请输入密码" show-password>
            <template #prefix>
              <el-icon>
                <Lock />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="login-button" @click="login" :loading="loading">登 录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import ParticlesBg from '../../components/Inspira/ParticlesBg.vue'
import { User, Lock } from '@element-plus/icons-vue'
import useUserStore from '../../store/modules/user.ts'
import { useRoute, useRouter } from 'vue-router'
import { ElNotification } from 'element-plus'
import { getTime } from '../../utils/time.ts'

let useStore = useUserStore()
let $router = useRouter()
//控制登录按钮加载，一开始不转
let loading = ref(false)
const loginFormRef = ref()
const loginForm = reactive({
  username: 'admin',
  password: '123456'
})

let $route = useRoute()
//登录
const login = async () => {
  //确保校验后发送请求
  await loginFormRef.value.validate()
  //登录按钮转圈
  loading.value = true
  try {
    await useStore.userLogin(loginForm)
    //判断登录是否有query参数，如果有就参考这个query跳
    let redirect:any = $route.query.redirect
    $router.push({path: redirect || '/'})
    ElNotification({
      type: 'success',
      title: `Hi, ${loginForm.username}！ ${getTime()}好`,
      message: '登录成功'
    })
  } catch (error) {
    loading.value = false
    ElNotification({
      type: 'error',
      message: (error as Error).message
    })
  }
}

const rules = {
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
    { min: 5, message: '账号长度至少为5位', trigger: 'blur' },
  ],
  password: [
    { required: true, min: 6, message: '密码长度至少为6位', trigger: 'change' },
  ]
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
  margin-top: -50px;
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
  background: linear-gradient(to right, #91d0fa, #c2b0fc);
  border-radius: 10px;
  font-weight: bold;
  transition: background 0.3s, transform 0.2s;
  color: #fdfcfc;
  border: none;
  padding: 15px;
  cursor: pointer;
}

.login-button:hover {
  transform: translateY(-0.6px);
}

.el-input :deep(.el-input__wrapper) {
  border-radius: 13px;
}
</style>
