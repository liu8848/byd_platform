import request from '@/utils/request'

export const standingBookListService=()=>{
    return request.get('/auditor/list')
}
export const getStandingBookInWorkPageAndQuery=(form)=>{
    return request({
        url:'/auditor/pageQuery',
        method:'get',
        params:form
    })
}

export const getEmployeeInfoService=(id)=>{
    return request.get('/employee/'+id)
}

export const addAuditorService=(auditorCreateModel)=>{
    console.log('开始添加审核员')
    console.log(auditorCreateModel)
    return request.post('/auditor/add',auditorCreateModel)
}

export const standingBookChangeListService=()=>{
    return request.get('/auditor/auditorStandingBookChange/list')
}

export const standingBookChangeUpdateService=(employeeId,FactoryId)=>{
    const params = new URLSearchParams();
    params.append('recordFactoryId',FactoryId)
    return request.post('/auditor/auditorStandingBookChange/'+employeeId,params)
}

export const standingBookOutWorkListService=()=>{
    return request.get('/auditor/auditorStandingBookOutWork/list')
}

export const standingBookOutWorkExportService=()=>{
    console.log('----------------调用------------------------')
    request.get('/auditor/auditorStandingBookOutWork/list/export')
        .then(
            (data)=>{
                if (!data) return;
                console.log(data)

                let url=window.URL.createObjectURL(data)
                let link = document.createElement('a');
                link.style.display = 'none'
                link.href = url
                link.setAttribute('download', name+ '.xls')//自定义下载的文件的名称
                document.body.appendChild(link)
                link.click()
            }
        )
}
