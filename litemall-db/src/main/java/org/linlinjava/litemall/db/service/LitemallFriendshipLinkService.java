package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallFriendshipLinkMapper;
import org.linlinjava.litemall.db.domain.LitemallFriendshipLink;
import org.linlinjava.litemall.db.domain.LitemallFriendshipLinkExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallFriendshipLinkService {
    @Resource
//    private LitemallNewsMapper newsMapper;
    private LitemallFriendshipLinkMapper friendshipLinkMapper;
//    private LitemallNews.Column[] columns = new LitemallNews.Column[]{LitemallNews.Column.id, LitemallNews.Column.title, LitemallNews.Column.subtitle, LitemallNews.Column.price, LitemallNews.Column.picUrl, LitemallNews.Column.readCount};

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
//
//    public int queryTotal() {
//        LitemallNewsExample example = new LitemallNewsExample();
//        example.or().andDeletedEqualTo(false);
//        return (int) newsMapper.countByExample(example);
//    }

    public LitemallFriendshipLink findById(Integer id) {
        LitemallFriendshipLinkExample example=new LitemallFriendshipLinkExample();

        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return friendshipLinkMapper.selectOneByExample(example);
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

    public List<LitemallFriendshipLink> querySelective(String link_name, Integer page, Integer limit, String sort, String order) {
        LitemallFriendshipLinkExample example=new LitemallFriendshipLinkExample();
        LitemallFriendshipLinkExample.Criteria criteria=example.createCriteria();

        if (!StringUtils.isEmpty(link_name)) {
            criteria.andLinkNameLike("%" + link_name + "%");
        }
//        if (!StringUtils.isEmpty(subtitle)) {
//            criteria.andSubtitleLike("%" + subtitle + "%");
//        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return friendshipLinkMapper.selectByExample(example);
    }

    public int countSelective(String link_name, Integer page, Integer size, String sort, String order) {
        LitemallFriendshipLinkExample example=new LitemallFriendshipLinkExample();
        LitemallFriendshipLinkExample.Criteria criteria=example.createCriteria();

        if (!StringUtils.isEmpty(link_name)) {
            criteria.andLinkNameLike("%" + link_name + "%");
        }
        criteria.andDeletedEqualTo(false);

        return (int) friendshipLinkMapper.countByExample(example);
    }

    public int updateById(LitemallFriendshipLink friendshipLink) {
        friendshipLink.setUpdateTime(LocalDateTime.now());
        LitemallFriendshipLinkExample example=new LitemallFriendshipLinkExample();
//        LitemallNewsExample example = new LitemallNewsExample();
        example.or().andIdEqualTo(friendshipLink.getId());
        return friendshipLinkMapper.updateByExampleSelective(friendshipLink, example);
    }

    public void deleteById(Integer id) {
        friendshipLinkMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallFriendshipLink friendshipLink) {
        friendshipLink.setAddTime(LocalDateTime.now());
        friendshipLink.setUpdateTime(LocalDateTime.now());
        friendshipLinkMapper.insertSelective(friendshipLink);
    }


}
