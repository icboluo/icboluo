/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 127.0.0.1:3306
 Source Schema         : game

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 15/03/2022 02:08:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cultivation_career
-- ----------------------------
DROP TABLE IF EXISTS `cultivation_career`;
CREATE TABLE `cultivation_career`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `player_id` int NOT NULL COMMENT '玩家id',
  `oper` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '修仙生涯' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cultivation_career
-- ----------------------------
INSERT INTO `cultivation_career` VALUES (1, 15, 'Games start');
INSERT INTO `cultivation_career` VALUES (2, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (3, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (4, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (5, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (6, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (7, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (8, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (9, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (10, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (11, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (12, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (13, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (14, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (15, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (16, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (17, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (18, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (19, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (20, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (21, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (22, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (23, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (24, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (25, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (26, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (27, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (28, 15, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (29, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (30, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (31, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (32, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (33, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (34, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (35, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (36, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (37, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (38, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (39, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (40, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (41, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (42, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (43, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (44, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (45, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (46, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (47, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (48, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (49, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (50, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (51, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (52, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (53, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (54, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (55, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (56, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (57, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (58, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (59, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (60, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (61, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (62, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (63, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (64, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (65, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (66, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (67, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (68, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (69, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (70, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (71, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (72, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (73, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (74, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (75, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (76, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (77, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (78, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (79, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (80, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (81, 15, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (82, 15, 'you are 100 years old, you are die');
INSERT INTO `cultivation_career` VALUES (83, 16, 'Games start');
INSERT INTO `cultivation_career` VALUES (84, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (85, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (86, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (87, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (88, 16, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (89, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (90, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (91, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (92, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (93, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (94, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (95, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (96, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (97, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (98, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (99, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (100, 16, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (101, 16, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (102, 16, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (103, 16, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (104, 16, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (105, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (106, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (107, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (108, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (109, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (110, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (111, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (112, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (113, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (114, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (115, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (116, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (117, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (118, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (119, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (120, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (121, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (122, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (123, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (124, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (125, 16, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (126, 16, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (127, 16, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (128, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (129, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (130, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (131, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (132, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (133, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (134, 16, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (135, 16, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (136, 16, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (137, 16, 'you killed a monster, and you upgraded');
INSERT INTO `cultivation_career` VALUES (138, 16, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (139, 16, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (140, 16, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (141, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (142, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (143, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (144, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (145, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (146, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (147, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (148, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (149, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (150, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (151, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (152, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (153, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (154, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (155, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (156, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (157, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (158, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (159, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (160, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (161, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (162, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (163, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (164, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (165, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (166, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (167, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (168, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (169, 16, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (170, 16, 'you are 100 years old, you are die');
INSERT INTO `cultivation_career` VALUES (171, 17, 'Games start');
INSERT INTO `cultivation_career` VALUES (172, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (173, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (174, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (175, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (176, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (177, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (178, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (179, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (180, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (181, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (182, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (183, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (184, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (185, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (186, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (187, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (188, 17, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (189, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (190, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (191, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (192, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (193, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (194, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (195, 17, 'time flies, another year has passed');

-- ----------------------------
-- Table structure for die_player
-- ----------------------------
DROP TABLE IF EXISTS `die_player`;
CREATE TABLE `die_player`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `age` int UNSIGNED NOT NULL COMMENT '年龄',
  `attack` int UNSIGNED NOT NULL COMMENT '攻击',
  `blood` int NOT NULL COMMENT '血量',
  `max_blood` int UNSIGNED NOT NULL COMMENT '最大血量',
  `experience` int UNSIGNED NOT NULL COMMENT '经验',
  `total_experience` int UNSIGNED NOT NULL COMMENT '总经验',
  `level` int UNSIGNED NOT NULL COMMENT '级别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '玩家' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of die_player
-- ----------------------------
INSERT INTO `die_player` VALUES (1, 183, 10, 200, 200, 1, 1, 3);
INSERT INTO `die_player` VALUES (2, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (3, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (4, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (5, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (6, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (7, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (8, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (9, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (10, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (11, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (12, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (13, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (14, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (15, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (16, 100, 10, 200, 200, 1, 1, 2);

-- ----------------------------
-- Table structure for monster
-- ----------------------------
DROP TABLE IF EXISTS `monster`;
CREATE TABLE `monster`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `attack` int UNSIGNED NULL DEFAULT NULL COMMENT '攻击',
  `blood` int NULL DEFAULT NULL COMMENT '血量',
  `max_blood` int UNSIGNED NULL DEFAULT NULL COMMENT '最大血量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '玩家' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of monster
-- ----------------------------
INSERT INTO `monster` VALUES (25, 4, 20, 30);
INSERT INTO `monster` VALUES (32, 6, 28, 28);
INSERT INTO `monster` VALUES (33, 6, 12, 22);
INSERT INTO `monster` VALUES (34, 7, 14, 24);
INSERT INTO `monster` VALUES (35, 6, 24, 24);
INSERT INTO `monster` VALUES (36, 7, 15, 15);
INSERT INTO `monster` VALUES (37, 6, 27, 27);

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `age` int UNSIGNED NOT NULL COMMENT '年龄',
  `attack` int UNSIGNED NOT NULL COMMENT '攻击',
  `blood` int NOT NULL COMMENT '血量',
  `max_blood` int UNSIGNED NOT NULL COMMENT '最大血量',
  `experience` int UNSIGNED NOT NULL COMMENT '经验',
  `total_experience` int UNSIGNED NOT NULL COMMENT '总经验',
  `level` int UNSIGNED NOT NULL COMMENT '级别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '玩家' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of player
-- ----------------------------
INSERT INTO `player` VALUES (17, 44, 10, 200, 200, 0, 0, 1);

-- ----------------------------
-- Table structure for player_level
-- ----------------------------
DROP TABLE IF EXISTS `player_level`;
CREATE TABLE `player_level`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '级别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of player_level
-- ----------------------------
INSERT INTO `player_level` VALUES (1, '练气期');
INSERT INTO `player_level` VALUES (2, '筑基期');
INSERT INTO `player_level` VALUES (3, '金丹期');
INSERT INTO `player_level` VALUES (4, '元婴期');

SET FOREIGN_KEY_CHECKS = 1;
