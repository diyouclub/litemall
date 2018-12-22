package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallCommissionMapper;
import org.linlinjava.litemall.db.domain.LitemallCommission;
import org.linlinjava.litemall.db.domain.LitemallCommissionExample;
import org.linlinjava.litemall.db.domain.LitemallCommission;
import org.linlinjava.litemall.db.domain.LitemallCommissionExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallCommissionService {
    @Resource
    private LitemallCommissionMapper litemallCommissionMapper;

    public List<LitemallCommission> queryIndex() {
        LitemallCommissionExample example = new LitemallCommissionExample();
        //example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return litemallCommissionMapper.selectByExample(example);
    }

    public List<LitemallCommission> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {
        LitemallCommissionExample example = new LitemallCommissionExample();
        LitemallCommissionExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
//        if (!StringUtils.isEmpty(content)) {
//            criteria.andContentLike("%" + content + "%");
//        }
//        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return litemallCommissionMapper.selectByExample(example);
    }

    public int countSelective(String name, String content, Integer page, Integer size, String sort, String order) {
        LitemallCommissionExample example = new LitemallCommissionExample();
        LitemallCommissionExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
//        if (!StringUtils.isEmpty(content)) {
//            criteria.andContentLike("%" + content + "%");
//        }
//        criteria.andDeletedEqualTo(false);

        return (int) litemallCommissionMapper.countByExample(example);
    }

    public int updateById(LitemallCommission ad) {
      //  ad.setUpdateTime(LocalDateTime.now());
        return litemallCommissionMapper.updateByPrimaryKeySelective(ad);
    }

    public void deleteById(Integer id) {
        //litemallCommissionMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallCommission ad) {
       // ad.setAddTime(LocalDateTime.now());
       // ad.setUpdateTime(LocalDateTime.now());
        litemallCommissionMapper.insertSelective(ad);
    }

    public LitemallCommission findById(Integer id) {
        return litemallCommissionMapper.selectByPrimaryKey(id);
    }
}
