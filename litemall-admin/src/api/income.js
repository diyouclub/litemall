import request from '@/utils/request'

export function listIncome(query) {
  return request({
    url: '/moneyApply/list',
    method: 'get',
    params: query
  })
}

export function listAudit(data) {
  return request({
    url: '/moneyApply/audit',
    method: 'post',
    data
  })
}
export function transfer(data) {
  return request({
    url: '/moneyApply/Transfer',
    method: 'post',
    data
  })
}
