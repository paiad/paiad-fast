/*
 Navicat Premium Data Transfer

 Source Server         : localhost-docker
 Source Server Type    : MySQL
 Source Server Version : 90200 (9.2.0)
 Source Host           : localhost:3306
 Source Schema         : paiad-fast

 Target Server Type    : MySQL
 Target Server Version : 90200 (9.2.0)
 File Encoding         : 65001

 Date: 21/05/2025 15:01:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mqtt_user
-- ----------------------------
DROP TABLE IF EXISTS `mqtt_user`;
CREATE TABLE `mqtt_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_superuser` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mqtt_user
-- ----------------------------
INSERT INTO `mqtt_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', NULL, 0);

-- ----------------------------
-- Table structure for paiad_user
-- ----------------------------
DROP TABLE IF EXISTS `paiad_user`;
CREATE TABLE `paiad_user`  (
  `id` bigint NOT NULL COMMENT '用户ID，唯一标识',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名，唯一',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码（加密存储）',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色，例如：admin, user',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限列表，逗号分隔，例如：user:read,user:write',
  `ip_address` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后登录IP地址，支持IPv4和IPv6',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态，1：启用，0：禁用',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别，0：未知，1：男，2：女',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像URL',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paiad_user
-- ----------------------------
INSERT INTO `paiad_user` VALUES (1745938111579, 'admin', '$2a$10$8JPwCNMM8jhi3Tbf83L.i./UwFMX4q0A1AhUetAIHTuLV8kj0G1u6', 'admin@163.com', '18888888888', 'admin', 'user:update,user:get,user:delete,user:add', '0:0:0:0:0:0:0:1', '2025-05-21 14:54:39', 1, 1, 'Admin', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2025-04-29 14:48:31', '2025-05-21 06:54:38');
INSERT INTO `paiad_user` VALUES (1745938083118, 'paiad', '$2a$10$zSD.jcT3tj4EZb.nW9z1Gu3DjglWAjRCtX08KKNiEsfMPAgepkCya', 'paiad@qq.com', '16666666666', 'user', 'user:update,user:get', '0:0:0:0:0:0:0:1', '2025-05-21 14:27:47', 1, 1, 'Paiad', '	https://gravatar.com/avatar/19310963b4cc243140a076f54843d91c', '2025-04-29 14:48:03', '2025-05-21 06:27:47');
INSERT INTO `paiad_user` VALUES (1925060505236541440, 'test-user', '$2a$10$TQLNxw6KfjTYLXPCipkZpOWKc5tj00.nz624OFiudXHNAg4Ne77ny', NULL, NULL, 'user', 'user:get', '0:0:0:0:0:0:0:1', '2025-05-21 13:57:39', 1, 0, 'Test', '	https://gravatar.com/avatar/19310963b4cc243140a076f54843d91c', '2025-05-21 05:26:05', '2025-05-21 05:57:38');

SET FOREIGN_KEY_CHECKS = 1;
