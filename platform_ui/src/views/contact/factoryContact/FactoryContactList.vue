<script setup>
import {useRouter} from 'vue-router'

const router=useRouter()

const add=()=>{
  router.push('/contact/factoryContact/add')
}
</script>

<template>
  <h1>工厂体系接口人</h1>
  <el-button type="primary" @click="add">添加</el-button>
</template>

<style scoped>

</style>