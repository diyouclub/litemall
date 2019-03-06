package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallExpressOrderCargoMapper;
import org.linlinjava.litemall.db.domain.LitemallExpressOrderCargo;
import org.linlinjava.litemall.db.domain.LitemallExpressOrderCargoExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : fujue
 * @version V1.0
 * @Project: litemall
 * @Package org.linlinjava.litemall.db.service
 * @Description: 顺丰邮寄订单货物
 * @date Date : 2019年02月17日 16:09
 */
@Service
public class LitemallExpressOrderCargoService {
    @Resource
    private LitemallExpressOrderCargoMapper cargoMapper;

    public LitemallExpressOrderCargo findByExpressOrderId(Integer expressOrderId) {
        LitemallExpressOrderCargoExample cargoExample = new LitemallExpressOrderCargoExample();
        cargoExample.or().andExpressOrderIdEqualTo(expressOrderId);
        return cargoMapper.selectOneByExample(cargoExample);
    }


    public int add(LitemallExpressOrderCargo cargo) {
        return cargoMapper.insert(cargo);
    }
}
