package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallTabInfoContentMapper;
import org.linlinjava.litemall.db.domain.LitemallTabInfoContent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class LitemallTabInfoContentService {
    @Resource
    private LitemallTabInfoContentMapper tabInfoContentMapper;

    public int updateById(LitemallTabInfoContent tabInfoContent) {
        tabInfoContent.setUpdateTime(LocalDateTime.now());
        return tabInfoContentMapper.updateByPrimaryKeySelective(tabInfoContent);
    }

    //
//    public void deleteById(Integer id) {
//        litemallAccountMapper.logicalDeleteByPrimaryKey(id);
//    }
//
    public void add(LitemallTabInfoContent content) {
        content.setCreateTime(LocalDateTime.now());
        content.setUpdateTime(LocalDateTime.now());
        tabInfoContentMapper.insertSelective(content);
    }

    public LitemallTabInfoContent findContent(Integer info_id) {
        return tabInfoContentMapper.selectInfoContent(info_id);
    }
}
