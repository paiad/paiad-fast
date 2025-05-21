<script setup lang="ts">

</script>

<template>
  <div class="button-container">
    <div>不同级别的用户能看到不同的按钮</div>
    <el-button
      type="primary"
      v-permission="['user:add']"
    >
      添加用户
    </el-button>

    <el-button
      v-permission="['user:delete', 'admin']"
      type="danger"
    >
      删除用户
    </el-button>

    <el-button
      type="success"
      v-permission="['user:get']"
    >
      查询用户
    </el-button>

    <el-button
      type="warning"
      v-permission="['user:update']"
    >
      查询用户
    </el-button>
  </div>

</template>

<style scoped>
.button-container{
  padding: 20px;
}
</style>
