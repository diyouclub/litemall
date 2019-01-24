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
import org.linlinjava.litemall.db.service.LitemallAccountService;
import org.linlinjava.litemall.db.service.LitemallCommissionResultService;
import org.linlinjava.litemall.db.service.LitemallMoneyApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/moneyApply")
@Validated
public class UserMoneyApplyController {
    private final Log logger = LogFactory.getLog(UserMoneyApplyController.class);
    @Autowired
    private LitemallMoneyApplyService litemallMoneyApplyService;


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

        List<LitemallMoneyApply> litemallMoneyApplies =litemallMoneyApplyService.queryByUserId(startTime,endTime,userId, page, limit);
        int total = litemallMoneyApplyService.countByUserId(startTime,endTime,userId, page, limit);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("litemallMoneyApplies", litemallMoneyApplies);

        return ResponseUtil.ok(data);
    }


}
