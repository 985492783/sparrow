import request from "@/utils/request";

export function getLogList(data) {
  return request({
    url: '/v1/log/query',
    method: 'post',
    data: data
  })
}
