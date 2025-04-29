# paiad-fast 后端框架

`paiad-fast` 是一个基于 Spring Boot 3.x + Sa-Token 构建的快速开发后台框架，支持权限认证、用户登录、权限控制、接口文档、Redis 缓存等功能，适用于中小型管理系统或平台的后端服务开发。

---

## 🌟 项目特色

- ✅ 基于 [Sa-Token](https://sa-token.cc/) 实现轻量级登录认证与权限控制
- ✅ 支持 JWT Token 鉴权机制
- ✅ 集成 Redis 缓存，支持分布式 Session 管理
- ✅ 使用 MyBatis-Plus 快速操作数据库
- ✅ 集成 Knife4j 接口文档，便于接口调试和对接
- ✅ 支持用户注册、登录、退出、获取用户信息等常用接口

---

## 🛠️ 技术栈

| 技术       | 版本       | 描述               |
|------------|------------|--------------------|
| Spring Boot | 3.x        | 主体框架            |
| Sa-Token   | 1.34+      | 权限认证框架         |
| MyBatis-Plus | 3.5+      | ORM框架            |
| Redis      | 6.0+       | 缓存存储            |
| Knife4j    | 3.x        | 接口文档            |
| Lombok     | 最新       | 简化 Java 代码       |
| Maven      | 3.8+       | 项目构建管理工具      |

---


## 🚀 快速启动

1. 克隆项目：

   ```bash
   git clone https://your.git.repo/paiad-fast.git
   cd paiad-fast
   ```

2. 配置数据库连接（`application.yml`）：
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/your_db
       username: root
       password: your_password
   ```

3. 启动 Redis 本地服务（默认端口 6379）
    ```yaml
    data:
      redis:
        database: 0
        host: localhost
        port: 6379
        password: your_password
    ```
4. 启动项目(paiad-server)：
   >[!important]
   >Run application

5. 打开接口文档：
   ```
   http://localhost:9090/api/doc.html
   ```
