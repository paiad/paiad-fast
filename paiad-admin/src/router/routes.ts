//对外暴露的路由
export const constantRoute = [
  {
    path: '/login',
    component: () => import('@/views/login/index.vue'),
    name: 'login',
    meta: {
      title: '登录',
      isShow: false,
      icon: 'skill-icons:webflow'
    }
  },
  //登录成功，展示数据的路由
  {
    path: '/',
    component: () => import('@/layout/index.vue'),
    name: 'layout',
    meta: {
      title: '',
      isShow: true,
      icon: 'iconoir:layout-right'
    },
    redirect: '/home',
    children: [
      {
        path: '/home',
        component: () => import('@/views/home/index.vue'),
        meta: {
          title: '首页',
          isShow: true,
          icon: 'solar:home-smile-linear'
        }
      }
      // {
      //   path: '/test',
      //   component: () => import('@/views/home/index.vue'),
      //   meta: {
      //     title: '测试',
      //     isShow: true,
      //     icon: 'hugeicons:test-tube-01'
      //   }
      // }
    ]
  },
  {
    path: '/404',
    component: () => import('@/views/404/index.vue'),
    name: '404',
    meta: {
      title: '404',
      isShow: false
    }
  },
]

//异步路由
export const asyncRoute = [
  //acl
  {
    path: '/acl',
    component: () => import('@/layout/index.vue'),
    name: 'Acl',
    meta: {
      title: '权限管理',
      isShow: true,
      icon: 'hugeicons:lock'
    },
    redirect: '/acl/user',
    children: [
      {
        path: '/acl/role',
        component: () => import('@/views/acl/role/index.vue'),
        name: 'Role',
        meta: {
          title: '角色管理',
          isShow: true,
          icon: 'ix:roles'
        }
      },
      {
        path: '/acl/admin',
        component: () => import('@/views/acl/admin/index.vue'),
        name: 'admin',
        meta: {
          title: '管理员',
          isShow: true,
          icon: 'ri:admin-line',
          roles:['admin']
        }
      },   {
        path: '/acl/user',
        component: () => import('@/views/acl/user/index.vue'),
        name: 'user',
        meta: {
          title: '普通用户',
          isShow: true,
          icon: 'lets-icons:user',
        }
      },
      {
        path: '/acl/permission',
        component: () => import('@/views/acl/permission/index.vue'),
        name: 'Permission',
        meta: {
          title: '菜单管理',
          isShow: true,
          icon: 'icon-park-outline:permissions'
        }
      }]
  },

  //product
  {
    path: '/product',
    component: () => import('@/layout/index.vue'),
    name: 'Product',
    meta: {
      title: '商品管理',
      isShow: true,
      icon: 'ep:goods',
      roles: ['admin']
    },
    redirect: '/product/trademark',
    children: [
      {
        path: '/product/trademark',
        component: () => import('@/views/product/trademark/index.vue'),
        name: 'Trademark',
        meta: {
          title: '品牌管理',
          icon: 'tabler:brand-codecov',
          isShow: true,
        },
      },
      {
        path: '/product/attr',
        component: () => import('@/views/product/attr/index.vue'),
        name: 'Attr',
        meta: {
          title: '属性管理',
          icon: 'lsicon:setting-outline',
          isShow: true,
        },
      },
      {
        path: '/product/spu',
        component: () => import('@/views/product/spu/index.vue'),
        name: 'Spu',
        meta: {
          title: 'Spu',
          icon: 'lsicon:goods-outline',
          isShow: true,
        },
      },
      {
        path: '/product/sku',
        component: () => import('@/views/product/sku/index.vue'),
        name: 'Sku',
        meta: {
          title: 'Sku',
          icon: 'solar:bag-5-outline',
          isShow: true,
        },
      },
    ],
  },
]

export const coreRoute = [
  //blog
  {
    path: '/',
    component: () => import('@/layout/index.vue'),
    name: 'Blog',
    meta: {
      title: '',
      isShow: true,
      icon: 'mdi:blog'
    },
    children: [
      {
        path: '/paiad',
        component: () => import('@/views/blog/index.vue'),
        name: 'BlogList',
        meta: {
          title: '博客园',
          isShow: true,
          icon: 'tdesign:broccoli'
        }
      }
    ]
  },

  //test
  {
    path: '/',
    component: () => import('@/layout/index.vue'),
    name: 'Test',
    meta: {
      title: '',
      isShow: true,
      icon: ''
    },
    children: [
      {
        path: '/test',
        component: () => import('@/views/test/index.vue'),
        name: '测试',
        meta: {
          title: '测试',
          isShow: true,
          icon: 'hugeicons:test-tube-01',
        },
      }
    ]
  },
]
//任意路由
export const anyRoute= [
  {
    path: '/:pathMatch(.*)*',
    redirect: '/404',
    name: 'Any',
    meta: {
      title: '任意路由',
      isShow: false
    }
  },
]
