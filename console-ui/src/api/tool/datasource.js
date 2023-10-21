import request from '@/utils/request'

export function getAllDatasource() {
  return request({
    url: '/tool/datasource/getDataSources',
    method: 'get'
  })
}

export function getSchemas(id) {
  return request({
    url: '/tool/datasource/getSchemas?id=' + id,
    method: 'get'
  })
}

export function getDbList(data) {
  return request({
    url: '/tool/datasource/list',
    method: 'post',
    data: data
  })
}

// 查询数据源列表
export function pageDatasource(data) {
  return request({
    url: '/tool/datasource/page',
    method: 'post',
    data: data
  })
}

// 单体查询数据源详细
export function getDatasource(id) {
  return request({
    url: '/tool/datasource/getById?id=' + id,
    method: 'get'
  })
}

// 新增数据源
export function saveDatasource(data) {
  return request({
    url: '/tool/datasource/save',
    method: 'post',
    data: data
  })
}

// 修改数据源
export function updateDatasource(data) {
  return request({
    url: '/tool/datasource/update',
    method: 'post',
    data: data
  })
}

// 删除数据源
export function delDatasource(id) {
  return request({
    url: '/tool/datasource/deleteById?id=' + id,
    method: 'delete'
  })
}
