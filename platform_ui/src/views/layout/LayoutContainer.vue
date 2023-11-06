<template>
    <div class="common-layout">
    <el-container>
      <el-header height="100px">
        <div style="height: 100px; background-color: #337ecc;">
            用户:{{ userStore.username }} <br/>
            token:{{ userStore.token }}
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <AsideComponent/>
        </el-aside>
        <el-main>
            <router-view>

            </router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import AsideComponent from '../../components/AsideComponent.vue'
import{useUserStore} from '@/stores'

const userStore=useUserStore()

</script>