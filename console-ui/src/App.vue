<template>
  <el-container>
    <el-header>
      <div class="sp-header-left">
        <img src="./assets/logo.png" class="sp-logo"/>
      </div>
      <div class="sp-header-right">
        <a class="github" href="https://github.com/985492783/sparrow" title="https://github.com/985492783/sparrow"
           target="_blank"><img src="./assets/github.png" class="github"/></a>
      </div>
    </el-header>
    <el-container>
      <el-aside width="200px" style="padding: 0">
        <h1 class="nav-title">Sparrow {{ version }}</h1>
        <el-menu router :default-active="this.$route.path">
          <el-menu-item index="/">
            <i class="el-icon-setting"></i>
            <span slot="title">实例管理</span>
          </el-menu-item>
          <el-menu-item index="/logGather">
            <i class="el-icon-date"></i>
            <span slot="title">日志采集</span>
          </el-menu-item>
          <el-menu-item index="/threadPoolAgent">
            <i class="el-icon-first-aid-kit"></i>
            <span slot="title">线程池织入</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import {getState} from "@/api/console/console";

export default {
  data() {
    return {
      version: ''
    }
  },
  components: {},
  created() {
    this.getSte()
  },
  methods: {
    async getSte() {
      let data = await getState()
      this.version = data.version
    }
  },
  metaInfo() {
    return {
      title: 'sparrow',
      titleTemplate: title => {
        return 'sparrow'
      }
    }
  }
}
</script>
<style>
body {
  margin: 0;
  height: 100%;
}

.el-container {
  height: 100%;
}

.el-header {
  background-color: #252a2f;
  color: #333;
  text-align: center;
  line-height: 60px;
  height: 60px !important;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  height: 100%;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  height: 100%;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.sp-header-left {
  display: flex;
  align-items: center;
  float: left;
  height: 100%;
}

.sp-header-right {
  float: right;
  display: flex;
  align-items: center;
  height: 100%;
}

.sp-logo {
  height: 45px;
  width: 130px;
  margin: auto;
}

.github {
  height: 35px;
  width: 35px;
  margin: auto;
}

.nav-title {
  margin: 0;
  text-align: center;
  font-size: 14px;
  font-weight: bold;
  height: 50px;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  border-bottom: 1px solid #252a2f;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
