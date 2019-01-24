package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallAccountDetailMapper;
import org.linlinjava.litemall.db.dao.LitemallAccountMapper;
import org.linlinjava.litemall.db.domain.LitemallAccount;
import org.linlinjava.litemall.db.domain.LitemallAccountDetail;
import org.linlinjava.litemall.db.domain.LitemallAccountDetailExample;
import org.linlinjava.litemall.db.domain.LitemallAccountExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallAccountDetailService {
    @Resource
    private LitemallAccountDetailMapper litemallAccountDetailMapper;

    public List<LitemallAccountDetail> queryIndex() {
        LitemallAccountDetailExample example = new LitemallAccountDetailExample();
        //example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return litemallAccountDetailMapper.selectByExample(example);
    }

    public List<LitemallAccountDetail> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {
        LitemallAccountDetailExample example = new LitemallAccountDetailExample();
        LitemallAccountDetailExample.Criteria criteria = example.createCriteria();



        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return litemallAccountDetailMapper.selectByExample(example);
    }

    public int countSelective(String name, String content, Integer page, Integer size, String sort, String order) {
        LitemallAccountDetailExample example = new LitemallAccountDetailExample();
        LitemallAccountDetailExample.Criteria criteria = example.createCriteria();


        return (int) litemallAccountDetailMapper.countByExample(example);
    }

    public int updateById(LitemallAccountDetail account) {
        account.setUpdateTime(LocalDateTime.now());
        return litemallAccountDetailMapper.updateByPrimaryKeySelective(account);
    }

    public void deleteById(Integer id) {
        litemallAccountDetailMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallAccountDetail account) {
        account.setAddTime(LocalDateTime.now());
        account.setUpdateTime(LocalDateTime.now());
        litemallAccountDetailMapper.insertSelective(account);
    }

    public LitemallAccountDetail findById(Integer id) {
        return litemallAccountDetailMapper.selectByPrimaryKey(id);
    }
}
