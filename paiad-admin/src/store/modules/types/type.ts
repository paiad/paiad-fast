import type { RouteRecordRaw } from 'vue-router'

export interface UserState {
  token: string | null
  menuRoutes: RouteRecordRaw[]
  userid: number
  username: string
  nickname: string
  avatar: string
}
