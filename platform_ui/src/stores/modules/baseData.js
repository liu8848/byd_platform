import {defineStore} from 'pinia'
import {ref} from 'vue'

export const useBaseDataStore = defineStore('baseData', () => {
    const recordFactoryList = ref([])
    const educationDict = ref([
        {
            dictId: 'EDUCATION',
            dictName: '小学',
            dictValue: 0
        },
        {
            dictId: 'EDUCATION',
            dictName: '初中',
            dictValue: 1
        },
        {
            dictId: 'EDUCATION',
            dictName: '高中',
            dictValue: 2
        },
        {
            dictId: 'EDUCATION',
            dictName: '本科',
            dictValue: 3
        },
        {
            dictId: 'EDUCATION',
            dictName: '硕士',
            dictValue: 4
        },
        {
            dictId: 'EDUCATION',
            dictName: '博士',
            dictValue: 5
        }
    ])

    const auditorLevelDict = ref([
        {
            dictId: 'AUDITOR_LEVEL',
            dictName: '资深审核员',
            dictValue: 'SA'
        },
        {
            dictId: 'AUDITOR_LEVEL',
            dictName: '审核员',
            dictValue: 'A'
        },
        {
            dictId: 'AUDITOR_LEVEL',
            dictName: '实习审核员',
            dictValue: 'PA'
        }
    ])

    const technologyList = ref([])

    const setTechnologyList = (list) => {
        console.log('读取工艺类型列表')
        technologyList.value = []
        technologyList.value = list
        console.log(technologyList)
    }

    const setRecordFactoryList = (res) => {
        console.log('开始读取备案工厂列表')
        recordFactoryList.value = []
        for (let i in res) {
            recordFactoryList.value.push(res[i])
        }
        console.log(recordFactoryList)
    }

    const levelMatchDict=ref([
        {
            dictId: 'LEVEL_MATCH',
            dictName: '符合',
            dictValue: 1
        },
        {
            dictId: 'LEVEL_MATCH',
            dictName: '预警',
            dictValue: 0
        },
        {
            dictId: 'LEVEL_MATCH',
            dictName: 'NA',
            dictValue: -1
        }
    ])

    const  levelDict=ref([
        {
            dictId: 'LEVEL',
            dictName: '一星',
            dictValue: 1
        },
        {
            dictId: 'LEVEL',
            dictName: '二星',
            dictValue: 2
        },
        {
            dictId: 'LEVEL',
            dictName: '三星',
            dictValue: 3
        },
        {
            dictId: 'LEVEL',
            dictName: '四星',
            dictValue: 4
        },
        {
            dictId: 'LEVEL',
            dictName: '五星',
            dictValue: 5
        }
    ])

    const buList=ref([])
    const setBuList = (res) => {
        console.log('开始读取事业部列表')
        buList.value = []
        for (let i in res) {
            buList.value.push(res[i])
        }
    }

    return {
        auditorLevelDict,
        educationDict,
        recordFactoryList,
        setRecordFactoryList,
        technologyList,
        setTechnologyList,
        levelMatchDict,
        levelDict,
        buList,
        setBuList
    }
}, {
    persist: true
})