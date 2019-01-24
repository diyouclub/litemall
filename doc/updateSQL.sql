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
