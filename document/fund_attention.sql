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

 Date: 28/05/2021 00:24:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fund_attention
-- ----------------------------
DROP TABLE IF EXISTS `fund_attention`;
CREATE TABLE `fund_attention`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fund_attention
-- ----------------------------
INSERT INTO `fund_attention` VALUES ('001186');
INSERT INTO `fund_attention` VALUES ('001230');
INSERT INTO `fund_attention` VALUES ('161032');
INSERT INTO `fund_attention` VALUES ('161725');
INSERT INTO `fund_attention` VALUES ('164402');

SET FOREIGN_KEY_CHECKS = 1;
