<script setup>
</script>

<template>
  <div style="text-align: center;">
    修改审核员
  </div>
  <!-- <div style="margin-top: 20px;">
      <el-form :model=employee :inline="true">
          <el-form-item label="工号:">
              <el-text class="mx-1" size="large">{{  }}</el-text>
          </el-form-item>
          <el-divider content-position="center">基本信息</el-divider>
          <el-row>
              <el-form-item label="姓名">
                  <el-text class="mx-1" size="large">{{ employee.employeeName.value }}</el-text>
              </el-form-item>
              <el-form-item label="事业部">
                  <el-text class="mx-1" size="large">{{ employee.buName.value }}</el-text>
              </el-form-item>
              <el-form-item label="工厂">
                  <el-text class="mx-1" size="large">{{ employee.factoryName.value }}</el-text>
              </el-form-item>
              <el-form-item label="部门">
                  <el-text class="mx-1" size="large">{{ employee.departmentName.value }}</el-text>
              </el-form-item>
              <el-form-item label="工作地点">
                  <el-text class="mx-1" size="large">{{ employee.locationName.value }}</el-text>
              </el-form-item>
          </el-row>
          <el-row>
              <el-form-item label="等级">
                  <el-text class="mx-1" size="large">{{ employee.grade.value }}</el-text>
              </el-form-item>
              <el-form-item label="性别">
                  <el-text class="mx-1" size="large">{{ employee.gender.value }}</el-text>
              </el-form-item>
              <el-form-item label="邮箱">
                  <el-text class="mx-1" size="large">{{ employee.email.value }}</el-text>
              </el-form-item>
          </el-row>

          <el-divider content-position="center">添加信息</el-divider>
          <el-row>
              <el-form-item label="备案工厂：">
                  <el-select v-model="factoryValue"
                      value-key="dictValue" placeholder="选择备案工厂">
                      <el-option
                          v-for="item in factoryOptions"
                          :key="item.dictValue"
                          :label="item.dictName+'('+item.dictValue+')'"
                          :value="item" />
                  </el-select>
              </el-form-item>
              <el-form-item label="学历:">
                  <el-select v-model="educationValue"
                              value-key="dictValue" placeholder="选择学历">
                      <el-option
                          v-for="item in educationOptions"
                          :key="item.dictValue"
                          :label="item.dictName+'('+item.dictValue+')'"
                          :value="item"/>
                  </el-select>
              </el-form-item>
              <el-form-item label="审核员级别:">
                  <el-select v-model="auditorLevelValue"
                              value-key="dictValue" placeholder="选择学历">
                      <el-option
                          v-for="item in auditorLevelOptions"
                          :key="item.dictValue"
                          :label="item.dictName+'('+item.dictValue+')'"
                          :value="item"/>
                  </el-select>
              </el-form-item>
          </el-row>
          <el-form-item label="手机号">
              <el-input v-model="auditorCreateModel.phone"/>
          </el-form-item>
          <el-form-item label="注册编号">
              <el-input v-model="auditorCreateModel.registrationNumber"/>
          </el-form-item>
          <el-form-item label="工艺类别">
              <el-select
                  v-model="technologyValue"
                  value-key="dictValue"
                  multiple
                  placeholder="请选择工艺类别"
                  style="width: 500px;">
                  <el-option
                      v-for="item in technologyOptions"
                      :key="item.dictValue"
                      :label="item.dictName+'('+item.dictValue+')'"
                      :value="item"/>
              </el-select>
          </el-form-item>
          <el-row justify="center">
              <el-button type="primary" @click="saveAuditor">
                  保存
              </el-button>
          </el-row>
      </el-form>
  </div> -->
</template>