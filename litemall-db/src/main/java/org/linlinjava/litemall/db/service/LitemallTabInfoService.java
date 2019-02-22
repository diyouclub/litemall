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
//    public LitemallNews findById(Integer id) {
//        LitemallNewsExample example = new LitemallNewsExample();
//        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
//        return newsMapper.selectOneByExampleWithBLOBs(example);
//    }
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

    public List<LitemallTabInfo> querySelective(String info_title, Integer page, Integer limit, String sort, String order) {
        LitemallTabInfoExample example = new LitemallTabInfoExample();
        LitemallTabInfoExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(info_title)) {
            criteria.andInfoTitleLike("%" + info_title + "%");
        }

        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return tabInfoMapper.selectByExample(example);
    }

    public int countSelective(String info_title, Integer page, Integer size, String sort, String order) {
        LitemallTabInfoExample example = new LitemallTabInfoExample();
        LitemallTabInfoExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(info_title)) {
            criteria.andInfoTitleLike("%" + info_title + "%");
        }

        criteria.andDeletedEqualTo(false);

        return (int) tabInfoMapper.countByExample(example);
    }
//
//    public int updateById(LitemallNews news) {
//        news.setUpdateTime(LocalDateTime.now());
//        LitemallNewsExample example = new LitemallNewsExample();
//        example.or().andIdEqualTo(news.getId());
//        return newsMapper.updateByExampleSelective(news, example);
//    }
//
//    public void deleteById(Integer id) {
//        newsMapper.logicalDeleteByPrimaryKey(id);
//    }
//
    public void add(LitemallTabInfo tabInfo) {
        tabInfo.setCreateTime(LocalDateTime.now());
        tabInfo.setUpdateTime(LocalDateTime.now());
        tabInfoMapper.insertSelective(tabInfo);
    }


}
