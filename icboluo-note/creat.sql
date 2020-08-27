SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS   `0`;
CREATE TABLE  `0`   (
`id` int(11)  NOT   NULL  ,
`belong_to_scope` varchar(255)  DEFAULT NULL  ,
`finish_time` int(11)  NOT   NULL  DEFAULT   '0'  COMMENT   '完成次数',
`gmt_create` datetime  NOT   NULL  DEFAULT   CURRENT_TIMESTAMP  ,
`gmt_modified` datetime  NOT   NULL  DEFAULT   CURRENT_TIMESTAMP  ,
`problem` varchar(255)  DEFAULT NULL  COMMENT   '问题',
`result` varchar(1000)  DEFAULT NULL  COMMENT   '结果',
PRIMARY KEY   (`id`  )   USING BTREE
)ENGINE = InnoDB CHARACTER         SET = utf8 COLLATE = utf8_general_ci    COMMENT = '0'   ;
SET FOREIGN_KEY_CHECKS = 1;
