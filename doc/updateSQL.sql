CREATE TABLE `litemall`.`litemall_money_apply` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `commission_result_id` INT(11) NULL COMMENT '收益明细表id',
  `apply_user` INT(11) NULL COMMENT '申请人',
  `apply_time` DATETIME NULL DEFAULT now() COMMENT '申请时间',
  `audit_flag` VARCHAR(3) NULL COMMENT '审核状态0、待审核1、审核通过、2、审核不通过',
  `audit_user` INT(11) NULL COMMENT '审核人',
  `audit_time` DATETIME NULL COMMENT '审核时间',
  PRIMARY KEY (`id`))
COMMENT = '提现明细表';


ALTER TABLE `litemall`.`litemall_money_apply`
CHANGE COLUMN `commission_result_id` `account_id` INT(11) NULL DEFAULT NULL COMMENT '账户表' ,
ADD COLUMN `money` DECIMAL(12,2) NULL COMMENT '金额' AFTER `account_id`,
ADD COLUMN `brokerage` DECIMAL(12,2) NULL AFTER `money`,
ADD COLUMN `finall_money` DECIMAL(12,2) NULL COMMENT '实际到账金额' AFTER `brokerage`;


ALTER TABLE `litemall`.`litemall_money_apply`
ADD COLUMN `apply_user_name` VARCHAR(45) NULL COMMENT '申请人姓名' AFTER `deleted`;


--2019-02-10-- andy
CREATE TABLE "litemall_news" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "title" varchar(255) NOT NULL DEFAULT '''' COMMENT '资讯标题',
  "subtitle" varchar(255) DEFAULT '''' COMMENT '资讯子标题',
  "news_type" varchar(255) DEFAULT '''' COMMENT '资讯类型',
  "content" text COMMENT '资讯内容，富文本格式',
  "price" decimal(10,2) DEFAULT '0.00' COMMENT '资讯相关商品最低价',
  "read_count" varchar(255) DEFAULT '1k' COMMENT '资讯阅读量',
  "pic_url" varchar(255) DEFAULT '' COMMENT '资讯图片',
  "sort_order" int(11) DEFAULT '100' COMMENT '排序',
  "goods" varchar(1023) DEFAULT '' COMMENT '资讯相关商品，采用JSON数组格式',
  "add_time" datetime DEFAULT NULL COMMENT '创建时间',
  "update_time" datetime DEFAULT NULL COMMENT '更新时间',
  "deleted" tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY ("id"),
  KEY "news_id" ("id")
) ENGINE=InnoDB AUTO_INCREMENT=315 DEFAULT CHARSET=utf8mb4 COMMENT='资讯表';


CREATE TABLE "litemall_code_tree" (
  "id" int(11) NOT NULL AUTO_INCREMENT COMMENT '单位',
  "pid" int(11) DEFAULT NULL COMMENT '父ID',
  "code_type" varchar(45) DEFAULT NULL COMMENT '代码',
  "code_name" varchar(45) DEFAULT NULL COMMENT '代码名称',
  "code_unit" varchar(45) DEFAULT NULL COMMENT '单位',
  "valid_flag" varchar(1) DEFAULT '1',
  "dis_order" int(4) DEFAULT '1' COMMENT '显示顺序',
  PRIMARY KEY ("id")
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


CREATE TABLE `litemall`.`litemall_friendship_link` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `link_name` VARCHAR(45) NULL COMMENT '名称',
  `link_url` VARCHAR(100) NULL COMMENT '链接地址',
  `pic_url` VARCHAR(100) NULL COMMENT '链接图片',
  `add_time` DATETIME NULL,
  `update_time` DATETIME NULL,
  `deleted` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id`))
COMMENT = '友情链接';

ALTER TABLE `litemall`.`litemall_friendship_link`
ADD COLUMN `friend_type` VARCHAR(3) NULL COMMENT '1、合作伙伴2、友情链接' AFTER `pic_url`;

