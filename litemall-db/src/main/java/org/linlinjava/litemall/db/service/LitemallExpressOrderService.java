package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallExpressOrderMapper;
import org.linlinjava.litemall.db.domain.LitemallExpressOrder;
import org.linlinjava.litemall.db.domain.LitemallExpressOrderExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : fujue
 * @version V1.0
 * @Project: litemall
 * @Package org.linlinjava.litemall.db.service
 * @Description:  顺丰邮寄订单
 * @date Date : 2019年02月17日 16:09
 */
@Service
public class LitemallExpressOrderService {

    @Resource
    private LitemallExpressOrderMapper expressOrderMapper;

    public LitemallExpressOrder findByOrderId(String orderId) {
        LitemallExpressOrderExample expressOrderExample = new LitemallExpressOrderExample();
        expressOrderExample.or().andOrderidEqualTo(orderId);
        return expressOrderMapper.selectOneByExample(expressOrderExample);
    }

//    public LitemallExpressOrder findById(Integer id) {
//        return expressOrderMapper.selectByPrimaryKey(id);
//    }
}
