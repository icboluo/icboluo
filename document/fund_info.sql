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

 Date: 09/07/2021 01:13:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fund_info
-- ----------------------------
DROP TABLE IF EXISTS `fund_info`;
CREATE TABLE `fund_info`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fund_info
-- ----------------------------
INSERT INTO `fund_info` VALUES ('001230', '鹏华医药科技股票');
INSERT INTO `fund_info` VALUES ('001302', '前海开源金银珠宝混合A');
INSERT INTO `fund_info` VALUES ('003844', '华夏新能源革新股票');
INSERT INTO `fund_info` VALUES ('004854', '广发中证全指汽车指数A');
INSERT INTO `fund_info` VALUES ('004856', '广发中证全指建筑材料指数A');
INSERT INTO `fund_info` VALUES ('005037', '银华新能源新材料量化优选股票');
INSERT INTO `fund_info` VALUES ('005827', '易方达蓝筹精选混合');
INSERT INTO `fund_info` VALUES ('160221', '国泰有色');
INSERT INTO `fund_info` VALUES ('161032', '煤炭');
INSERT INTO `fund_info` VALUES ('161122', '生物LOF');
INSERT INTO `fund_info` VALUES ('161725', '白酒基金');
INSERT INTO `fund_info` VALUES ('162605', '景顺鼎益');
INSERT INTO `fund_info` VALUES ('164402', '中航军工');
INSERT INTO `fund_info` VALUES ('320007', '诺安成长混合');

SET FOREIGN_KEY_CHECKS = 1;
