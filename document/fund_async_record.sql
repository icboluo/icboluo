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

 Date: 08/06/2021 01:52:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fund_async_record
-- ----------------------------
DROP TABLE IF EXISTS `fund_async_record`;
CREATE TABLE `fund_async_record`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `start_time` datetime NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fund_async_record
-- ----------------------------
INSERT INTO `fund_async_record` VALUES ('001186', '2021-01-01 00:00:00', '2021-06-08 00:00:00');
INSERT INTO `fund_async_record` VALUES ('001230', '2021-01-01 00:00:00', '2021-06-08 00:00:00');
INSERT INTO `fund_async_record` VALUES ('161032', '2021-01-01 00:00:00', '2021-06-08 00:00:00');
INSERT INTO `fund_async_record` VALUES ('161725', '2021-01-01 00:00:00', '2021-06-08 00:00:00');
INSERT INTO `fund_async_record` VALUES ('164402', '2021-01-01 00:00:00', '2021-06-08 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
