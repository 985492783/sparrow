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
      <el-aside width="200px">Aside</el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>

export default {
  components: {},
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
  text-align: center;
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
</style>
