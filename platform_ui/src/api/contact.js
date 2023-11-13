import request from '@/utils/request'

export const addFactoryContactService=(createDTO)=>{
    return request.post('/contact/factoryContact/add',createDTO)
}