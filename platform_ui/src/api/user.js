import request from '@/utils/request'

export const userLoginService=({username,password})=>{
    return request.post('/user/login',{username,password})
}
