package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallAccountMapper;
import org.linlinjava.litemall.db.domain.LitemallAccount;
import org.linlinjava.litemall.db.domain.LitemallAccountExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallAccountService {
    @Resource
    private LitemallAccountMapper litemallAccountMapper;

    public List<LitemallAccount> queryIndex() {
        LitemallAccountExample example = new LitemallAccountExample();
        //example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return litemallAccountMapper.selectByExample(example);
    }

    public List<LitemallAccount> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {
        LitemallAccountExample example = new LitemallAccountExample();
        LitemallAccountExample.Criteria criteria = example.createCriteria();



        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return litemallAccountMapper.selectByExample(example);
    }

    public int countSelective(String name, String content, Integer page, Integer size, String sort, String order) {
        LitemallAccountExample example = new LitemallAccountExample();
        LitemallAccountExample.Criteria criteria = example.createCriteria();


        return (int) litemallAccountMapper.countByExample(example);
    }

    public int updateById(LitemallAccount account) {
        account.setUpdateTime(LocalDateTime.now());
        return litemallAccountMapper.updateByPrimaryKeySelective(account);
    }

    public void deleteById(Integer id) {
        litemallAccountMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallAccount account) {
        account.setAddTime(LocalDateTime.now());
        account.setUpdateTime(LocalDateTime.now());
        litemallAccountMapper.insertSelective(account);
    }

    public LitemallAccount findById(Integer id) {
        return litemallAccountMapper.selectByPrimaryKey(id);
    }

    public LitemallAccount findByUser(Integer userId) {
        LitemallAccountExample example = new LitemallAccountExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return litemallAccountMapper.selectOneByExample(example);
    }

    public LitemallAccount initUserAccount(Integer userId) {
        LitemallAccount litemallAccount = this.findByUser(userId);
        if (litemallAccount == null ) {
            litemallAccount = new LitemallAccount();
            litemallAccount.setUserId(userId);
            litemallAccount.setBalance(new BigDecimal("0"));
            litemallAccount.setFrozenBalance(new BigDecimal("0"));
            litemallAccount.setAddTime(LocalDateTime.now());
            litemallAccount.setUpdateTime(LocalDateTime.now());
            litemallAccount.setAccountType("1");
            litemallAccount.setAccountStatus("1");
            litemallAccount.setDeleted(false);

            this.add(litemallAccount);
        }

        return litemallAccount;
    }

    public LitemallAccount addMoney(BigDecimal fee, Integer userId) {
        LitemallAccount litemallAccount = this.findByUser(userId);
        if (litemallAccount == null ) {
            litemallAccount =  this.initUserAccount(userId);
        }
        litemallAccount.setBalance(litemallAccount.getBalance().add(fee));
        litemallAccount.setUpdateTime(LocalDateTime.now());
        litemallAccountMapper.updateByPrimaryKey(litemallAccount);
        return litemallAccount;
    }

    public LitemallAccount subtractMoney(BigDecimal fee, Integer userId) {
        LitemallAccount litemallAccount = this.findByUser(userId);
        if (litemallAccount == null ) {
            litemallAccount =  this.initUserAccount(userId);
        }
        litemallAccount.setBalance(litemallAccount.getBalance().subtract(fee));
        litemallAccount.setUpdateTime(LocalDateTime.now());
        litemallAccountMapper.updateByPrimaryKey(litemallAccount);
        return litemallAccount;
    }
}
