package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallBankcardAuthCallMapper;
import org.linlinjava.litemall.db.dao.LitemallBankcardMapper;
import org.linlinjava.litemall.db.domain.LitemallBankcard;
import org.linlinjava.litemall.db.domain.LitemallBankcardAuthCallWithBLOBs;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author : fujue
 * @version V1.0
 * @Project: litemall
 * @Package org.linlinjava.litemall.db.service
 * @Description: TODO
 * @date Date : 2019年02月17日 16:09
 */
@Service
public class LitemallBankcardService {
    @Resource
    private LitemallBankcardMapper litemallBankcardMapper;

    public int add(LitemallBankcard card) {
        card.setAddTime(LocalDateTime.now());
        card.setUpdateTime(LocalDateTime.now());
        return litemallBankcardMapper.insertSelective(card);
    }
}
