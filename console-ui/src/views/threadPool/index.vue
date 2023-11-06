<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备id" prop="deviceId">
        <el-input
            v-model="queryParams.model.serverName"
            placeholder="请输入设备id"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {getInstances} from "@/api/instance/instance";

export default {
  name: "index",
  data() {
    return {
      queryParams: {
        model: {

        }
      }
    }
  },
  created() {
    this.getInstance()
  },
  methods: {
    getInstance () {
      getInstances({key: 'executorEnabled', value: 'true'}).then((res) => {
        if (res.code === 200) {
          this.list = res.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
