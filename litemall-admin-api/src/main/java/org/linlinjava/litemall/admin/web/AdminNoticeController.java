package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.annotation.LoginAdmin;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallNotice;
import org.linlinjava.litemall.db.service.LitemallNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/notice")
@Validated
public class AdminNoticeController {
    private final Log logger = LogFactory.getLog(AdminNoticeController.class);

    @Autowired
    private LitemallNoticeService noticeService;

    //验证参数不为空
    private Object validate(LitemallNotice notice) {
        String author = notice.getAuthor();
        if (StringUtils.isEmpty(author)) {
            return ResponseUtil.badArgument();
        }
        String name = notice.getNoticeName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        String content = notice.getNoticeContent();
        if (StringUtils.isEmpty(content)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @PostMapping("/create")
    public Object create(@LoginAdmin Integer adminId, @RequestBody LitemallNotice notice) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(notice);
        if (error != null) {
            return error;
        }
        noticeService.add(notice);

        return ResponseUtil.ok(notice);
    }

    @GetMapping("/list")
    public Object list(@LoginAdmin Integer adminId,
                       String notice_name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "create_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallNotice> noticeList = noticeService.querySelective(notice_name, page, limit, sort, order);
        int total = noticeService.countSelective(notice_name, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", noticeList);

        return ResponseUtil.ok(data);
    }


    @PostMapping("/update")
    public Object update(@LoginAdmin Integer adminId, @RequestBody LitemallNotice notice) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(notice);
        if (error != null) {
            return error;
        }
        if (noticeService.updateById(notice) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(notice);
    }

    @PostMapping("/delete")
    public Object delete(@LoginAdmin Integer adminId, @RequestBody LitemallNotice notice) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Integer id = notice.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        noticeService.deleteById(id);
        return ResponseUtil.ok();
    }

}
