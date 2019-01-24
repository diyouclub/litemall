package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.service.UserTokenManager;
import org.linlinjava.litemall.core.util.RegexUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallUser;
import org.linlinjava.litemall.db.service.LitemallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.linlinjava.litemall.admin.util.AdminResponseCode.ADMIN_INVALID_NAME;
import static org.linlinjava.litemall.admin.util.AdminResponseCode.ADMIN_INVALID_PASSWORD;

@RestController
@RequestMapping("/user/user")
@Validated
public class UserUserController {
    private final Log logger = LogFactory.getLog(UserUserController.class);

    @Autowired
    private LitemallUserService userService;

    @GetMapping("/info")
    public Object info(String token) {
        Integer userId = UserTokenManager.getUserId(token);
        if (userId == null) {
            return ResponseUtil.badArgumentValue();
        }
        LitemallUser user = userService.findById(userId);
        if (user == null) {
            return ResponseUtil.badArgumentValue();
        }

        Map<String, Object> data = new HashMap<>();
        data.put("name", user.getUsername());
        data.put("avatar", user.getAvatar());

        // 目前roles不支持，这里简单设置admin
        List<String> roles = new ArrayList<>();
        roles.add("user");
        data.put("roles", roles);
        data.put("introduction", "user introduction");
        return ResponseUtil.ok(data);
    }



    private Object validate(LitemallUser user) {
        String name = user.getUsername();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isUsername(name)) {
            return ResponseUtil.fail(ADMIN_INVALID_NAME, "管理员名称不符合规定");
        }
        String password = user.getPassword();
        if (StringUtils.isEmpty(password) || password.length() < 6) {
            return ResponseUtil.fail(ADMIN_INVALID_PASSWORD, "管理员密码长度不能小于6");
        }
        return null;
    }

}
