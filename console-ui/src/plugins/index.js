import modal from './modal'

export default {
  install(Vue) {
    // 页签操作
    // 模态框对象
    Vue.prototype.$modal = modal
    // 下载文件
    Vue.prototype.$download = download
  }
}
