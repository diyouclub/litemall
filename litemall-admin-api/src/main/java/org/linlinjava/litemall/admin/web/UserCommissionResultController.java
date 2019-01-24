package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.annotation.LoginUser;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallCommissionResult;
import org.linlinjava.litemall.db.service.LitemallCommissionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

        List<LitemallCommissionResult> commissionResults = commissionResultService.queryByUserId(startTime,endTime,userId, page, limit);
        int total = commissionResultService.countByUserId(startTime,endTime,userId, page, limit);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("commissionResults", commissionResults);

        return ResponseUtil.ok(data);
    }

}
