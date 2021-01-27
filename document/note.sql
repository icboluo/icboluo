/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 127.0.0.1:3306
 Source Schema         : note

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 27/01/2021 21:58:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for finish
-- ----------------------------
DROP TABLE IF EXISTS `finish`;
CREATE TABLE `finish`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题',
  `result` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结果',
  `belong_to_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属范围',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_first_create` datetime NOT NULL COMMENT '源头数据创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for month_time
-- ----------------------------
DROP TABLE IF EXISTS `month_time`;
CREATE TABLE `month_time`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题',
  `result` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结果',
  `finish_time` int NOT NULL DEFAULT 0 COMMENT '完成次数',
  `belong_to_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属范围',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_first_create` datetime NOT NULL COMMENT '源头数据创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 674 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for only_read
-- ----------------------------
DROP TABLE IF EXISTS `only_read`;
CREATE TABLE `only_read`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题',
  `result` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结果',
  `finish_time` int NOT NULL DEFAULT 0 COMMENT '完成次数',
  `belong_to_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属范围',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1181 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for software_password
-- ----------------------------
DROP TABLE IF EXISTS `software_password`;
CREATE TABLE `software_password`  (
  `id` int NOT NULL COMMENT 'id',
  `software` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '软件名称',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮件',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '软件密码' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for time_note
-- ----------------------------
DROP TABLE IF EXISTS `time_note`;
CREATE TABLE `time_note`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题',
  `result` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结果',
  `finish_time` int NOT NULL DEFAULT 0 COMMENT '完成次数',
  `belong_to_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属范围',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` int NULL DEFAULT NULL COMMENT 'code',
  `gmt_creat` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `name` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '名称',
  `nick_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '密码',
  `tel` int NULL DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for week_time
-- ----------------------------
DROP TABLE IF EXISTS `week_time`;
CREATE TABLE `week_time`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题',
  `result` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结果',
  `finish_time` int NOT NULL DEFAULT 0 COMMENT '完成次数',
  `belong_to_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属范围',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_first_create` datetime NOT NULL COMMENT '源头数据创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 895 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for year_time
-- ----------------------------
DROP TABLE IF EXISTS `year_time`;
CREATE TABLE `year_time`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题',
  `result` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结果',
  `finish_time` int NOT NULL DEFAULT 0 COMMENT '完成次数',
  `belong_to_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属范围',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_first_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '源头数据创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 550 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
