/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : test1

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 11/04/2021 09:29:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单路径',
  `flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '用户管理', '用户管理', 'user.html', 'user');
INSERT INTO `t_permission` VALUES (2, '角色管理', '角色管理', 'role.html', 'role');
INSERT INTO `t_permission` VALUES (3, '权限管理', '权限管理', 'permission.html', 'permission');
INSERT INTO `t_permission` VALUES (4, '插件管理', '插件管理', 'plugins.html', 'plugins');
INSERT INTO `t_permission` VALUES (18, '聊天室', '聊天室', 'im.html', 'im');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `permission` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限列表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '超级管理员', '所有权限', '[{\"id\":1,\"name\":\"用户管理\",\"path\":\"user.html\",\"description\":null,\"flag\":\"user\"},{\"id\":2,\"name\":\"角色管理\",\"path\":\"role.html\",\"description\":null,\"flag\":\"role\"},{\"id\":3,\"name\":\"权限管理\",\"path\":\"permission.html\",\"description\":null,\"flag\":\"permission\"},{\"id\":4,\"name\":\"插件管理\",\"path\":\"plugins.html\",\"description\":\"插件管理\",\"flag\":\"plugins\"},{\"id\":18,\"name\":\"聊天室\",\"path\":\"im.html\",\"description\":\"聊天室\",\"flag\":\"im\"}]');
INSERT INTO `t_role` VALUES (2, '普通用户', '部分权限', '[{\"id\":4,\"name\":\"插件管理\",\"path\":\"plugins.html\",\"description\":\"插件管理\",\"flag\":\"plugins\"},{\"id\":18,\"name\":\"聊天室\",\"path\":\"im.html\",\"description\":\"聊天室\",\"flag\":\"im\"}]');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限',
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '部门',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'admin', '111124444', '13978786565', '1616271650817', '[{\"id\":1,\"name\":\"超级管理员\",\"description\":null,\"permission\":null}]', NULL, NULL);
INSERT INTO `t_user` VALUES (11, 'tom', '123456', 'tom@qq.com', '13685249632', '1616271650817', '[{\"id\":2,\"name\":\"普通用户\",\"description\":\"部分权限\",\"permission\":[{\"id\":4,\"name\":\"插件管理\",\"path\":\"/page/end/plugins.html\",\"description\":\"插件管理\",\"flag\":\"plugins\"},{\"id\":5,\"name\":\"聊天室\",\"path\":\"/page/end/im.html\",\"description\":\"聊天室\",\"flag\":\"im\"}]}]', NULL, NULL);
INSERT INTO `t_user` VALUES (16, 'jack', '123456', NULL, NULL, '1616271628207', '[{\"id\":2,\"name\":\"仓库管理员\",\"description\":null,\"permission\":[{\"id\":2,\"name\":\"仓库管理\",\"path\":\"/page/end/cangku.html\",\"description\":\"仓库管理\"}]}]', NULL, NULL);
INSERT INTO `t_user` VALUES (20, 'jerry', '123456', 'jerry@qq.com', '13698597854', '1616284768677', '[{\"id\":2,\"name\":\"仓库管理员\",\"description\":null,\"permission\":[{\"id\":4,\"name\":\"部门管理\",\"path\":\"/page/end/department.html\",\"description\":\"部门管理\"},{\"id\":8,\"name\":\"聊天室\",\"path\":\"/page/end/im.html\",\"description\":\"聊天室\"}]}]', NULL, NULL);
INSERT INTO `t_user` VALUES (21, 'hello', '123456', 'hello@qq.com', '13695285412', '1615969390812', '[{\"id\":2,\"name\":\"普通用户\",\"description\":\"部分权限\",\"permission\":[{\"id\":4,\"name\":\"插件管理\",\"path\":\"/page/end/plugins.html\",\"description\":\"插件管理\",\"flag\":\"plugins\"},{\"id\":5,\"name\":\"聊天室\",\"path\":\"/page/end/im.html\",\"description\":\"聊天室\",\"flag\":\"im\"}]}]', NULL, NULL);
INSERT INTO `t_user` VALUES (23, 'yes', '123456', NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
