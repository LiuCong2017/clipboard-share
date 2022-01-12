use mysql;
DROP database if EXISTS `clip_db`;
CREATE database `clip_db` default character set utf8mb4 collate utf8mb4_unicode_ci;
use `clip_db`;

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item` (
  `id` BigInt UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `item_content` LONGTEXT DEFAULT NULL,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_item
-- ----------------------------
BEGIN;
INSERT INTO t_item(item_content) VALUES('content test');
INSERT INTO t_item(item_content) VALUES('ffewaw');
COMMIT;