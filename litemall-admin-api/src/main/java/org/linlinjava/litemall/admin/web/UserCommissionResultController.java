package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.annotation.LoginUser;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallAccount;
import org.linlinjava.litemall.db.domain.LitemallCommissionResult;
import org.linlinjava.litemall.db.domain.LitemallMoneyApply;
import org.linlinjava.litemall.db.domain.LitemallUser;
import org.linlinjava.litemall.db.service.LitemallAccountService;
import org.linlinjava.litemall.db.service.LitemallCommissionResultService;
import org.linlinjava.litemall.db.service.LitemallMoneyApplyService;
import org.linlinjava.litemall.db.service.LitemallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/Commission")
@Validated
public class UserCommissionResultController {
    private final Log logger = LogFactory.getLog(UserCommissionResultController.class);

    @Autowired
    private LitemallCommissionResultService commissionResultService;
    @Autowired
    private LitemallMoneyApplyService litemallMoneyApplyService;
    @Autowired
    private LitemallAccountService litemallAccountService;
    @Autowired
    private LitemallUserService litemallUserService;


    @GetMapping("/list")
    public Object list(@LoginUser Integer userId,
                       String startTime,
                       String endTime,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallCommissionResult> commissionResults = null;
        try {
            commissionResults = commissionResultService.queryByUserId(startTime,endTime,userId, page, limit);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int total = commissionResultService.countByUserId(startTime,endTime,userId, page, limit);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("commissionResults", commissionResults);

        return ResponseUtil.ok(data);
    }


    @PostMapping("/apply")
    public Object apply(@LoginUser Integer userId,@RequestBody String body){
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        int accountId = JacksonUtil.parseInteger(body, "accountId");
        String brokerage = JacksonUtil.parseString(body, "brokerage");
        String money = JacksonUtil.parseString(body, "money");
        String finally_money = JacksonUtil.parseString(body, "finally_money");
        String band_name = JacksonUtil.parseString(body, "band_name");
        String bank_card = JacksonUtil.parseString(body, "bank_card");
        if(StringUtils.isEmpty(money)||StringUtils.isEmpty(brokerage)||StringUtils.isEmpty(finally_money)){
            return ResponseUtil.badArgumentValue();
        }
        LitemallMoneyApply lmas=litemallMoneyApplyService.findByUser(userId);
        if(lmas!=null) {
            if (lmas.getAuditFlag().equals("0")) {
                return ResponseUtil.applyFailed();
            }
        }
        LitemallUser litemallUser=litemallUserService.findById(userId);
        LitemallMoneyApply litemallMoneyApply=new LitemallMoneyApply();
        litemallMoneyApply.setAccountId(accountId);
        litemallMoneyApply.setApplyUser(userId);
        litemallMoneyApply.setMoney(new BigDecimal(money));
        litemallMoneyApply.setBrokerage(new BigDecimal(brokerage));
        litemallMoneyApply.setFinallyMoney(new BigDecimal(finally_money));
        litemallMoneyApply.setApplyTime(LocalDateTime.now());
        litemallMoneyApply.setApplyUserName(litemallUser.getNickname());
        litemallMoneyApply.setBandName(band_name);
        litemallMoneyApply.setBankCard(bank_card);
        litemallMoneyApplyService.add(litemallMoneyApply);
        litemallAccountService.subtractMoney(new BigDecimal(money),userId);
        return ResponseUtil.ok(litemallMoneyApply);

    }
    @GetMapping("/account")
    public Object account(@LoginUser Integer userId){
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        LitemallAccount lmas=litemallAccountService.findByUser(userId);
        return ResponseUtil.ok(lmas);

    }

}
