CREATE SCHEMA `StuInfoManage` ;


CREATE TABLE `StuInfoManage`.`user` (
  `id` VARCHAR(10) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `role` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `StuInfoManage`.`stu` (
  `sid` VARCHAR(10) NOT NULL,
  `name` VARCHAR(10) NOT NULL,
  `sex` INT NULL,
  `age` INT NULL,
  `major` VARCHAR(20) NULL,
  PRIMARY KEY (`sid`));


CREATE TABLE `StuInfoManage`.`SC` (
  `sid` VARCHAR(10) NOT NULL,
  `cid` VARCHAR(10) NOT NULL,
  `grade` INT NULL,
  PRIMARY KEY (`sid`, `cid`));


CREATE TABLE `StuInfoManage`.`course` (
  `cid` VARCHAR(10) NOT NULL,
  `tid` VARCHAR(10) NULL,
  `cname` VARCHAR(20) NULL,
  PRIMARY KEY (`cid`, `tid`));

CREATE TABLE `StuInfoManage`.`nteacher` (
  `tid` VARCHAR(10) NOT NULL,
  `tname` VARCHAR(20) NULL,
  PRIMARY KEY (`tid`));

CREATE TABLE `StuInfoManage`.`steacher` (
  `tid` VARCHAR(10) NOT NULL,
  `tname` VARCHAR(20) NULL,
  PRIMARY KEY (`tid`));



ALTER TABLE `StuInfoManage`.`stu` 
ADD CONSTRAINT `fk_stu`
  FOREIGN KEY (`sid`)
  REFERENCES `StuInfoManage`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE RESTRICT;

ALTER TABLE `StuInfoManage`.`nteacher` 
ADD CONSTRAINT `fk_nteacher`
  FOREIGN KEY (`tid`)
  REFERENCES `StuInfoManage`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE RESTRICT;

ALTER TABLE `StuInfoManage`.`steacher` 
ADD CONSTRAINT `fk_steacher`
  FOREIGN KEY (`tid`)
  REFERENCES `StuInfoManage`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE RESTRICT;


ALTER TABLE `StuInfoManage`.`course` 
ADD INDEX `fk_couse_idx` (`tid` ASC) VISIBLE;
;
ALTER TABLE `StuInfoManage`.`course` 
ADD CONSTRAINT `fk_couse`
  FOREIGN KEY (`tid`)
  REFERENCES `StuInfoManage`.`nteacher` (`tid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `StuInfoManage`.`SC` 
ADD INDEX `fk_SCcid_idx` (`cid` ASC) VISIBLE;
;
ALTER TABLE `StuInfoManage`.`SC` 
ADD CONSTRAINT `fk_SCsid`
  FOREIGN KEY (`sid`)
  REFERENCES `StuInfoManage`.`stu` (`sid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `fk_SCcid`
  FOREIGN KEY (`cid`)
  REFERENCES `StuInfoManage`.`course` (`cid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

INSERT INTO `StuInfoManage`.`user` (`id`, `password`, `role`) VALUES ('0000', '0000', '3');
INSERT INTO `StuInfoManage`.`user` (`id`, `password`, `role`) VALUES ('1001', '1001', '2');
INSERT INTO `StuInfoManage`.`user` (`id`, `password`, `role`) VALUES ('1002', '1002', '2');
INSERT INTO `StuInfoManage`.`user` (`id`, `password`, `role`) VALUES ('1003', '1003', '2');
INSERT INTO `StuInfoManage`.`user` (`id`, `password`, `role`) VALUES ('1004', '1004', '2');
INSERT INTO `StuInfoManage`.`user` (`id`, `password`, `role`) VALUES ('1005', '1005', '2');
INSERT INTO `StuInfoManage`.`user` (`id`, `password`, `role`) VALUES ('201701', '201701', '1');
INSERT INTO `StuInfoManage`.`user` (`id`, `password`, `role`) VALUES ('201702', '201702', '1');
INSERT INTO `StuInfoManage`.`user` (`id`, `password`, `role`) VALUES ('201703', '201703', '1');
INSERT INTO `StuInfoManage`.`user` (`id`, `password`, `role`) VALUES ('201704', '201704', '1');
INSERT INTO `StuInfoManage`.`user` (`id`, `password`, `role`) VALUES ('201705', '201705', '1');


INSERT INTO `StuInfoManage`.`stu` (`sid`, `name`, `sex`, `age`, `major`) VALUES ('201701', '李一', '1', '20', '大数据');
INSERT INTO `StuInfoManage`.`stu` (`sid`, `name`, `sex`, `age`, `major`) VALUES ('201702', '王二', '1', '20', '小数据');
INSERT INTO `StuInfoManage`.`stu` (`sid`, `name`, `sex`, `age`, `major`) VALUES ('201703', '赵三', '2', '20', '软件工程');
INSERT INTO `StuInfoManage`.`stu` (`sid`, `name`, `sex`, `age`, `major`) VALUES ('201704', '钱四', '1', '20', '硬件工程');
INSERT INTO `StuInfoManage`.`stu` (`sid`, `name`, `sex`, `age`, `major`) VALUES ('201705', '孙五', '2', '20', '数据分析');

INSERT INTO `StuInfoManage`.`nteacher` (`tid`, `tname`) VALUES ('1001', '周一一');
INSERT INTO `StuInfoManage`.`nteacher` (`tid`, `tname`) VALUES ('1002', '吴二二');
INSERT INTO `StuInfoManage`.`nteacher` (`tid`, `tname`) VALUES ('1003', '郑三三');
INSERT INTO `StuInfoManage`.`nteacher` (`tid`, `tname`) VALUES ('1004', '冯四四');
INSERT INTO `StuInfoManage`.`nteacher` (`tid`, `tname`) VALUES ('1005', '陈五五');

INSERT INTO `StuInfoManage`.`steacher` (`tid`, `tname`) VALUES ('0000', '凌零零');


INSERT INTO `StuInfoManage`.`course` (`cid`, `tid`, `cname`) VALUES ('001', '1001', '数据库');
INSERT INTO `StuInfoManage`.`course` (`cid`, `tid`, `cname`) VALUES ('002', '1001', 'Python');
INSERT INTO `StuInfoManage`.`course` (`cid`, `tid`, `cname`) VALUES ('003', '1002', '英语');
INSERT INTO `StuInfoManage`.`course` (`cid`, `tid`, `cname`) VALUES ('004', '1002', '英语口语');
INSERT INTO `StuInfoManage`.`course` (`cid`, `tid`, `cname`) VALUES ('005', '1003', '离散数学');
INSERT INTO `StuInfoManage`.`course` (`cid`, `tid`, `cname`) VALUES ('006', '1003', '数据库');
INSERT INTO `StuInfoManage`.`course` (`cid`, `tid`, `cname`) VALUES ('007', '1004', 'C#');
INSERT INTO `StuInfoManage`.`course` (`cid`, `tid`, `cname`) VALUES ('008', '1005', '网页设计');

INSERT INTO `StuInfoManage`.`SC` (`sid`, `cid`, `grade`) VALUES ('201701', '001', '99');
INSERT INTO `StuInfoManage`.`SC` (`sid`, `cid`, `grade`) VALUES ('201701', '002', '66');
INSERT INTO `StuInfoManage`.`SC` (`sid`, `cid`, `grade`) VALUES ('201701', '005', '60');
INSERT INTO `StuInfoManage`.`SC` (`sid`, `cid`, `grade`) VALUES ('201702', '004', '89');
INSERT INTO `StuInfoManage`.`SC` (`sid`, `cid`, `grade`) VALUES ('201703', '002', '99');
INSERT INTO `StuInfoManage`.`SC` (`sid`, `cid`, `grade`) VALUES ('201703', '003', '37');
INSERT INTO `StuInfoManage`.`SC` (`sid`, `cid`, `grade`) VALUES ('201704', '001', '90');
INSERT INTO `StuInfoManage`.`SC` (`sid`, `cid`, `grade`) VALUES ('201705', '004', '24');
