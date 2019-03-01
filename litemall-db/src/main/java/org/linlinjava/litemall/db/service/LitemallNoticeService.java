package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallNoticeMapper;
import org.linlinjava.litemall.db.domain.LitemallNotice;
import org.linlinjava.litemall.db.domain.LitemallNoticeExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallNoticeService {
    @Resource
    private LitemallNoticeMapper noticeMapper;

    //公告新增
    public void add(LitemallNotice notice) {
        notice.setAddTime(LocalDateTime.now());
        notice.setUpdateTime(LocalDateTime.now());
        noticeMapper.insertSelective(notice);
    }

    //公告列表
    public List<LitemallNotice> querySelective(String notice_name, Integer page, Integer size, String sort, String order) {
        LitemallNoticeExample example = new LitemallNoticeExample();
        LitemallNoticeExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(notice_name)) {
            criteria.andNoticeNameEqualTo("%" + notice_name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return noticeMapper.selectByExample(example);
    }

    public int countSelective(String notice_name, Integer page, Integer size, String sort, String order) {
        LitemallNoticeExample example = new LitemallNoticeExample();
        LitemallNoticeExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(notice_name)) {
            criteria.andNoticeNameEqualTo("%" + notice_name + "%");
        }
        criteria.andDeletedEqualTo(false);

        return (int) noticeMapper.countByExample(example);
    }

    //公告编辑
    public int updateById(LitemallNotice notice) {
        notice.setUpdateTime(LocalDateTime.now());
        return noticeMapper.updateByPrimaryKeySelective(notice);
    }

    //公告删除
    public void deleteById(Integer id) {
        noticeMapper.logicalDeleteByPrimaryKey(id);
    }

}
