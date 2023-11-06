import request from "@/utils/request";

export function getInstances(params) {
  return request({
    url: '/v1/instance/query',
    method: 'get',
    params: params
  })
}
