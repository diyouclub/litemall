import request from '@/utils/request'

export function listCommission(query) {
  return request({
    url: '/commission/list',
    method: 'get',
    params: query
  })
}

export function createCommission(data) {
  return request({
    url: '/commission/create',
    method: 'post',
    data
  })
}

export function readCommission(data) {
  return request({
    url: '/commission/read',
    method: 'get',
    data
  })
}

export function updateCommission(data) {
  return request({
    url: '/commission/update',
    method: 'post',
    data
  })
}

export function deleteCommission(data) {
  return request({
    url: '/commission/delete',
    method: 'post',
    data
  })
}
