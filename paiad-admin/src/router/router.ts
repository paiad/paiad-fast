//对外暴露的路由
export const constantRoute = [
  {
    path: '/login',
    component: () => import('@/views/login/index.vue'),
    name: 'login',
    meta: {
      title: 'login',
      isShow: false,
    },
  },
  //登录成功，展示数据的路由
  {
    path: '/',
    component: () => import('@/layout/index.vue'),
    name: 'layout',
    meta: {
      title: 'Layout',
      isShow: true,
      icon: '',
    },
    redirect: '/home',
    children: [
      {
        path: '/home',
        component: () => import('@/views/home/index.vue'),
        meta: {
          title: '首页',
          isShow: true,
          icon: 'HomeFilled',
        },
      },
      {
        path: '/test',
        component: () => import('@/views/home/index.vue'),
        meta: {
          title: '测试',
          isShow: true,
          icon: 'HomeFilled',
        },
      },
    ],
  },
  {
    path: '/404',
    component: () => import('@/views/404/index.vue'),
    name: '404',
    meta: {
      title: '404',
      isShow: false,
    },
  },
  //任意路由，若都不匹配，会跳转到404
  {
    path: '/:pathMatch(.*)*',
    redirect: '/404',
    name: 'Any',
    meta: {
      title: '任意路由',
      isShow: false,
    },
  }
]
