// 封装 localstorage 数据与读取数据
export const SET_TOKEN = (token: string) => {
  localStorage.setItem('paiad-token', token)
}

export const GET_TOKEN = () => {
  return localStorage.getItem('paiad-token')
}

export const REMOVE_TOKEN = () => {
  localStorage.removeItem('paiad-token')
}
