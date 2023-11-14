<script setup>
import {useRouter} from 'vue-router'
import {ref} from "vue";

import {getAllFactoryContactService} from '@/api/contact'
import {useBaseDataStore} from '@/stores'

const router=useRouter()
const baseDataStore=useBaseDataStore()

const contactList=ref([])
const buDictOptions=baseDataStore.buList
const recordFactoryOptions=baseDataStore.recordFactoryList

const add=()=>{
  router.push('/contact/factoryContact/add')
}

const query=async ()=>{
  contactList.value=[]
  const res=await getAllFactoryContactService()
  let data=res.data.data;
  for (let i in data){
    data[i].isEdit=false
  }
  contactList.value=data
  console.log(data)

}
</script>

<template>
  <h1>工厂体系接口人</h1>
  <el-button type="primary" @click="query">检索</el-button>
  <el-button type="primary" @click="add">添加</el-button>
  <el-divider/>
  <el-table :data="contactList" border style="width: 100%;" align="center">
    <el-table-column label="操作">
        <el-button type="primary">编辑</el-button>
        <el-button type="danger">删除</el-button>
    </el-table-column>
    <el-table-column label="事业部">
      <template #default="{row}">
        <el-select id="buSelect" v-model="row.buId" value-key="dictValue">
          <el-option
              v-for="item in buDictOptions"
              :key="item.dictValue"
              :label="item.dictName+'('+item.dictValue+')'"
              :value="item.dictValue"
          />
        </el-select>
      </template>
    </el-table-column>
    <el-table-column label="备案工厂">
      <template #default="{row}">
        <el-select v-model="row.recordFactoryId" value-key="dictValue">
          <el-option
              v-for="item in recordFactoryOptions"
              :key="item.dictValue"
              :label="item.dictName+'('+item.dictValue+')'"
              :value="item.dictValue"
          />
        </el-select>
      </template>
    </el-table-column>
    <el-table-column label="体系接口人">
      <template #default="{row}">
        {{row.employeeName}}({{row.employeeId}})
      </template>
    </el-table-column>
    <el-table-column label="电话">
      <template #default="{row}">
        <el-input v-model="row.phone" placeholder="请输入电话号码" />
      </template>
    </el-table-column>
    <el-table-column label="邮箱">
      <template #default="{row}">
        <el-input v-model="row.email" placeholder="请输入邮箱"/>
      </template>
    </el-table-column>
    <el-table-column label="创建人">
      <template #default="{row}">
        {{row.createUserName}}({{row.createUserId}})
      </template>
    </el-table-column>
    <el-table-column label="创建时间" prop="createTime"/>
    <el-table-column label="修改人">
      <template #default="{row}">
        {{row.updateUserName}}({{row.updateUserId}})
      </template>
    </el-table-column>
    <el-table-column label="修改时间" prop="updateTime"/>
  </el-table>
</template>
