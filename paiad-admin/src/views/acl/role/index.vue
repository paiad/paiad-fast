<template>
  <div class="user-tip-card">
    <div class="title">
      <span class="icon"><Icon icon="fluent-color:person-key-32"/></span>
      系统内置三种用户角色
    </div>
    <p class="description">以下为示例账号，可用于登录测试不同权限：</p>

    <div class="user-list">
      <div class="user">
        <div class="credentials">
          <span class="label">Username:</span> <span class="value">admin</span>
          <span class="label">Password:</span> <span class="value">123456</span>
        </div>
        <div class="info-line"><span class="role-label">Role:</span> <span class="value">admin</span></div>
        <div class="info-line">
          <span class="perm-label">Permission:</span>
          <span class="value">user:update, user:get, user:delete, user:add</span>
        </div>
      </div>

      <div class="user">
        <div class="credentials">
          <span class="label">Username:</span> <span class="value">paiad</span>
          <span class="label">Password:</span> <span class="value">123456</span>
        </div>
        <div class="info-line"><span class="role-label">Role:</span> <span class="value">user</span></div>
        <div class="info-line">
          <span class="perm-label">Permission:</span>
          <span class="value">user:update, user:get</span>
        </div>
      </div>

      <div class="user">
        <div class="credentials">
          <span class="label">Username:</span> <span class="value">test-user</span>
          <span class="label">Password:</span> <span class="value">123456</span>
        </div>
        <div class="info-line"><span class="role-label">Role:</span> <span class="value">user</span></div>
        <div class="info-line">
          <span class="perm-label">Permission:</span>
          <span class="value">user:get</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.user-tip-card {
  margin: 20px;
  padding: 20px;
  background: #f7faff;
  border: 1px solid #d0e3ff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 80, 179, 0.05);
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #333;
}

.title {
  font-size: 16px;
  font-weight: bold;
  color: #2b5dab;
  display: flex;
  align-items: center;
  margin-bottom: 8px;
}

.icon {
  font-size: 18px;
  margin-right: 8px;
}

.description {
  font-size: 14px;
  color: #666;
  margin-bottom: 15px;
}

.user-list {
  display: grid;
  gap: 12px;
}

.user {
  padding: 12px 16px;
  background: #ffffff;
  border: 1px solid #e0e9f8;
  border-radius: 8px;
  font-size: 14px;
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.credentials {
  display: flex;
  flex-wrap: wrap;
  gap: 8px 12px;
}

.info-line {
  margin-left: 2px;
  line-height: 1.6;
}

.label {
  font-weight: 500;
  color: #2b5dab;
}

.role-label{
  font-weight: 500;
  color: #e76161;
}

.perm-label{
  font-weight: 500;
  color: #26bebe;
}
.value {
  font-weight: 600;
  color: #444;
}

</style>
<script setup lang="ts">
</script>
