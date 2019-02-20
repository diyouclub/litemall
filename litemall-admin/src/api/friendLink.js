import request from '@/utils/request'

export function createLink(data) {
  return request({
    url: '/friendshipLink/create',
    method: 'post',
    data
  })
}
export function listLink(query) {
  return request({
    url: '/friendshipLink/list',
    method: 'get',
    params: query
  })
}
export function updateLink(data) {
  return request({
    url: '/friendshipLink/update',
    method: 'post',
    data
  })
}

export function deleteLink(data) {
  return request({
    url: '/friendshipLink/delete',
    method: 'post',
    data
  })
}
