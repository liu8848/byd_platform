import request from '@/utils/request'

export const getRecordFactoryService = () => {
    return request.get('/dict/factory')
}

export const getTechnologyDictService = () => {
    return request.get('/dict/technology')
}

export const getBuListService=()=>{
    return request.get('/dict/buList')
}