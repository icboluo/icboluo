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

 Date: 28/05/2021 00:24:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fund_data
-- ----------------------------
DROP TABLE IF EXISTS `fund_data`;
CREATE TABLE `fund_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `fund_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '基金id',
  `increase_rate_day` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '日增长率',
  `subscribe_status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申购状态',
  `net_value_date` datetime NULL DEFAULT NULL COMMENT '净值日期',
  `net_asset_value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位净值',
  `net_value_cumulative` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '累计净值',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_(``code``, ``net_value_date``)`(`fund_id`, `net_value_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fund_data
-- ----------------------------
INSERT INTO `fund_data` VALUES (1, '001186', '1.79', '开放申购', '2021-05-27 00:00:00', '2.4410', '2.4410', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (2, '001186', '0.08', '开放申购', '2021-05-26 00:00:00', '2.3980', '2.3980', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (3, '001186', '1.61', '开放申购', '2021-05-25 00:00:00', '2.3960', '2.3960', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (4, '001186', '0.94', '开放申购', '2021-05-24 00:00:00', '2.3580', '2.3580', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (5, '001186', '0.00', '开放申购', '2021-05-21 00:00:00', '2.3360', '2.3360', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (6, '001186', '0.69', '开放申购', '2021-05-20 00:00:00', '2.3360', '2.3360', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (34, '001230', '1.05', '开放申购', '2021-05-27 00:00:00', '1.4470', '1.4470', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (35, '001230', '-0.56', '开放申购', '2021-05-26 00:00:00', '1.4320', '1.4320', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (36, '001230', '2.20', '开放申购', '2021-05-25 00:00:00', '1.4400', '1.4400', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (37, '001230', '-0.56', '开放申购', '2021-05-24 00:00:00', '1.4090', '1.4090', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (38, '001230', '-0.42', '开放申购', '2021-05-21 00:00:00', '1.4170', '1.4170', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (39, '001230', '0.71', '开放申购', '2021-05-20 00:00:00', '1.4230', '1.4230', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (40, '161725', '0.55', '开放申购', '2021-05-27 00:00:00', '1.5593', '3.1904', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (41, '161725', '-0.86', '开放申购', '2021-05-26 00:00:00', '1.5507', '3.1818', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (42, '161725', '3.49', '开放申购', '2021-05-25 00:00:00', '1.5642', '3.1953', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (43, '161725', '2.63', '开放申购', '2021-05-24 00:00:00', '1.5114', '3.1425', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (44, '161725', '0.29', '开放申购', '2021-05-21 00:00:00', '1.4726', '3.1037', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (45, '161725', '2.46', '开放申购', '2021-05-20 00:00:00', '1.4683', '3.0994', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (58, '161032', '1.46', '开放申购', '2021-05-27 00:00:00', '1.2520', '0.8720', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (59, '161032', '0.16', '开放申购', '2021-05-26 00:00:00', '1.2340', '0.8600', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (60, '161032', '1.48', '开放申购', '2021-05-25 00:00:00', '1.2320', '0.8580', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (61, '161032', '-0.33', '开放申购', '2021-05-24 00:00:00', '1.2140', '0.8460', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (62, '161032', '1.58', '开放申购', '2021-05-21 00:00:00', '1.2180', '0.8490', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (63, '161032', '-3.54', '开放申购', '2021-05-20 00:00:00', '1.1990', '0.8350', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (70, '164402', '0.51', '开放申购', '2021-05-27 00:00:00', '0.9780', '0.9780', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (71, '164402', '0.00', '开放申购', '2021-05-26 00:00:00', '0.9730', '0.9730', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (72, '164402', '3.62', '开放申购', '2021-05-25 00:00:00', '0.9730', '0.9730', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (73, '164402', '1.29', '开放申购', '2021-05-24 00:00:00', '0.9390', '0.9390', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (74, '164402', '-2.22', '开放申购', '2021-05-21 00:00:00', '0.9270', '0.9270', '2021-05-28 00:21:00', '2021-05-28 00:21:00');
INSERT INTO `fund_data` VALUES (75, '164402', '0.11', '开放申购', '2021-05-20 00:00:00', '0.9480', '0.9480', '2021-05-28 00:21:00', '2021-05-28 00:21:00');

SET FOREIGN_KEY_CHECKS = 1;
