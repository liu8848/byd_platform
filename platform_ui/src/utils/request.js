import axios from 'axios'
import {useUserStore} from '@/stores'
import { ElMessage } from 'element-plus'

const baseURL='http://localhost:8020/api'

const instance=axios.create({
    baseURL,
    timeout:10000,
    headers:{
        // "Access-Control-Allow-Method":"POST,GET",
        'Access-Control-Allow-Origin': '*'
    }
})

instance.interceptors.request.use(
    (config)=>{
        const useStore=useUserStore()
        if(useStore.token){
            config.headers.set('token',useStore.token)
        }
        return config
    },
    (err)=>Promise.reject(err)
)

instance.interceptors.response.use(
    (res)=>{
        if(res.data.code==1){
            return res
        }
        console.error(res.data.msg);
        return Promise.reject(res.data.msg)
    },
    (err)=>{
        ElMessage.error(err.response.data.msg)
        return Promise.reject(err)
    }
)

export default instance
export {baseURL}