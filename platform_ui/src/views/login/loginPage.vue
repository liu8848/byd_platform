<script setup>
import{ref} from 'vue'
import{useRouter} from 'vue-router'

import{useUserStore,useBaseDataStore} from '@/stores'
import {userLoginService} from '@/api/user'
import {getRecordFactoryService,getTechnologyDictService} from '@/api/basedata'

const router=useRouter()
const userStore=useUserStore()
const baseDataStore=useBaseDataStore()

const formModel=ref({
    username:'',
    password:''
})

const login=async()=>{
    console.log('登录')
    const res=await userLoginService(formModel.value)
    userStore.setToken(res.data.data.token,res.data.data.username)
    
    const factoryDict=await getRecordFactoryService()
    const technologyDict=await getTechnologyDictService()
    baseDataStore.setRecordFactoryList(factoryDict.data.data)
    baseDataStore.setTechnologyList(technologyDict.data.data)
    console.log('开始登陆...',res)
    router.push('/')
}
</script>

<template>
    <el-form :model="formModel">
    </el-form>
    <el-row>
        <el-col :span="24">
            <div style="text-align: center; font-size: 30px; margin-bottom: 50px;">用户登录</div>
        </el-col>
    </el-row>
    <el-row style="margin: 10px;">
        <el-col :span="6">
            <div style="text-align: right;font-size: large;">账号：</div>
        </el-col>
        <el-col :span="12">
            <el-form-item>
                <el-input v-model="formModel.username"/>
            </el-form-item>
        </el-col>
    </el-row>
    <el-row style="margin: 10px;">
        <el-col :span="6">
            <div style="text-align: right;font-size: large;">密码：</div>
        </el-col>
        <el-col :span="12">
            <el-el-form-item>
                <el-input v-model="formModel.password"/>
            </el-el-form-item>
        </el-col>
    </el-row>
    <el-row style="margin: 10px;">
        <div style="align-content: center;">
            <el-button type="primary" 
            style="margin-left: 400px;margin-top: 50px;"
            @click="login">登录</el-button>
        </div>
    </el-row>

</template>