package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.annotation.LoginAdmin;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallFriendshipLink;
import org.linlinjava.litemall.db.service.LitemallFriendshipLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/friendshipLink")
@Validated
public class AdminFriendshipLinkController {
    private final Log logger = LogFactory.getLog(AdminFriendshipLinkController.class);

    @Autowired
    private LitemallFriendshipLinkService friendshipLinkService;

    @GetMapping("/list")
    public Object list(@LoginAdmin Integer adminId,
                       String link_name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        List<LitemallFriendshipLink> friendshipLinkList=friendshipLinkService.querySelective(link_name, page, limit, sort, order);

        int total = friendshipLinkService.countSelective(link_name, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", friendshipLinkList);
        return ResponseUtil.ok(data);
    }

    private Object validate(LitemallFriendshipLink friendshipLink) {
        String linkName = friendshipLink.getLinkName();
        if (StringUtils.isEmpty(linkName)) {
            return ResponseUtil.badArgument();
        }
        String friendType = friendshipLink.getFriendType();
        if (StringUtils.isEmpty(friendType)) {
            return ResponseUtil.badArgument();
        }

        String linkUrl = friendshipLink.getLinkUrl();
        if (StringUtils.isEmpty(linkUrl)) {
            return ResponseUtil.badArgument();
        }

//        String picUrl = friendshipLink.getPicUrl();
//        if (StringUtils.isEmpty(picUrl)) {
//            return ResponseUtil.badArgument();
//        }

        Integer sortOrder = friendshipLink.getSortOrder();
        if (sortOrder==null) {
            return ResponseUtil.badArgument();
        }

        return null;
    }

    @PostMapping("/create")
    public Object create(@LoginAdmin Integer adminId, @RequestBody LitemallFriendshipLink friendshipLink) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(friendshipLink);
        if (error != null) {
            return error;
        }
        friendshipLinkService.add(friendshipLink);

        return ResponseUtil.ok(friendshipLink);
    }

    @GetMapping("/read")
    public Object read(@LoginAdmin Integer adminId, @NotNull Integer id) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        LitemallFriendshipLink friendshipLink=friendshipLinkService.findById(id);

        return ResponseUtil.ok(friendshipLink);
    }

    @PostMapping("/update")
    public Object update(@LoginAdmin Integer adminId, @RequestBody LitemallFriendshipLink friendshipLink) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(friendshipLink);
        if (error != null) {
            return error;
        }
        if (friendshipLinkService.updateById(friendshipLink) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(friendshipLink);
    }

    @PostMapping("/delete")
    public Object delete(@LoginAdmin Integer adminId, @RequestBody LitemallFriendshipLink friendshipLink) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        friendshipLinkService.deleteById(friendshipLink.getId());
        return ResponseUtil.ok();
    }

}
