<script setup lang="ts">
import {ref} from 'vue'
import {addAuditorService, getEmployeeInfoService} from '@/api/auditor.js'
import {useBaseDataStore} from '@/stores'
import {ElButton} from 'element-plus';

const baseDataStore = useBaseDataStore()

type Option = {
  dictId: string
  dictName: string
  dictValue: number
}

type AuditorLevelOption = {
  dictId: string
  dictName: string
  dictValue: string
}

class AuditorCreateModel {
  employeeId: number
  recordFactoryId: number
  education: number
  auditorLevel: string
  phone: string
  registrationNumber: string
  technology: string
}

const auditorCreateModel = ref(new AuditorCreateModel())


const factoryValue = ref<Option>()
const factoryOptions = baseDataStore.recordFactoryList

const educationValue = ref<Option>()
const educationOptions = baseDataStore.educationDict

const auditorLevelValue = ref<AuditorLevelOption>()
const auditorLevelOptions = baseDataStore.auditorLevelDict

const technologyValue = ref([])
const technologyOptions = baseDataStore.technologyList

const employee = {
  buName: ref(''),
  factoryName: ref(''),
  departmentName: ref(''),
  employeeId: ref(),
  employeeName: ref(''),
  grade: ref(''),
  gender: ref(''),
  email: ref(''),
  locationName: ref('')
}

const getEmployeeInfo = async (employeeId) => {
  const res = await getEmployeeInfoService(employeeId)
  const info = res.data.data;
  console.log(info);
  employee.employeeName.value = info['employeeName']
  employee.buName.value = info['buName']
  employee.factoryName.value = info['factoryName']
  employee.departmentName.value = info['departmentName']
  employee.locationName.value = info['locationName']
  employee.grade.value = info['grade']
  employee.gender.value = info['gender']
  employee.email.value = info['email']
}

const saveAuditor = async () => {
  auditorCreateModel.value.employeeId = employee.employeeId.value
  auditorCreateModel.value.recordFactoryId = factoryValue.value.dictValue
  auditorCreateModel.value.auditorLevel = auditorLevelValue.value.dictValue
  auditorCreateModel.value.education = educationValue.value.dictValue

  let technologyList = technologyValue.value
  let str = ''
  for (let i in technologyList) {
    str += technologyList[i].dictValue + ','
  }
  str = str.slice(0, -1)

  auditorCreateModel.value.technology = str

  const res = await addAuditorService(auditorCreateModel.value)
  console.log(res.data)
}


</script>
<template>
  <div style="text-align: center;">
    添加审核员
  </div>
  <div style="margin-top: 20px;">
    <el-form :model=employee :inline="true">
      <el-form-item label="工号:">
        <el-input v-model="employee.employeeId.value" @change="getEmployeeInfo(employee.employeeId.value)"/>
      </el-form-item>
      <el-divider content-position="center">基本信息</el-divider>
      <el-row>
        <el-form-item label="姓名">
          <el-text class="mx-1" size="large">{{ employee.employeeName.value }}</el-text>
        </el-form-item>
        <el-form-item label="事业部">
          <el-text class="mx-1" size="large">{{ employee.buName.value }}</el-text>
        </el-form-item>
        <el-form-item label="工厂">
          <el-text class="mx-1" size="large">{{ employee.factoryName.value }}</el-text>
        </el-form-item>
        <el-form-item label="部门">
          <el-text class="mx-1" size="large">{{ employee.departmentName.value }}</el-text>
        </el-form-item>
        <el-form-item label="工作地点">
          <el-text class="mx-1" size="large">{{ employee.locationName.value }}</el-text>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="等级">
          <el-text class="mx-1" size="large">{{ employee.grade.value }}</el-text>
        </el-form-item>
        <el-form-item label="性别">
          <el-text class="mx-1" size="large">{{ employee.gender.value }}</el-text>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-text class="mx-1" size="large">{{ employee.email.value }}</el-text>
        </el-form-item>
      </el-row>

      <el-divider content-position="center">添加信息</el-divider>
      <el-row>
        <el-form-item label="备案工厂：">
          <el-select v-model="factoryValue"
                     value-key="dictValue" placeholder="选择备案工厂">
            <el-option
                v-for="item in factoryOptions"
                :key="item.dictValue"
                :label="item.dictName+'('+item.dictValue+')'"
                :value="item"/>
          </el-select>
        </el-form-item>
        <el-form-item label="学历:">
          <el-select v-model="educationValue"
                     value-key="dictValue" placeholder="选择学历">
            <el-option
                v-for="item in educationOptions"
                :key="item.dictValue"
                :label="item.dictName+'('+item.dictValue+')'"
                :value="item"/>
          </el-select>
        </el-form-item>
        <el-form-item label="审核员级别:">
          <el-select v-model="auditorLevelValue"
                     value-key="dictValue" placeholder="选择学历">
            <el-option
                v-for="item in auditorLevelOptions"
                :key="item.dictValue"
                :label="item.dictName+'('+item.dictValue+')'"
                :value="item"/>
          </el-select>
        </el-form-item>
      </el-row>
      <el-form-item label="手机号">
        <el-input v-model="auditorCreateModel.phone"/>
      </el-form-item>
      <el-form-item label="注册编号">
        <el-input v-model="auditorCreateModel.registrationNumber"/>
      </el-form-item>
      <el-form-item label="工艺类别">
        <el-select
            v-model="technologyValue"
            value-key="dictValue"
            multiple
            placeholder="请选择工艺类别"
            style="width: 500px;">
          <el-option
              v-for="item in technologyOptions"
              :key="item.dictValue"
              :label="item.dictName+'('+item.dictValue+')'"
              :value="item"/>
        </el-select>
      </el-form-item>
      <el-row justify="center">
        <el-button type="primary" @click="saveAuditor">
          保存
        </el-button>
      </el-row>
    </el-form>
  </div>
</template>