<template>
  <div>
    <el-form :model="form" inline>
      <el-form-item label="星级匹配情况">
        <el-select v-model="form.levelMatch"
                   value-key="dictValue">
          <el-option
              v-for="item in levelMatchOptions"
              :key="item.dictValue"
              :label="item.dictName+'('+item.dictValue+')'"
              :value="item.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="星级">
        <el-select v-model="form.level"
                   value-key="dictValue">
          <el-option
              v-for="item in levelOption"
              :key="item.dictValue"
              :label="item.dictName+'('+item.dictValue+')'"
              :value="item.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="事业部">
        <el-select v-model="form.buId"
                   value-key="dictValue">
          <el-option
              v-for="item in buOptions"
              :key="item.dictValue"
              :label="item.dictName+'('+item.dictValue+')'"
              :value="item.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="备案工厂">
        <el-select v-model="form.recordFactory"
                   value-key="dictValue">
          <el-option
              v-for="item in factoryOptions"
              :key="item.dictValue"
              :label="item.dictName+'('+item.dictValue+')'"
              :value="item.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="form.employeeName"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="query">查询</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" @click="add">添加</el-button>
    <el-divider/>
  </div>
  <div>
    <el-table :data="auditors" :span-method="objectSpanMethod" border style="width: 100%; margin-top: 20px">
      <el-table-column prop="levelMatch" label="星级匹配情况"/>
      <el-table-column prop="warnTime" label="预警开始时间"/>
      <el-table-column prop="level" label="星级"/>
      <el-table-column prop="SA" label="SA"/>
      <el-table-column prop="A" label="A"/>
      <el-table-column prop="PA" label="PA"/>
      <el-table-column prop="buName" label="事业部"/>
      <el-table-column prop="recordFactoryName" label="备案工厂"/>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary" @click="editAuditor(scope.row.employeeId)">编辑</el-button>
          <p/>
          <el-button type="danger">删除</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="factoryName" label="工厂"/>
      <el-table-column prop="departmentName" label="部门"/>
      <el-table-column prop="employeeName" label="姓名"/>
      <el-table-column prop="employeeId" label="工号"/>
      <el-table-column prop="grade" label="级别"/>
      <el-table-column prop="gender" label="性别"/>
      <el-table-column prop="education" label="学历"/>
      <el-table-column prop="auditorLevel" label="审核员级别"/>
      <el-table-column prop="email" label="邮箱"/>
      <el-table-column prop="phone" label="手机"/>
      <el-table-column prop="registrationNumber" label="注册编号"/>
      <el-table-column prop="locationName" label="工作地点"/>
      <el-table-column prop="technologyName" label="工艺类别"/>
      <el-table-column label="优先安排">
        <template v-slot="scope">
          <el-switch
              inline-prompt
              active-text="是"
              inactive-text="否"
              v-model="scope.row.arrangement"
          />
        </template>
      </el-table-column>
      <el-table-column prop="status" label="在职状态"/>
      <el-table-column prop="type" label="类型"/>
    </el-table>
  </div>
  <div class="demo-pagination-block">
    <el-pagination
        v-model:current-page="form.page"
        v-model:page-size="form.size"
        :page-sizes="[2, 10, 50,100]"
        :small="false"
        :disabled="false"
        :background="false"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="query"
        @current-change="query"
    />
  </div>
</template>

<script lang="ts" setup>
import {ref,reactive} from 'vue'

import {useRouter} from 'vue-router'
import {standingBookListService,getStandingBookInWorkPageAndQuery} from '@/api/auditor'
import {TableColumnCtx} from 'element-plus';
import { useBaseDataStore } from '@/stores'

const router = useRouter()
const baseDataStore=useBaseDataStore()
const levelMatchOptions=baseDataStore.levelMatchDict
const levelOption=baseDataStore.levelDict
const buOptions=baseDataStore.buList
const factoryOptions=baseDataStore.recordFactoryList
const total=ref(0)

class Auditor {
  levelMatch: string
  warnTime: any
  level: string
  SA: number
  A: number
  PA: number
  buName: string
  recordFactoryName: string
  departmentName: string
  employeeName: string
  employeeId: number
  factoryName: any;
  grade: any;
  gender: any;
  education: any;
  auditorLevel: any;
  email: any;
  phone: any;
  registrationNumber: any;
  locationName: any;
  technologyName: string;
  arrangement: boolean
  status: string;
  type: string;
  hidden:string;
}

const form=reactive({
  levelMatch:null,
  level:null,
  buId:null,
  recordFactory:null,
  employeeName:'',
  auditorLevel:null,
  page:0,
  size:10
})

interface SpanMethodProps {
  row: Auditor
  column: TableColumnCtx<Auditor>
  rowIndex: number
  columnIndex: number
}

const add = () => {
  router.push('/auditorStandingBook/addAuditor')
}

const editAuditor = (employeeId) => {
  console.log(employeeId)
  router.push({
    name: 'editAuditor',
    params: {
      a: 1123456
    }
  })
}

let cellList: any[] = []
let count: number = 0

const computeCell = (auditorList: any[]) => {
  console.log('run...')
  cellList = []
  count = 0
  for (let i = 0; i < auditorList.length; i++) {
    if (i == 0) {
      cellList.push(1)
      count = 0
    } else {
      if (auditorList[i].recordFactoryName === auditorList[i - 1].recordFactoryName) {
        cellList[count] += 1;
        cellList.push(0)
      } else {
        cellList.push(1);
        count = i
      }
    }
  }
}

const objectSpanMethod = ({
                            rowIndex,
                            columnIndex,
                          }: SpanMethodProps) => {
  computeCell(auditors.value)
  if (columnIndex < 8) {
    const fRow = cellList[rowIndex]
    const fCol = fRow > 0 ? 1 : 0
    return {
      rowspan: fRow,
      colspan: fCol
    }
  }
}

const standingBookList = ref([])
const auditors = ref([])


const query = async () => {
  console.log('开始查询。。。')
  standingBookList.value = []
  auditors.value = []
  // const res = await standingBookListService()
  const res=await getStandingBookInWorkPageAndQuery(form)
  let result = JSON.parse(JSON.stringify(res.data.data))
  let objs=result.records
  total.value=result.total
  console.log(objs)
  for (let i in objs) {
    standingBookList.value.push(objs[i])
    let p = objs[i]['auditors']
    for (let j in p) {
      let au = new Auditor();
      au.levelMatch = objs[i]['levelMatchName']
      au.warnTime = objs[i]['warnTime']
      au.level = objs[i]['levelName']
      au.SA = objs[i]['sa']
      au.A = objs[i]['a']
      au.PA = objs[i]['pa']
      au.buName = objs[i]['buName']
      au.recordFactoryName = objs[i]['recordFactoryName']
      au.factoryName = p[j]['factoryName']
      au.departmentName = p[j]['departmentName']
      au.employeeName = p[j]['employeeName']
      au.employeeId = p[j]['employeeId']
      au.grade = p[j]['grade']
      au.gender = p[j]['gender']
      au.education = p[j]['education']
      au.auditorLevel = p[j]['auditorLevel']
      au.email = p[j]['email']
      au.phone = p[j]['phone']
      au.registrationNumber = p[j]['registrationNumber']
      au.locationName = p[j]['locationName']
      au.arrangement = p[j].arrangement
      au.technologyName = p[j].technologyName
      au.status = p[j].status
      au.type = p[j].type
      au.hidden=p[j].auditorLevel=='SA'?'hidden':'visible'
      auditors.value.push(au)
    }
  }
  console.log(auditors)

}
</script>