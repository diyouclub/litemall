package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallCommissionResult;
import org.linlinjava.litemall.db.domain.LitemallUser;
import org.linlinjava.litemall.db.service.LitemallCommissionResultService;
import org.linlinjava.litemall.db.service.LitemallOrderService;
import org.linlinjava.litemall.db.service.LitemallUserService;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户服务
 */
@RestController
@RequestMapping("/wx/user")
@Validated
public class WxUserController {
    private final Log logger = LogFactory.getLog(WxUserController.class);

    @Autowired
    private LitemallOrderService orderService;
    @Autowired
    private LitemallUserService userService;
    @Autowired
    private LitemallCommissionResultService litemallCommissionResultService;

    /**
     * 用户个人页面数据
     * <p>
     * 目前是用户订单统计信息
     *
     * @param userId 用户ID
     * @return 用户个人页面数据
     */
    @GetMapping("index")
    public Object list(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("order", orderService.orderInfo(userId));

        LitemallUser litemallUser = userService.findById(userId);
        Map mapUser = new HashMap();
        mapUser.put("invite_url",litemallUser.getInviteUrl());
        mapUser.put("agency_level",litemallUser.getAgencyLevel());
        data.put("user", mapUser);

        //查询省级用户
        LitemallUser userProvince = userService.getProvince1stUser(userId);
        Map mapUpUser = new HashMap();
        mapUpUser.put("name",userProvince.getNickname());
        mapUpUser.put("mobile",userProvince.getMobile());
        data.put("upUser", mapUpUser);


        return ResponseUtil.ok(data);
    }

    /**
     * 用户子级用户数据
     * <p>
     *
     * @param userId 用户ID
     * @return 用户个人页面数据
     */
    @GetMapping("subUserList")
    public Object userlist(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        Map<Object, Object> data = new HashMap<Object, Object>();

        List<LitemallUser> litemallUsers = userService.findUserListByPid(userId);
        data.put("userList", litemallUsers);

        return ResponseUtil.ok(data);
    }

    /**
     * 用户收益数据
     * <p>
     *
     * @param userId 用户ID
     * @return 用户个人页面数据
     */
    @GetMapping("commissionList")
    public Object commissionList(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        Map<Object, Object> data = new HashMap<Object, Object>();


        List<LitemallCommissionResult> lstCommissionFee = litemallCommissionResultService.findByUserId(userId);
        data.put("commission",lstCommissionFee);


        return ResponseUtil.ok(data);
    }
    @GetMapping("info")
    public Object getInfo(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        Map<Object, Object> data = new HashMap<Object, Object>();

        LitemallUser litemallUser=userService.findById(userId);
        data.put("info",litemallUser);
        return ResponseUtil.ok(data);
    }

}