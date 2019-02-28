package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallTabInfoTagMapper;
import org.linlinjava.litemall.db.domain.LitemallTabInfoTag;
import org.linlinjava.litemall.db.domain.LitemallTabInfoTagExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallTabInfoTagService {
    @Resource
    private LitemallTabInfoTagMapper tabInfoTagMapper;

//
//    public int updateById(LitemallAccount account) {
//        account.setUpdateTime(LocalDateTime.now());
//        return litemallAccountMapper.updateByPrimaryKeySelective(account);
//    }
//
//    public void deleteById(Integer id) {
//        litemallAccountMapper.logicalDeleteByPrimaryKey(id);
//    }
//
    public Integer add(LitemallTabInfoTag content, String name) {
        content.setCreateTime(LocalDateTime.now());
        content.setUpdateTime(LocalDateTime.now());
        content.setName(name);
        tabInfoTagMapper.insertSelective(content);
        Integer tag_id = content.getId();
        return tag_id;
    }

    public LitemallTabInfoTag findByName(String name) {
        LitemallTabInfoTagExample example = new LitemallTabInfoTagExample();
        example.or().andNameEqualTo(name).andDeletedEqualTo(false);
        return tabInfoTagMapper.selectOneByExample(example);
    }

    public List<LitemallTabInfoTag> findInfoTag(Integer info_id) {
        return tabInfoTagMapper.selectInfoTag(info_id);
    }

    public List<LitemallTabInfoTag> querySelective() {
        LitemallTabInfoTagExample example = new LitemallTabInfoTagExample();
        LitemallTabInfoTagExample.Criteria criteria = example.createCriteria();

        criteria.andDeletedEqualTo(false);
        return tabInfoTagMapper.selectAllTag(example);
    }
}
