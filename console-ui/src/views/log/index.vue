<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true">
      <el-form-item label="设备id" prop="deviceId">
        <el-select clearable @click.native="getInstance" v-model="queryParams.model.projectId" filterable
                   placeholder="请选择">
          <el-option
              class="option-box"
              v-for="item in serverList"
              :key="item.id"
              :label="item.displayName + ' (' + item.id + ')'"
              :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="pattern" prop="pattern">
        <el-input
            v-model="queryParams.model.pattern"
            placeholder="请输入pattern"
            clearable
            size="small"
        />
      </el-form-item>
      <el-form-item label="time" prop="time">
        <el-date-picker
            v-model="queryParams.model.time"
            type="datetimerange"
            :picker-options="pickerOptions"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>


      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="query">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini">重置</el-button>
      </el-form-item>
    </el-form>
    <div style="margin-bottom: 10px;font-weight: bold">
      日志采集
    </div>
    <el-table v-loading="loading" :data="logList" :show-header="false">
      <el-table-column label="logger" align="left" prop="logger">
        <template slot-scope="scope">
          <div v-html="scope.row.logger"></div>
        </template>
      </el-table-column>
    </el-table>
    <pagination
        style="background-color: #E9EEF3"
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="query"
    />
  </div>
</template>

<script>
import {getInstances} from "@/api/instance/instance";
import {getLogList} from "@/api/log/log";

export default {
  name: "logGather",
  data() {
    return {
      loading: false,
      logList: [],
      pickerOptions: {
        shortcuts: [{
          text: '最近5分钟',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 60 * 5 * 1000);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近10分钟',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 60 * 10 * 1000);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近30分钟',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 60 * 30 * 1000);
            picker.$emit('pick', [start, end]);
          }
        },{
          text: '最近3小时',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 60 * 60 * 3 * 1000);
            picker.$emit('pick', [start, end]);
          }
        },{
          text: '最近一天',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 60 * 60 * 24 * 1000);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      total: 1,
      serverList: [],
      queryParams: {
        pageSize: 10,
        pageNum: 1,
        model: {}
      }
    }
  },
  created() {
  },
  methods: {
    getInstance() {
      getInstances({key: 'logEnabled', value: 'true'}).then((res) => {
        if (res.code === 200) {
          this.serverList = res.data
        }
      })
    },
    query() {
      let data = this.queryParams
      this.loading = true
      if (data.model.time !== undefined && data.model.time !== null) {
        data.model.startTime = data.model.time[0].valueOf()
        data.model.endTime = data.model.time[1].valueOf()
      } else {
        data.model.startTime = null
        data.model.endTime = null
      }
      getLogList(data).then((res) => {
        this.total = res.total
        this.logList = res.data
        if (data.model.pattern !== undefined && data.model.pattern !== null&& data.model.pattern !== '') {
          this.logList.forEach((e) => {
            let regex = new RegExp("(" + data.model.pattern + ")", "g")
            e.logger = e.logger.replace(regex, "<span style='color: red'>$1</span>")
          })
        }
        this.loading = false
      })
    }
  }
}
</script>

<style scoped>
.option-box {
}
</style>
