/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : amdb
Target Host     : localhost:3306
Target Database : amdb
Date: 2015-12-14 22:31:42
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tno` char(8) NOT NULL,
  `tname` varchar(20) NOT NULL,
  `tpwd` varchar(20) NOT NULL,
  PRIMARY KEY (`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('11111111', '刘铎', '111');
INSERT INTO `teacher` VALUES ('11111112', '袁岗', '111');
INSERT INTO `teacher` VALUES ('11111113', '曾立刚', '111');
INSERT INTO `teacher` VALUES ('11111114', '冯凤娟', '111');
INSERT INTO `teacher` VALUES ('11111115', '冀振燕', '111');
