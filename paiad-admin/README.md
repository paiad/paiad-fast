# Paiad-Admin 前端项目文档

## 1. 项目概述
Paiad-Admin是一个基于Vue 3的后台管理系统前端项目。

## 2. 技术栈
- Vue 3
- Vue Router
- TypeScript
- 布局组件：`@/layout/index.vue`

## 3. 项目结构
```
paiad-admin/
├── src/
│   ├── layout/          # 布局组件
│   ├── router/          # 路由配置
│   ├── views/           # 页面组件
│   └── ...
```

## 4. 路由系统

### 4.1 路由配置
路由配置位于 `src/router/routes.ts`，主要包含：
- `constantRoute`: 基础路由配置
- `testRoute`: 测试路由配置

### 4.2 路由显示规则
1. 在Main组件中显示的路由：
    - 必须使用 `@/layout/index.vue` 作为父组件
    - `meta.isShow` 设置为 `true`
    - 例如：首页、权限管理、商品管理等

2. 独立页面路由：
    - 直接使用页面组件
    - `meta.isShow` 设置为 `false`
    - 例如：登录页、404页面

### 4.3 主要路由模块
1. 首页模块 (`/home`)
2. 权限管理模块 (`/acl`)
    - 用户管理
    - 角色管理
    - 菜单管理
3. 商品管理模块 (`/product`)
    - 品牌管理
    - 属性管理
    - Spu管理
    - Sku管理
4. 测试模块 (`/test`)

## 5. 布局系统

### 5.1 布局组件
- 主布局：`@/layout/index.vue`
- 包含：顶部导航、侧边菜单、Main内容区域

### 5.2 显示规则
- 需要显示在主导航中的页面：使用布局组件作为父组件
- 独立页面：直接使用页面组件

## 6. 开发指南

### 6.1 添加新路由
1. 在 `routes.ts` 中添加路由配置
2. 确保在 `router/index.ts` 中正确引入
3. 如果需要在Main中显示，使用布局组件作为父组件

### 6.2 路由配置示例
```typescript
{
    path: '/your-path',
    component: () => import('@/layout/index.vue'),
    name: 'YourName',
    meta: {
      title: '页面标题',
      isShow: true,
      icon: 'your-icon'
    },
    children: [
      {
        path: '',
        component: () => import('@/views/your-page/index.vue'),
        name: 'YourPage',
        meta: {
          title: '子页面标题',
          isShow: true,
          icon: 'your-icon'
        }
      }
    ]
}
```

### 6.3 注意事项
1. 路由必须正确注册才能显示
2. 使用布局组件时注意配置子路由
3. 图标使用统一的图标系统
4. 保持路由命名规范

## 7. 常见问题

### 7.1 路由不显示
- 检查路由是否正确注册
- 确认 `meta.isShow` 设置
- 验证父组件配置

### 7.2 页面显示位置
- 在Main中显示：使用布局组件作为父组件
- 独立页面：直接使用页面组件

## 8. 最佳实践
1. 保持路由结构清晰
2. 合理使用布局组件
3. 统一的路由命名规范
4. 适当的图标选择
5. 清晰的代码注释
