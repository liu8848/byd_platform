<script setup lang="ts">
import {ref} from 'vue'
import {useRouter} from 'vue-router'
import {standingBookChangeListService} from '@/api/auditor'
import { useBaseDataStore } from '@/stores'

const baseDataStore=useBaseDataStore()

const router=useRouter()
let changeList=ref([])

type Option = {
  dictId: string
  dictName: string
  dictValue: number
}

class standingBookChange{
  nowBuName:string
  oldBuName:string
  recordFactory:ref<Option>
  nowFactoryName:string
  oldFactoryName:string
  departmentName:string
  name:string
  employeeId:number
  gradeName:string
  genderName:string
  educationName:string
  auditorLevelName:string
  email:string
  phone:string
  registrationNumber:string
  locationName:string
  technology:string
  workStatusName:string
}

const factoryValue=ref<Option>()
const factoryOptions=baseDataStore.recordFactoryList
const query=async()=>{
  console.log('查询开始......')
  const res=await standingBookChangeListService()
  const data=res.data.data
  changeList.value=[]
  for (let i in data){
    let t=new standingBookChange()
    t.nowBuName=data[i]['nowBuName']
    t.oldBuName=data[i]['oldBuName']
    t.recordFactory={
      dictId: data[i]['recordFactoryId'],
      dictName: data[i]['recordFactoryName'],
      dictValue: data[i]['recordFactoryId']
    }
    t.nowFactoryName=data[i]['nowFactoryName']
    t.oldFactoryName=data[i]['oldFactoryName']
    t.departmentName=data[i]['departmentName']
    t.name=data[i]['name']
    t.employeeId=data[i]['employeeId']
    t.gradeName=data[i]['gradeName']
    t.genderName=data[i]['genderName']
    t.educationName=data[i]['educationName']
    t.auditorLevelName=data[i]['auditorLevelName']
    t.email=data[i]['email']
    t.phone=data[i]['phone']
    t.registrationNumber=data[i]['registrationNumber']
    t.locationName=data[i]['locationName']
    t.technology=data[i]['technology']
    t.workStatusName=data[i]['workStatusName']

    changeList.value.push(t)
  }
  console.log(changeList)
}


</script>

<template>
  <h1>事业部变动审核员台账</h1>
  <el-button type="primary" @click="query">查询</el-button>
  <div>
    <el-table :data="changeList"
        border style="width: 100%; margin-top: 20px">
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary">确定</el-button>
          <el-button type="danger">删除</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="nowBuName" label="现事业部"/>
      <el-table-column prop="oldBuName" label="原事业部"/>
      <el-table-column label="备案工厂">
        <template v-slot="scope">
          <el-select v-model="scope.row.recordFactory"
                     value-key="dictValue">
            <el-option
                v-for="item in factoryOptions"
                :key="item.dictValue"
                :label="item.dictName+'('+item.dictValue+')'"
                :value="item"
            />
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="nowFactoryName" label="现工厂"/>
      <el-table-column prop="oldFactoryName" label="原工厂"/>
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
    </el-table>
  </div>
</template>
