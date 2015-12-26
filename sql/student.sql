/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : amdb
Target Host     : localhost:3306
Target Database : amdb
Date: 2015-12-26 10:09:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sno` char(8) NOT NULL,
  `sname` varchar(20) NOT NULL,
  `spwd` varchar(20) NOT NULL,
  `avatar` varchar(20) NOT NULL DEFAULT 'default.png',
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('13301081', '于瀚程', '111', 'default.png');
INSERT INTO `student` VALUES ('13301085', '周灿桢', '111', 'default.png');
INSERT INTO `student` VALUES ('13301087', '葛梦珍', '111', 'default.png');
INSERT INTO `student` VALUES ('13301089', '贾佩', '111', 'default.png');
