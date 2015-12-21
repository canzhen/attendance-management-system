/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : amdb
Target Host     : localhost:3306
Target Database : amdb
Date: 2015-12-14 22:31:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tc
-- ----------------------------
DROP TABLE IF EXISTS `tc`;
CREATE TABLE `tc` (
  `tno` char(8) NOT NULL,
  `cno` char(8) NOT NULL,
  `cweek` int(11) DEFAULT NULL,
  `cday` int(11) DEFAULT NULL,
  `ctime` int(11) DEFAULT NULL,
  `check_time` int(11) DEFAULT '10',
  `max_absence` int(11) DEFAULT '10',
  PRIMARY KEY (`tno`,`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc
-- ----------------------------
INSERT INTO `tc` VALUES ('1111113', 'cs002', '0', '13', '2', '11', '10');
INSERT INTO `tc` VALUES ('1111115', 'cs003', '2', '26', '6', '10', '10');
INSERT INTO `tc` VALUES ('1111114', 'cs001', '0', '13', '2', '11', '10');
INSERT INTO `tc` VALUES ('1111115', 'cs001', '2', '26', '6', '10', '10');
INSERT INTO `tc` VALUES ('1111112', 'cs001', '0', '13', '2', '11', '10');
INSERT INTO `tc` VALUES ('1111114', 'cs002', '2', '26', '6', '10', '10');