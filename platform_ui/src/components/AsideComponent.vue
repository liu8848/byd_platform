<script setup>

</script>
<template>
  <el-menu :default-openeds="['1', '3']"
           background-color="#79bbff"
           text-color="#fff"
           active-text-color="#303133"
           :default-active="$route.path"
           router
  >
    <el-sub-menu index="/auditorStandingBook">
      <template #title>
        审核员台账
      </template>
      <el-menu-item index="/auditorStandingBook/inWork">在职审核员台账</el-menu-item>
      <el-menu-item index="/auditorStandingBook/change">事业部变动审核员台账</el-menu-item>
      <el-menu-item index="/auditorStandingBook/outWork">离职审核员台账</el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="/contact">
      <template #title>
        联系人
      </template>
      <el-menu-item index="/contact/factoryContact">工厂体系接口人</el-menu-item>
    </el-sub-menu>

  </el-menu>
</template>