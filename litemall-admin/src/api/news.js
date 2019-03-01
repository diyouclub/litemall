import request from '@/utils/request'

export function newsTypeList(query) { // 资讯分类列表
  return request({
    url: '/infoClass/list',
    method: 'get',
    params: query
  })
}
export function listNews(query) {
  return request({
    url: '/tabInfo/list',
    method: 'get',
    params: query
  })
}

export function createNews(data) {
  return request({
    url: '/tabInfo/create',
    method: 'post',
    data
  })
}

export function updateNews(data) {
  return request({
    url: '/tabInfo/update',
    method: 'post',
    data
  })
}

export function deleteNews(data) {
  return request({
    url: '/tabInfo/delete',
    method: 'post',
    data
  })
}

export function createNewsType(data) { // 添加资讯分类
  return request({
    url: '/infoClass/create',
    method: 'post',
    data
  })
}
export function getNewsType(data) { // 获取资讯分类
  return request({
    url: '/infoClass/list',
    method: 'get',
    params: data
  })
}
export function redNewsType(data) { // 资讯分类编辑显示
  return request({
    url: '/infoClass/read',
    method: 'get',
    params: data
  })
}
export function updateNewsType(data) { // 修改资讯分类
  return request({
    url: '/infoClass/update',
    method: 'post',
    data
  })
}
export function deleteNewsType(data) { // 删除资讯分类
  return request({
    url: '/infoClass/delete',
    method: 'post',
    data
  })
}
