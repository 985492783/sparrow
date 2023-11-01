import request from "@/utils/request";

export function getInstances() {
  return request({
    url: '/v1/instance/query',
    method: 'get'
  })
}
