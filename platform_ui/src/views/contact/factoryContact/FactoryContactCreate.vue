<script setup lang="ts">
import {ref} from 'vue'
import {useRouter} from 'vue-router'

import {getEmployeeInfoService} from'@/api/auditor'
import {useBaseDataStore} from '@/stores'
import {addFactoryContactService} from '@/api/contact'

const router=useRouter()
const baseData=useBaseDataStore()
const buListOptions=baseData.buList
const factoryListOptions=baseData.recordFactoryList



const createDTO=ref({
  buId:null,
  recordFactoryId:null,
  employeeId:null
})

const employeeInfo=ref({
  employeeName:null,
  phone:null,
  email:null
})

const getEmployeeInfo = async (employeeId) => {
  const res=await getEmployeeInfoService(employeeId)
  const data=res.data.data
  console.log(data)
  employeeInfo.value.employeeName=data.employeeName
  employeeInfo.value.phone=data.phone
  employeeInfo.value.email=data.email
}

const save= async (createDTO)=>{
  const res=await addFactoryContactService(createDTO)
  console.log(res)
  await router.push('/contact/factoryContact')
}
</script>

<template>
  <h1>添加工厂体系接口人</h1>
  <el-form>
    <el-row>
      <el-form-item label="接口人工号：">
        <el-input v-model="createDTO.employeeId"
                  placeholder="输入要添加的接口人工号" style="width: 200px"
                  @change="getEmployeeInfo(createDTO.employeeId)"
        />
      </el-form-item>
    </el-row>
    <el-row>
      <el-form-item label="姓名">
        <el-text>{{employeeInfo.employeeName}}</el-text>
      </el-form-item>
      <el-form-item label="邮箱:" style="margin-left: 50px">
        <el-input v-model="employeeInfo.email"/>
      </el-form-item>
      <el-form-item label="手机:" style="margin-left: 50px">
        <el-input v-model="employeeInfo.phone"/>
      </el-form-item>
    </el-row>

    <el-row>
      <el-form-item label="事业部">
        <el-select v-model="createDTO.buId"
                   value-key="dictValue"
                   placeholder="选择事业部"
        >
          <el-option
              v-for="item in buListOptions"
              :key="item.dictValue"
              :label="item.dictName+'('+item.dictValue+')'"
              :value="item.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="备案工厂">
        <el-select v-model="createDTO.recordFactoryId"
                   value-key="dictValue"
                   placeholder="选择备案工厂"
        >
          <el-option
              v-for="item in factoryListOptions"
              :key="item.dictValue"
              :label="item.dictName+'('+item.dictValue+')'"
              :value="item.dictValue"
          />
        </el-select>
      </el-form-item>
    </el-row>
    <el-button type="primary" @click="save(createDTO)">保存</el-button>
  </el-form>
</template>