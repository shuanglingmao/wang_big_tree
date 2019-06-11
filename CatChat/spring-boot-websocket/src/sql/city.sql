SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `city`
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `city_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	 `city_no` varchar(32) NOT NULL  COMMENT '城市编号',
  `city_name` varchar(32) DEFAULT NULL COMMENT '城市名称',
  `province_name` varchar(32) DEFAULT NULL COMMENT '省份名称',
  `flag` INT  DEFAULT NULL COMMENT '标识',
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


