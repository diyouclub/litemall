package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.express.SfExpressService;
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

    @Autowired
    SfExpressService sfExpressService;
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

        try {
            String respXml  = sfExpressService.execOrderSearch( "800669400640887922");
           // String respXml  = "<?xml version='1.0' encoding='UTF-8'?><Response service=\"OrderSearchService\"><Head>ERR</Head><ERROR code=\"6150\">找不到该订单</ERROR></Response>";

            if (respXml != null) {
                System.out.println("--------------------------------------");
                System.out.println("返回报文: "+ respXml);
                System.out.println("--------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseUtil.ok("hello world, this is wx service");
    }

}