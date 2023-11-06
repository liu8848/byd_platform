import {defineStore} from 'pinia'
import{ref} from 'vue'


export const useUserStore=defineStore('big-user',()=>{
    const username=ref('default')
    const token=ref('default')
    const setToken=(newToken,name)=>{
        token.value=newToken
        username.value=name
    }
    const removeToken=()=>{
        token.value=''
        username.value=''
    }
    return{
        username,
        token,
        setToken,
        removeToken
    }
},{
    persist:true
})



