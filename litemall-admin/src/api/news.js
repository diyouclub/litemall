import request from '@/utils/request'

export function seleNews(query) {
  return request({
    url: '/codeTree/getCodeTree',
    method: 'get',
    params: {
      code_type: 'news_type'
    }
  })
}
export function listNews(query) {
  return request({
    url: '/news/list',
    method: 'get',
    params: query
  })
}

export function createNews(data) {
  return request({
    url: '/news/create',
    method: 'post',
    data
  })
}

export function readNews(data) {
  return request({
    url: '/news/read',
    method: 'get',
    data
  })
}

export function updateNews(data) {
  return request({
    url: '/news/update',
    method: 'post',
    data
  })
}

export function deleteNews(data) {
  return request({
    url: '/news/delete',
    method: 'post',
    data
  })
}
