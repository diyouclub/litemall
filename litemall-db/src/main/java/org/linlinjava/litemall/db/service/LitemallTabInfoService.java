package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallTabInfoMapper;
import org.linlinjava.litemall.db.domain.LitemallTabInfo;
import org.linlinjava.litemall.db.domain.LitemallTabInfoExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallTabInfoService {
    @Resource
//    private LitemallNewsMapper newsMapper;
    private LitemallTabInfoMapper tabInfoMapper;
//    private LitemallNews.Column[] columns = new LitemallNews.Column[]{LitemallNews.Column.id, LitemallNews.Column.title, LitemallNews.Column.subtitle, LitemallNews.Column.price, LitemallNews.Column.picUrl, LitemallNews.Column.readCount,LitemallNews.Column.newsType};

//    public List<LitemallNews> queryList(int offset, int limit) {
//        return queryList(offset, limit, "add_time", "desc");
//    }

//    public List<LitemallNews> queryList(int offset, int limit, String sort, String order) {
//        LitemallNewsExample example = new LitemallNewsExample();
//        example.or().andDeletedEqualTo(false);
//        example.setOrderByClause(sort + " " + order);
//        PageHelper.startPage(offset, limit);
//        return newsMapper.selectByExampleSelective(example, columns);
//    }

    //    public int queryTotal() {
//        LitemallNewsExample example = new LitemallNewsExample();
//        example.or().andDeletedEqualTo(false);
//        return (int) newsMapper.countByExample(example);
//    }
//
    public LitemallTabInfo findById(Integer id) {
        LitemallTabInfoExample example = new LitemallTabInfoExample();
        example.or().andClsIdEqualTo(id).andDeletedEqualTo(false);
        return tabInfoMapper.selectOneByExample(example);
    }
//
//    public List<LitemallNews> queryRelatedList(Integer id, int offset, int limit) {
//        LitemallNewsExample example = new LitemallNewsExample();
//        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
//        List<LitemallNews> newss = newsMapper.selectByExample(example);
//        if (newss.size() == 0) {
//            return queryList(offset, limit, "add_time", "desc");
//        }
//        LitemallNews news = newss.get(0);
//
//        example = new LitemallNewsExample();
//        example.or().andIdNotEqualTo(news.getId()).andDeletedEqualTo(false);
//        PageHelper.startPage(offset, limit);
//        List<LitemallNews> relateds = newsMapper.selectByExampleWithBLOBs(example);
//        if (relateds.size() != 0) {
//            return relateds;
//        }
//
//        return queryList(offset, limit, "add_time", "desc");
//    }

    public List<LitemallTabInfo> querySelective(String info_title, Integer page, Integer limit, String sort, String order,Integer cls_id) {
        LitemallTabInfoExample example = new LitemallTabInfoExample();
        LitemallTabInfoExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(info_title)) {
            criteria.andInfoTitleLike("%" + info_title + "%");
        }
        criteria.andDeletedEqualTo(false);
        if(cls_id!=null){
            criteria.andClsIdEqualTo(cls_id);
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return tabInfoMapper.selectByMyself(example);
    }

    public int countSelective(String info_title, Integer page, Integer size, String sort, String order,Integer cls_id) {
        LitemallTabInfoExample example = new LitemallTabInfoExample();
        LitemallTabInfoExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(info_title)) {
            criteria.andInfoTitleLike("%" + info_title + "%");
        }
        if(cls_id!=null){
            criteria.andClsIdEqualTo(cls_id);
        }
        criteria.andDeletedEqualTo(false);
        return (int) tabInfoMapper.countByExample(example);
    }

    public int updateById(LitemallTabInfo tabInfo) {
        tabInfo.setUpdateTime(LocalDateTime.now());
        LitemallTabInfoExample example = new LitemallTabInfoExample();
        example.or().andInfoIdEqualTo(tabInfo.getInfoId());
        return tabInfoMapper.updateByExampleSelective(tabInfo, example);
    }

    public void deleteById(Integer id) {
        tabInfoMapper.logicalDeleteByPrimaryKey(id);
    }

    public Integer add(LitemallTabInfo tabInfo) {
        tabInfo.setCreateTime(LocalDateTime.now());
        tabInfo.setUpdateTime(LocalDateTime.now());
        tabInfoMapper.insertSelective(tabInfo);
        Integer info=tabInfo.getInfoId();
        return info;
    }

}
