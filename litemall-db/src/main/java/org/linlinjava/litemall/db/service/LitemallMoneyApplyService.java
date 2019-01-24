package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallAccountMapper;
import org.linlinjava.litemall.db.dao.LitemallMoneyApplyMapper;
import org.linlinjava.litemall.db.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LitemallMoneyApplyService {
    @Resource
    private LitemallMoneyApplyMapper litemallMoneyApplyMapper;

    public List<LitemallMoneyApply> queryIndex() {
        LitemallMoneyApplyExample example = new LitemallMoneyApplyExample();
        //example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return litemallMoneyApplyMapper.selectByExample(example);
    }

    public List<LitemallMoneyApply> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {
        LitemallMoneyApplyExample example = new LitemallMoneyApplyExample();
        LitemallMoneyApplyExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return litemallMoneyApplyMapper.selectByExample(example);
    }

    public int countSelective(String name, String content, Integer page, Integer size, String sort, String order) {
        LitemallMoneyApplyExample example = new LitemallMoneyApplyExample();
        LitemallMoneyApplyExample.Criteria criteria = example.createCriteria();


        return (int) litemallMoneyApplyMapper.countByExample(example);
    }

    public int updateById(LitemallMoneyApply litemallMoneyApply) {
        litemallMoneyApply.setUpdateTime(LocalDateTime.now());
        return litemallMoneyApplyMapper.updateByPrimaryKeySelective(litemallMoneyApply);
    }

    public void deleteById(Integer id) {
        litemallMoneyApplyMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallMoneyApply litemallMoneyApply) {
        litemallMoneyApply.setAddTime(LocalDateTime.now());
        litemallMoneyApply.setUpdateTime(LocalDateTime.now());
        litemallMoneyApplyMapper.insertSelective(litemallMoneyApply);
    }

    public LitemallMoneyApply findById(Integer id) {
        return litemallMoneyApplyMapper.selectByPrimaryKey(id);
    }

    public LitemallMoneyApply findByUser(Integer userId) {
        LitemallMoneyApplyExample example = new LitemallMoneyApplyExample();
        example.or().andApplyUserEqualTo(userId).andDeletedEqualTo(false);
        return litemallMoneyApplyMapper.selectOneByExample(example);
    }
    /**
     * 管理员审核列表
     * @param startTime
     * @param endTime
     * @param userName
     * @param page
     * @param limit
     * @return
     */
    public List<LitemallMoneyApply> queryAll(String startTime, String endTime, String userName, Integer page, Integer limit) {
        LitemallMoneyApplyExample example = new LitemallMoneyApplyExample();
        LitemallMoneyApplyExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime) ) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startDate = LocalDateTime.parse(startTime,df);
            LocalDateTime endDate = LocalDateTime.parse(endTime,df);
            example.or().andAddTimeBetween(startDate,endDate);
        }else {
        }
        PageHelper.startPage(page, limit);
        return litemallMoneyApplyMapper.selectByExample(example);
    }
    public int countByAll(String startTime,String endTime, Integer userid, Integer page, Integer limit) {
        LitemallMoneyApplyExample example = new LitemallMoneyApplyExample();
        LitemallMoneyApplyExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime) ) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startDate = LocalDateTime.parse(startTime,df);
            LocalDateTime endDate = LocalDateTime.parse(endTime,df);
            example.or().andAddTimeBetween(startDate,endDate);
        }else {
        }
        return (int)litemallMoneyApplyMapper.countByExample(example);
    }
    /**
     * 用户查看自己申请列表
     * @param startTime
     * @param endTime
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public List<LitemallMoneyApply> queryByUserId(String startTime, String endTime, Integer userId, Integer page, Integer limit) {
        LitemallMoneyApplyExample example = new LitemallMoneyApplyExample();
        LitemallMoneyApplyExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime) ) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startDate = LocalDateTime.parse(startTime,df);
            LocalDateTime endDate = LocalDateTime.parse(endTime,df);
            example.or().andApplyUserEqualTo(userId).andAddTimeBetween(startDate,endDate);
        }else {
            example.or().andApplyUserEqualTo(userId);
        }
        PageHelper.startPage(page, limit);
        return litemallMoneyApplyMapper.selectByExample(example);
    }
    public int countByUserId(String startTime,String endTime, Integer userId, Integer page, Integer limit) {
        LitemallMoneyApplyExample example = new LitemallMoneyApplyExample();
        LitemallMoneyApplyExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime) ) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startDate = LocalDateTime.parse(startTime,df);
            LocalDateTime endDate = LocalDateTime.parse(endTime,df);
            example.or().andApplyUserEqualTo(userId).andAddTimeBetween(startDate,endDate);
        }else {
            example.or().andApplyUserEqualTo(userId);
        }
        return (int)litemallMoneyApplyMapper.countByExample(example);
    }
}
