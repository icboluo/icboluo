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

 Date: 16/03/2022 00:44:26
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
INSERT INTO `cultivation_career` VALUES (196, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (197, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (198, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (199, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (200, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (201, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (202, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (203, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (204, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (205, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (206, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (207, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (208, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (209, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (210, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (211, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (212, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (213, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (214, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (215, 18, 'Games start');
INSERT INTO `cultivation_career` VALUES (216, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (217, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (218, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (219, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (220, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (221, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (222, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (223, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (224, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (225, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (226, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (227, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (228, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (229, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (230, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (231, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (232, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (233, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (234, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (235, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (236, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (237, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (238, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (239, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (240, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (241, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (242, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (243, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (244, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (245, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (246, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (247, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (248, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (249, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (250, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (251, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (252, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (253, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (254, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (255, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (256, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (257, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (258, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (259, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (260, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (261, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (262, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (263, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (264, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (265, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (266, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (267, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (268, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (269, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (270, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (271, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (272, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (273, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (274, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (275, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (276, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (277, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (278, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (279, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (280, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (281, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (282, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (283, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (284, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (285, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (286, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (287, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (288, 17, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (289, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (290, 17, 'you are 100 years old, you are die');
INSERT INTO `cultivation_career` VALUES (291, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (292, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (293, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (294, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (295, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (296, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (297, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (298, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (299, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (300, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (301, 18, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (302, 18, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (303, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (304, 18, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (305, 18, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (306, 18, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (307, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (308, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (309, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (310, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (311, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (312, 18, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (313, 18, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (314, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (315, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (316, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (317, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (318, 19, 'Games start');
INSERT INTO `cultivation_career` VALUES (319, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (320, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (321, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (322, 19, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (323, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (324, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (325, 19, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (326, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (327, 19, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (328, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (329, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (330, 19, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (331, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (332, 19, 'you killed a monster, and you upgraded');
INSERT INTO `cultivation_career` VALUES (333, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (334, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (335, 19, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (336, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (337, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (338, 19, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (339, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (340, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (341, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (342, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (343, 19, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (344, 19, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (345, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (346, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (347, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (348, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (349, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (350, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (351, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (352, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (353, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (354, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (355, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (356, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (357, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (358, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (359, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (360, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (361, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (362, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (363, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (364, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (365, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (366, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (367, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (368, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (369, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (370, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (371, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (372, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (373, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (374, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (375, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (376, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (377, 18, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (378, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (379, 18, 'you are 100 years old, you are die');
INSERT INTO `cultivation_career` VALUES (380, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (381, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (382, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (383, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (384, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (385, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (386, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (387, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (388, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (389, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (390, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (391, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (392, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (393, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (394, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (395, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (396, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (397, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (398, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (399, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (400, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (401, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (402, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (403, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (404, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (405, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (406, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (407, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (408, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (409, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (410, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (411, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (412, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (413, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (414, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (415, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (416, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (417, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (418, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (419, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (420, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (421, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (422, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (423, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (424, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (425, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (426, 19, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (427, 19, 'you are 100 years old, you are die');
INSERT INTO `cultivation_career` VALUES (428, 20, 'Games start');
INSERT INTO `cultivation_career` VALUES (429, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (430, 20, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (431, 20, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (432, 20, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (433, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (434, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (435, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (436, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (437, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (438, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (439, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (440, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (441, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (442, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (443, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (444, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (445, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (446, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (447, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (448, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (449, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (450, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (451, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (452, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (453, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (454, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (455, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (456, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (457, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (458, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (459, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (460, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (461, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (462, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (463, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (464, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (465, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (466, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (467, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (468, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (469, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (470, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (471, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (472, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (473, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (474, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (475, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (476, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (477, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (478, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (479, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (480, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (481, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (482, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (483, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (484, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (485, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (486, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (487, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (488, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (489, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (490, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (491, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (492, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (493, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (494, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (495, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (496, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (497, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (498, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (499, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (500, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (501, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (502, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (503, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (504, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (505, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (506, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (507, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (508, 20, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (509, 20, 'you are 100 years old, you are die');
INSERT INTO `cultivation_career` VALUES (510, 21, 'Games start');
INSERT INTO `cultivation_career` VALUES (511, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (512, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (513, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (514, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (515, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (516, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (517, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (518, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (519, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (520, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (521, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (522, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (523, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (524, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (525, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (526, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (527, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (528, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (529, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (530, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (531, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (532, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (533, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (534, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (535, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (536, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (537, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (538, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (539, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (540, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (541, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (542, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (543, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (544, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (545, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (546, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (547, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (548, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (549, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (550, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (551, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (552, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (553, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (554, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (555, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (556, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (557, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (558, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (559, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (560, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (561, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (562, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (563, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (564, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (565, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (566, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (567, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (568, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (569, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (570, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (571, 22, 'Games start');
INSERT INTO `cultivation_career` VALUES (572, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (573, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (574, 22, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (575, 22, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (576, 22, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (577, 22, 'you killed a monster, and you upgraded');
INSERT INTO `cultivation_career` VALUES (578, 22, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (579, 22, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (580, 22, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (581, 22, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (582, 22, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (583, 22, 'you killed a monster, and you upgraded');
INSERT INTO `cultivation_career` VALUES (584, 22, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (585, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (586, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (587, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (588, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (589, 22, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (590, 22, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (591, 22, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (592, 22, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (593, 22, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (594, 22, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (595, 22, 'you killed a monster, and you upgraded');
INSERT INTO `cultivation_career` VALUES (596, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (597, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (598, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (599, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (600, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (601, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (602, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (603, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (604, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (605, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (606, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (607, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (608, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (609, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (610, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (611, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (612, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (613, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (614, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (615, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (616, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (617, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (618, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (619, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (620, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (621, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (622, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (623, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (624, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (625, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (626, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (627, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (628, 21, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (629, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (630, 21, 'you are 100 years old, you are die');
INSERT INTO `cultivation_career` VALUES (631, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (632, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (633, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (634, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (635, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (636, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (637, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (638, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (639, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (640, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (641, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (642, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (643, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (644, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (645, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (646, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (647, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (648, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (649, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (650, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (651, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (652, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (653, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (654, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (655, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (656, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (657, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (658, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (659, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (660, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (661, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (662, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (663, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (664, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (665, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (666, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (667, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (668, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (669, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (670, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (671, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (672, 22, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (673, 22, 'you are 100 years old, you are die');
INSERT INTO `cultivation_career` VALUES (674, 23, 'Games start');
INSERT INTO `cultivation_career` VALUES (675, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (676, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (677, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (678, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (679, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (680, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (681, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (682, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (683, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (684, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (685, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (686, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (687, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (688, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (689, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (690, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (691, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (692, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (693, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (694, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (695, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (696, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (697, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (698, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (699, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (700, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (701, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (702, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (703, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (704, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (705, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (706, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (707, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (708, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (709, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (710, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (711, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (712, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (713, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (714, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (715, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (716, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (717, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (718, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (719, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (720, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (721, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (722, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (723, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (724, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (725, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (726, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (727, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (728, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (729, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (730, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (731, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (732, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (733, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (734, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (735, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (736, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (737, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (738, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (739, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (740, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (741, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (742, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (743, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (744, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (745, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (746, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (747, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (748, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (749, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (750, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (751, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (752, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (753, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (754, 23, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (755, 23, 'you are 100 years old, you are die');
INSERT INTO `cultivation_career` VALUES (756, 24, 'Games start');
INSERT INTO `cultivation_career` VALUES (757, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (758, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (759, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (760, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (761, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (762, 24, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (763, 24, 'you killed a monster, and you upgraded');
INSERT INTO `cultivation_career` VALUES (764, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (765, 24, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (766, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (767, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (768, 24, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (769, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (770, 24, 'you killed a monster, and you upgraded');
INSERT INTO `cultivation_career` VALUES (771, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (772, 24, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (773, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (774, 24, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (775, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (776, 24, 'you killed a monster');
INSERT INTO `cultivation_career` VALUES (777, 24, 'You spend 1 year attacking a monster');
INSERT INTO `cultivation_career` VALUES (778, 24, 'you killed a monster, and you upgraded');
INSERT INTO `cultivation_career` VALUES (779, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (780, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (781, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (782, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (783, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (784, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (785, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (786, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (787, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (788, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (789, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (790, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (791, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (792, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (793, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (794, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (795, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (796, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (797, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (798, 24, 'time flies, another year has passed');
INSERT INTO `cultivation_career` VALUES (799, 24, 'time flies, another year has passed');

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
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '玩家' ROW_FORMAT = Dynamic;

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
INSERT INTO `die_player` VALUES (17, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (18, 100, 10, 200, 200, 3, 3, 1);
INSERT INTO `die_player` VALUES (19, 100, 10, 200, 200, 3, 3, 2);
INSERT INTO `die_player` VALUES (20, 100, 10, 200, 200, 1, 1, 1);
INSERT INTO `die_player` VALUES (21, 100, 10, 200, 200, 0, 0, 1);
INSERT INTO `die_player` VALUES (22, 100, 13, 230, 230, 0, 9, 4);
INSERT INTO `die_player` VALUES (23, 100, 10, 200, 200, 0, 0, 1);

-- ----------------------------
-- Table structure for monster
-- ----------------------------
DROP TABLE IF EXISTS `monster`;
CREATE TABLE `monster`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `attack` int UNSIGNED NOT NULL COMMENT '攻击',
  `blood` int NOT NULL COMMENT '血量',
  `max_blood` int UNSIGNED NOT NULL COMMENT '最大血量',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '玩家' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of monster
-- ----------------------------
INSERT INTO `monster` VALUES (52, 7, 26, 26, '温童彤');

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
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '玩家' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of player
-- ----------------------------
INSERT INTO `player` VALUES (24, 63, 13, 230, 230, 0, 9, 4, '可正真');

-- ----------------------------
-- Table structure for player_level
-- ----------------------------
DROP TABLE IF EXISTS `player_level`;
CREATE TABLE `player_level`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '级别',
  `experience` int UNSIGNED NOT NULL COMMENT '经验',
  `increase_attack` int UNSIGNED NOT NULL COMMENT '增加攻击',
  `increase_blood` int UNSIGNED NOT NULL COMMENT '增加血量',
  `age` int UNSIGNED NOT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of player_level
-- ----------------------------
INSERT INTO `player_level` VALUES (1, '练气期', 2, 1, 8, 80);
INSERT INTO `player_level` VALUES (2, '筑基期', 3, 1, 10, 100);
INSERT INTO `player_level` VALUES (3, '金丹期', 4, 1, 12, 120);
INSERT INTO `player_level` VALUES (4, '元婴期', 5, 1, 14, 140);

SET FOREIGN_KEY_CHECKS = 1;
