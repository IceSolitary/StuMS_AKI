CREATE SCHEMA `Stu_Info_Manage_System` ;

CREATE TABLE `Stu_Info_Manage_System`.`course` (
  `cid` varchar(10) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
);
CREATE TABLE `Stu_Info_Manage_System`.`user` (
  `id` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `Stu_Info_Manage_System`.`stu` (
  `sid` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sex` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  CONSTRAINT `fk_stu` FOREIGN KEY (`sid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE TABLE `Stu_Info_Manage_System`.`nteacher` (
  `tid` varchar(10) NOT NULL,
  `tname` varchar(20) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  CONSTRAINT `fk_nteacher` FOREIGN KEY (`tid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `Stu_Info_Manage_System`.`ct` (
  `kkid` varchar(10) NOT NULL,
  `cid` varchar(10) DEFAULT NULL,
  `tid` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`kkid`),
  KEY `cid_idx` (`cid`),
  KEY `tid_idx` (`tid`),
  CONSTRAINT `fk_ctcid` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cttid` FOREIGN KEY (`tid`) REFERENCES `nteacher` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `Stu_Info_Manage_System`.`steacher` (
  `tid` varchar(10) NOT NULL,
  `tname` varchar(20) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  CONSTRAINT `fk_steacher` FOREIGN KEY (`tid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `Stu_Info_Manage_System`.`sc` (
  `sid` varchar(10) NOT NULL,
  `kkid` varchar(10) NOT NULL,
  `grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`,`kkid`),
  KEY `fk_sckk` (`kkid`),
  CONSTRAINT `fk_sckk` FOREIGN KEY (`kkid`) REFERENCES `ct` (`kkid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_scsid` FOREIGN KEY (`sid`) REFERENCES `stu` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO `Stu_Info_Manage_System`.`user` (`id`, `password`, `role`) VALUES ('000000', '000000', '3');
INSERT INTO `Stu_Info_Manage_System`.`user` (`id`, `password`, `role`) VALUES ('000001', '000001', '2');
INSERT INTO `Stu_Info_Manage_System`.`user` (`id`, `password`, `role`) VALUES ('000002', '000002', '2');
INSERT INTO `Stu_Info_Manage_System`.`user` (`id`, `password`, `role`) VALUES ('000003', '000003', '2');
INSERT INTO `Stu_Info_Manage_System`.`user` (`id`, `password`, `role`) VALUES ('000004', '000004', '2');
INSERT INTO `Stu_Info_Manage_System`.`user` (`id`, `password`, `role`) VALUES ('000005', '000005', '2');
INSERT INTO `Stu_Info_Manage_System`.`user` (`id`, `password`, `role`) VALUES ('201701', '201701', '1');
INSERT INTO `Stu_Info_Manage_System`.`user` (`id`, `password`, `role`) VALUES ('201702', '201702', '1');
INSERT INTO `Stu_Info_Manage_System`.`user` (`id`, `password`, `role`) VALUES ('201703', '201703', '1');
INSERT INTO `Stu_Info_Manage_System`.`user` (`id`, `password`, `role`) VALUES ('201704', '201704', '1');
INSERT INTO `Stu_Info_Manage_System`.`user` (`id`, `password`, `role`) VALUES ('201705', '201705', '1');


INSERT INTO `Stu_Info_Manage_System`.`stu` (`sid`, `name`, `sex`, `age`, `major`) VALUES ('201701', '李一', '1', '20', '大数据');
INSERT INTO `Stu_Info_Manage_System`.`stu` (`sid`, `name`, `sex`, `age`, `major`) VALUES ('201702', '王二', '1', '20', '小数据');
INSERT INTO `Stu_Info_Manage_System`.`stu` (`sid`, `name`, `sex`, `age`, `major`) VALUES ('201703', '赵三', '2', '20', '软件工程');
INSERT INTO `Stu_Info_Manage_System`.`stu` (`sid`, `name`, `sex`, `age`, `major`) VALUES ('201704', '钱四', '1', '20', '硬件工程');
INSERT INTO `Stu_Info_Manage_System`.`stu` (`sid`, `name`, `sex`, `age`, `major`) VALUES ('201705', '孙五', '2', '20', '数据分析');

INSERT INTO `Stu_Info_Manage_System`.`nteacher` (`tid`, `tname`) VALUES ('000001', '周一一');
INSERT INTO `Stu_Info_Manage_System`.`nteacher` (`tid`, `tname`) VALUES ('000002', '吴二二');
INSERT INTO `Stu_Info_Manage_System`.`nteacher` (`tid`, `tname`) VALUES ('000003', '郑三三');
INSERT INTO `Stu_Info_Manage_System`.`nteacher` (`tid`, `tname`) VALUES ('000004', '冯四四');
INSERT INTO `Stu_Info_Manage_System`.`nteacher` (`tid`, `tname`) VALUES ('000005', '陈五五');

UPDATE `Stu_Info_Manage_System`.`nteacher` SET `sex` = '2', `age` = '30', `major` = 'English' WHERE (`tid` = '000003');
UPDATE `Stu_Info_Manage_System`.`nteacher` SET `sex` = '1', `age` = '27', `major` = 'Computer' WHERE (`tid` = '000001');
UPDATE `Stu_Info_Manage_System`.`nteacher` SET `sex` = '1', `age` = '39', `major` = 'Computer' WHERE (`tid` = '000002');
UPDATE `Stu_Info_Manage_System`.`nteacher` SET `sex` = '1', `age` = '30', `major` = 'Math' WHERE (`tid` = '000004');
UPDATE `Stu_Info_Manage_System`.`nteacher` SET `sex` = '1', `age` = '30', `major` = 'Math' WHERE (`tid` = '000005');


INSERT INTO `Stu_Info_Manage_System`.`steacher` (`tid`, `tname`) VALUES ('000000', '凌零零');
UPDATE `Stu_Info_Manage_System`.`steacher` SET `sex` = '2', `age` = '44', `major` = 'Manage' WHERE (`tid` = '000000');


INSERT INTO `Stu_Info_Manage_System`.`course` (`cid`, `cname`) VALUES ('100001', '数据库');
INSERT INTO `Stu_Info_Manage_System`.`course` (`cid`, `cname`) VALUES ('100002', 'Python');
INSERT INTO `Stu_Info_Manage_System`.`course` (`cid`, `cname`) VALUES ('100003', '英语');
INSERT INTO `Stu_Info_Manage_System`.`course` (`cid`, `cname`) VALUES ('100004', '英语口语');
INSERT INTO `Stu_Info_Manage_System`.`course` (`cid`, `cname`) VALUES ('100005', '离散数学');
INSERT INTO `Stu_Info_Manage_System`.`course` (`cid`, `cname`) VALUES ('100006', '数据库');
INSERT INTO `Stu_Info_Manage_System`.`course` (`cid`, `cname`) VALUES ('100007', 'C#');
INSERT INTO `Stu_Info_Manage_System`.`course` (`cid`, `cname`) VALUES ('100008', '网页设计');


INSERT INTO `Stu_Info_Manage_System`.`CT` (`kkid`, `cid`, `tid`) VALUES ('1', '100001', '000001');
INSERT INTO `Stu_Info_Manage_System`.`CT` (`kkid`, `cid`, `tid`) VALUES ('2', '100002', '000002');
INSERT INTO `Stu_Info_Manage_System`.`CT` (`kkid`, `cid`, `tid`) VALUES ('3', '100003', '000003');
INSERT INTO `Stu_Info_Manage_System`.`CT` (`kkid`, `cid`, `tid`) VALUES ('4', '100004', '000003');
INSERT INTO `Stu_Info_Manage_System`.`CT` (`kkid`, `cid`, `tid`) VALUES ('5', '100005', '000004');
INSERT INTO `Stu_Info_Manage_System`.`CT` (`kkid`, `cid`, `tid`) VALUES ('6', '100006', '000005');


INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201701', '1', '99');
INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201701', '2', '66');

INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201702', '1', '46');
INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201702', '2', '99');
INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201702', '3', '78');

INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201703', '1', '88');
INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201703', '2', '76');
INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201703', '5', '56');

INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201704', '3', '99');
INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201704', '4', '99');
INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201704', '5', '99');


INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201705', '1', '99');
INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201705', '2', '77');
INSERT INTO `Stu_Info_Manage_System`.`SC` (`sid`, `kkid`, `grade`) VALUES ('201705', '4', '88');

