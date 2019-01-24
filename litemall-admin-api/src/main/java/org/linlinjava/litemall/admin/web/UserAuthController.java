package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.annotation.LoginUser;
import org.linlinjava.litemall.admin.dao.UserToken;
import org.linlinjava.litemall.admin.service.UserTokenManager;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.util.bcrypt.BCryptPasswordEncoder;
import org.linlinjava.litemall.db.domain.LitemallUser;
import org.linlinjava.litemall.db.service.LitemallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.linlinjava.litemall.admin.util.AdminResponseCode.ADMIN_INVALID_ACCOUNT;

@RestController
@RequestMapping("/user/login")
@Validated
public class UserAuthController {
    private final Log logger = LogFactory.getLog(UserAuthController.class);

    @Autowired
    private LitemallUserService userService;

    /*
     *  { username : value, password : value }
     */
    @PostMapping("/login")
    public Object login(@RequestBody String body) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseUtil.badArgument();
        }

        List<LitemallUser> adminList = userService.findUser(username);
        Assert.state(adminList.size() < 2, "同一个用户名存在两个账户");
        if (adminList.size() == 0) {
            return ResponseUtil.badArgumentValue();
        }
        LitemallUser user = adminList.get(0);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPassword())) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "账号密码不对");
        }

        Integer userId = user.getId();
        // token
        UserToken userToken = UserTokenManager.generateToken(userId);

        return ResponseUtil.ok(userToken.getToken());
    }

    /*
     *
     */
    @PostMapping("/logout")
    public Object login(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        return ResponseUtil.ok();
    }
}
