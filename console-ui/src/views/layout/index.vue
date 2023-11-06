<template>
  <div>
    <el-card class="box-card" v-for="item in this.list" shadow="hover">
      <div slot="header" class="clearfix">
        <span>{{item.displayName}}</span>
        <el-button style="float: right; padding: 3px 0" type="text">修改</el-button>
      </div>
      <div class="text item">
        ip: {{item.ip}}
      </div>
      <div class="text item">
        serverName: {{item.serverName}}
      </div>
      <div v-for="(value, item) in item.params" class="text item">
        {{item}}: <span :class="value==='true'? 'enable-open': 'enable-close'">{{value}}</span>
      </div>
    </el-card>
  </div>
</template>

<script>
import {getInstances} from "@/api/instance/instance";
export default {
  name: "index",
  components: {

  },
  data() {
    return {
      list: []
    }
  },
  created() {
    this.getInstance()
  },
  methods: {
    getInstance () {
      getInstances().then((res) => {
        if (res.code === 200) {
          this.list = res.data
        }
      })
    }
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 400px;
}
.enable-open {
  color: #0bb70b;
}

.enable-close {
  color: red;
}
</style>
