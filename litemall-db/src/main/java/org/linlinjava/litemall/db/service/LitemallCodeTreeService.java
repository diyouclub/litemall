package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallCodeTreeMapper;
import org.linlinjava.litemall.db.domain.LitemallCodeTree;
import org.linlinjava.litemall.db.domain.LitemallCodeTreeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LitemallCodeTreeService {
    @Resource
    private LitemallCodeTreeMapper codeTreeMapper;
//    private LitemallCodeTree.Column[] columns = new LitemallCodeTree.Column[]{LitemallCodeTree.Column.id, LitemallCodeTree.Column.title, LitemallCodeTree.Column.subtitle, LitemallCodeTree.Column.price, LitemallCodeTree.Column.picUrl, LitemallCodeTree.Column.readCount};

    public List<LitemallCodeTree> queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }

    public List<LitemallCodeTree> queryList(int offset, int limit, String sort, String order) {
        LitemallCodeTreeExample example = new LitemallCodeTreeExample();
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(offset, limit);
        return codeTreeMapper.selectByExample(example);
    }

    public int queryTotal() {
        LitemallCodeTreeExample example = new LitemallCodeTreeExample();
        return (int) codeTreeMapper.countByExample(example);
    }

    public List<LitemallCodeTree> findByPId(Integer pid) {
        LitemallCodeTreeExample example = new LitemallCodeTreeExample();
        example.or().andPidEqualTo(pid);
        return codeTreeMapper.selectByExampleSelective(example);
    }
    public List<LitemallCodeTree> findById(Integer id) {
        LitemallCodeTreeExample example = new LitemallCodeTreeExample();
        example.or().andIdEqualTo(id);
        return codeTreeMapper.selectByExample(example);
    }
    public LitemallCodeTree findByCodeType(String codeType) {
        LitemallCodeTreeExample example = new LitemallCodeTreeExample();
        example.or().andCodeTypeEqualTo(codeType);
        return codeTreeMapper.selectOneByExample(example);
    }


    public int updateById(LitemallCodeTree codeTree) {
        LitemallCodeTreeExample example = new LitemallCodeTreeExample();
        example.or().andIdEqualTo(codeTree.getId());
        return codeTreeMapper.updateByExampleSelective(codeTree, example);
    }



    public void add(LitemallCodeTree codeTree) {
        codeTreeMapper.insertSelective(codeTree);
    }


}
