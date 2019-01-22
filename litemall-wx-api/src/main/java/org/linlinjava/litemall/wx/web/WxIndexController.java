package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.wx.service.PyramidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 测试服务
 */
@RestController
@RequestMapping("/wx/index")
public class WxIndexController {
    private final Log logger = LogFactory.getLog(WxIndexController.class);
    @Autowired
    PyramidService pyramidService;
    /**
     * 测试数据
     *
     * @return 测试数据
     */
    @RequestMapping("/index")
    public Object index() {
        pyramidService.calcSell(new BigDecimal("168.00"),14);
        //pyramidService.calcAgency((byte) 2,new BigDecimal("300"),28);
        //pyramidService.calcAgency((byte) 3,new BigDecimal("6800"),28);
        return ResponseUtil.ok("hello world, this is wx service");
    }

}