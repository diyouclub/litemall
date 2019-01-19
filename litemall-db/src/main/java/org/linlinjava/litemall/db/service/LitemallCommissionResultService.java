package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallCommissionResultMapper;
import org.linlinjava.litemall.db.domain.LitemallCommissionResult;
import org.linlinjava.litemall.db.domain.LitemallCommissionResultExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallCommissionResultService {
    @Resource
    private LitemallCommissionResultMapper litemallCommissionResultMapper;

    public List<LitemallCommissionResult> queryIndex() {
        LitemallCommissionResultExample example = new LitemallCommissionResultExample();
        //example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return litemallCommissionResultMapper.selectByExample(example);
    }

    public List<LitemallCommissionResult> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {
        LitemallCommissionResultExample example = new LitemallCommissionResultExample();
        LitemallCommissionResultExample.Criteria criteria = example.createCriteria();


//        if (!StringUtils.isEmpty(content)) {
//            criteria.andContentLike("%" + content + "%");
//        }
//        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return litemallCommissionResultMapper.selectByExample(example);
    }

    public int countSelective(String name, String content, Integer page, Integer size, String sort, String order) {
        LitemallCommissionResultExample example = new LitemallCommissionResultExample();
        LitemallCommissionResultExample.Criteria criteria = example.createCriteria();


//        if (!StringUtils.isEmpty(content)) {
//            criteria.andContentLike("%" + content + "%");
//        }
//        criteria.andDeletedEqualTo(false);

        return (int) litemallCommissionResultMapper.countByExample(example);
    }

    public int updateById(LitemallCommissionResult result) {
         result.setUpdateTime(LocalDateTime.now());
        return litemallCommissionResultMapper.updateByPrimaryKeySelective(result);
    }

    public void deleteById(Integer id) {
        //litemallCommissionResultMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallCommissionResult result) {
        result.setAddTime(LocalDateTime.now());
        result.setUpdateTime(LocalDateTime.now());
        litemallCommissionResultMapper.insertSelective(result);
    }

    public LitemallCommissionResult findById(Integer id) {
        return litemallCommissionResultMapper.selectByPrimaryKey(id);
    }

    public List<LitemallCommissionResult> findByUserId(Integer userid) {
        LitemallCommissionResultExample example = new LitemallCommissionResultExample();
        LitemallCommissionResultExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userid);
        return litemallCommissionResultMapper.selectByExample(example);
    }
}
