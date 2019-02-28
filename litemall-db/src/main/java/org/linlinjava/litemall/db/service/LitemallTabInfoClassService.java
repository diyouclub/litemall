package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallTabInfoClassMapper;
import org.linlinjava.litemall.db.domain.LitemallTabInfoClass;
import org.linlinjava.litemall.db.domain.LitemallTabInfoClassExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallTabInfoClassService {
    @Resource
//    private LitemallNewsMapper newsMapper;
//    private LitemallTabInfoMapper tabInfoMapper;
    private LitemallTabInfoClassMapper tabInfoClassMapper;
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

    public LitemallTabInfoClass findById(Integer id) {
        LitemallTabInfoClassExample example = new LitemallTabInfoClassExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return tabInfoClassMapper.selectOneByExample(example);
    }

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

    public List<LitemallTabInfoClass> querySelective(String cls_name, Integer page, Integer limit, String sort, String order) {
        LitemallTabInfoClassExample example = new LitemallTabInfoClassExample();
        LitemallTabInfoClassExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(cls_name)) {
            criteria.andClsNameLike("%" + cls_name + "%");
        }

        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return tabInfoClassMapper.selectByExample(example);
    }

    public int countSelective(String cls_name, Integer page, Integer size, String sort, String order) {
        LitemallTabInfoClassExample example = new LitemallTabInfoClassExample();
        LitemallTabInfoClassExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(cls_name)) {
            criteria.andClsNameLike("%" + cls_name + "%");
        }

        criteria.andDeletedEqualTo(false);

        return (int) tabInfoClassMapper.countByExample(example);
    }

    public int updateById(LitemallTabInfoClass tabInfoClass) {
        tabInfoClass.setUpdateTime(LocalDateTime.now());
        LitemallTabInfoClassExample example = new LitemallTabInfoClassExample();
        example.or().andIdEqualTo(tabInfoClass.getId());
        return tabInfoClassMapper.updateByExampleSelective(tabInfoClass, example);
    }

    public void deleteById(Integer id) {
        tabInfoClassMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallTabInfoClass tabInfoClass) {
        tabInfoClass.setCreateTime(LocalDateTime.now());
        tabInfoClass.setUpdateTime(LocalDateTime.now());
        tabInfoClassMapper.insertSelective(tabInfoClass);
    }


}
