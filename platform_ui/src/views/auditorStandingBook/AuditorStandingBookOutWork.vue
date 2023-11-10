<script setup lang="ts">
import {ref} from "vue";
import {standingBookOutWorkListService,standingBookOutWorkExportService} from '@/api/auditor'

let datas=ref([])

const query=async ()=>{
  const res=await standingBookOutWorkListService()
  datas.value=res.data.data
}

const exportExcel=async()=>{
  console.log('-----------------------开始导出-------------------------')
  await standingBookOutWorkExportService()

}

</script>

<template>
  <el-button type="primary" @click="query">查询</el-button>
  <el-button type="primary" @click="exportExcel">导出</el-button>
  <div>
    <el-table :data="datas"
              border style="width: 100%; margin-top: 20px">
      <el-table-column prop="buName" label="事业部"/>
      <el-table-column prop="recordFactoryName" label="备案工厂"/>
      <el-table-column prop="factoryName" label="工厂"/>
      <el-table-column prop="departmentName" label="部门"/>
      <el-table-column prop="name" label="姓名"/>
      <el-table-column prop="employeeId" label="工号"/>
      <el-table-column prop="gradeName" label="级别"/>
      <el-table-column prop="genderName" label="性别"/>
      <el-table-column prop="educationName" label="学历"/>
      <el-table-column prop="auditorLevelName" label="审核员级别"/>
      <el-table-column prop="email" label="邮箱"/>
      <el-table-column prop="phone" label="手机"/>
      <el-table-column prop="registrationNumber" label="注册编号"/>
      <el-table-column prop="locationName" label="工作地点"/>
      <el-table-column prop="technology" label="工艺类别"/>
      <el-table-column prop="workStatusName" label="任职状态"/>
      <el-table-column prop="departTime" label="离职日期"/>
    </el-table>
  </div>
</template>
