import request from '@/utils/request'

request.defaults.baseURL = 'http://120.78.206.205:3000/mock/24/admin'
export function listNotice(params) { // 公告列表
  return request({
    url: '/notice/list',
    method: 'get',
    params
  })
}
export function createNotice(data) {
  return request({
    url: '/notice/create',
    method: 'post',
    data
  })
}
export function updateNotice(data) {
  return request({
    url: '/notice/update',
    method: 'post',
    data
  })
}
export function deleteNotice(params) {
  return request({
    url: '/notice/delete',
    method: 'get',
    params
  })
}
