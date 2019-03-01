package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallTabInfoTagRelatedMapper;
import org.linlinjava.litemall.db.domain.LitemallTabInfoTagRelated;
import org.linlinjava.litemall.db.domain.LitemallTabInfoTagRelatedExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallTabInfoTagRelatedService {
    @Resource
    private LitemallTabInfoTagRelatedMapper tabInfoTagRelatedMapper;

//
    public void deleteById(Integer id) {
        tabInfoTagRelatedMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallTabInfoTagRelated tabInfoTagRelated) {
        tabInfoTagRelated.setCreateTime(LocalDateTime.now());
        tabInfoTagRelated.setUpdateTime(LocalDateTime.now());
        tabInfoTagRelatedMapper.insertSelective(tabInfoTagRelated);
    }

    //根据资讯id查询标签关联表
    public List<LitemallTabInfoTagRelated> selectAllTag(Integer info_id){
        LitemallTabInfoTagRelatedExample example = new LitemallTabInfoTagRelatedExample();
        LitemallTabInfoTagRelatedExample.Criteria criteria = example.createCriteria();

        criteria.andDeletedEqualTo(false);

        if(info_id!=null){
            criteria.andInfoIdEqualTo(info_id);
        }

        return tabInfoTagRelatedMapper.selectByExample(example);
    }

    public LitemallTabInfoTagRelated findTagExist(Integer info_id,Integer tag_id) {
        LitemallTabInfoTagRelatedExample example = new LitemallTabInfoTagRelatedExample();

        example.or().andInfoIdEqualTo(info_id).andTagIdEqualTo(tag_id).andDeletedEqualTo(false);
        return tabInfoTagRelatedMapper.selectOneByExample(example);
    }


}
