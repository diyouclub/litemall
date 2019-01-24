package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallAccount;
import org.linlinjava.litemall.db.domain.LitemallAccountDetail;
import org.linlinjava.litemall.db.domain.LitemallCommissionResult;
import org.linlinjava.litemall.db.domain.LitemallOrder;
import org.linlinjava.litemall.db.service.LitemallAccountDetailService;
import org.linlinjava.litemall.db.service.LitemallAccountService;
import org.linlinjava.litemall.db.service.LitemallCommissionResultService;
import org.linlinjava.litemall.db.service.LitemallOrderService;
import org.linlinjava.litemall.wx.service.PyramidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 测试服务
 */
@RestController
@RequestMapping("/wx/index")
public class WxIndexController {
    private final Log logger = LogFactory.getLog(WxIndexController.class);
    @Autowired
    PyramidService pyramidService;
    @Autowired
    private LitemallCommissionResultService litemallCommissionResultService;
    @Autowired
    private LitemallAccountService litemallAccountService;
    @Autowired
    private LitemallAccountDetailService litemallAccountDetailService;
    @Autowired
    private LitemallOrderService orderService;
    /**
     * 测试数据
     *
     * @return 测试数据
     */
    @RequestMapping("/index")
    public Object index() {

//        int[] oids= {35,36,37,38,39,40,41,43,45,46};
//        for (int y = 0 ; y<oids.length ; y++) {
//
//            LitemallOrder order = orderService.findById(oids[y]);
//            List lst = pyramidService.calcSell(order.getActualPrice(), order.getUserId());
//            for (int i = 0 ; i < lst.size() ; i++) {
//
//                Map map = (Map) lst.get(i);
//                LitemallCommissionResult litemallCommissionResult = new LitemallCommissionResult();
//                litemallCommissionResult.setOrderId(order.getId());
//                litemallCommissionResult.setFee((BigDecimal) map.get("fee"));
//                litemallCommissionResult.setUserId((Integer) map.get("user_id"));
//                litemallCommissionResult.setUserName((String) map.get("user_name"));
//                litemallCommissionResult.setScale(new BigDecimal(map.get("scale").toString()).divide(new BigDecimal("100")));
//                litemallCommissionResult.setRuleDesc((String) map.get("desc"));
//                litemallCommissionResult.setRuleName((String) map.get("name"));
//                litemallCommissionResult.setCommissionType(String.valueOf(1));
//
//                litemallCommissionResultService.add(litemallCommissionResult);
//                // 更新账户
//                LitemallAccount litemallAccount = litemallAccountService.addMoney(litemallCommissionResult.getFee(),litemallCommissionResult.getUserId());
//                // 记录账户明细
//                LitemallAccountDetail litemallAccountDetail = litemallAccountDetailService.add(litemallAccount,litemallCommissionResult.getFee(), String.valueOf(1),litemallCommissionResult.getId());
//            }
//
//        }

        // pyramidService.calcSell(new BigDecimal("168.00"),14);
       // pyramidService.calcAgency((byte) 2,new BigDecimal("300"),64);
        //pyramidService.calcAgency((byte) 3,new BigDecimal("6800"),28);
        return ResponseUtil.ok("hello world, this is wx service");
    }

}