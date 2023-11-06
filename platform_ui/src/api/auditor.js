import request from '@/utils/request'

export const standingBookListService = () => {
    return request.get('/auditor/list')
}

export const getEmployeeInfoService = (id) => {
    return request.get('/employee/' + id)
}

export const addAuditorService = (auditorCreateModel) => {
    console.log('开始添加审核员')
    console.log(auditorCreateModel)
    return request.post('/auditor/add', auditorCreateModel)
}