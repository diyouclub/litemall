package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.annotation.LoginAdmin;
import org.linlinjava.litemall.admin.annotation.LoginUser;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallAccount;
import org.linlinjava.litemall.db.domain.LitemallCommissionResult;
import org.linlinjava.litemall.db.domain.LitemallMoneyApply;
import org.linlinjava.litemall.db.service.LitemallAccountService;
import org.linlinjava.litemall.db.service.LitemallCommissionResultService;
import org.linlinjava.litemall.db.service.LitemallMoneyApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/moneyApply")
@Validated
public class AdminMoneyApplyController {
    private final Log logger = LogFactory.getLog(AdminMoneyApplyController.class);

    @Autowired
    private LitemallCommissionResultService commissionResultService;
    @Autowired
    private LitemallMoneyApplyService litemallMoneyApplyService;
    @Autowired
    private LitemallAccountService litemallAccountService;


    @GetMapping("/list")
    public Object list(@LoginAdmin Integer adminId,
            String userName,
                       String startTime,
                       String endTime,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallMoneyApply> commissionResults = litemallMoneyApplyService.queryAll(startTime,endTime,userName, page, limit);
        int total = litemallMoneyApplyService.countByAll(startTime,endTime,adminId, page, limit);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("commissionResults", commissionResults);

        return ResponseUtil.ok(data);
    }

    @PostMapping("/audit")
    public Object apply(@LoginAdmin Integer adminId,@RequestBody String body){
        String auditFlag = JacksonUtil.parseString(body, "auditFlag");
        int applyId = JacksonUtil.parseInteger(body, "applyId");
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        if(StringUtils.isEmpty(auditFlag)||StringUtils.isEmpty(applyId)){
            return ResponseUtil.badArgumentValue();
        }
        LitemallMoneyApply lmas=litemallMoneyApplyService.findById(applyId);
        lmas.setAuditFlag(auditFlag);
        lmas.setApplyTime(LocalDateTime.now());
        lmas.setAuditUser(adminId);
        litemallMoneyApplyService.updateById(lmas);
        if(auditFlag.equals("2")){
            litemallAccountService.addMoney(lmas.getMoney(),lmas.getApplyUser());
        }
        return ResponseUtil.ok();

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
