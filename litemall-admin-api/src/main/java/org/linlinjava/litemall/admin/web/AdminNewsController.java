package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.annotation.LoginAdmin;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallNews;
import org.linlinjava.litemall.db.service.LitemallNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/news")
@Validated
public class AdminNewsController {
    private final Log logger = LogFactory.getLog(AdminNewsController.class);

    @Autowired
    private LitemallNewsService newsService;

    @GetMapping("/list")
    public Object list(@LoginAdmin Integer adminId,
                       String title, String subtitle,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallNews> newsList = newsService.querySelective(title, subtitle, page, limit, sort, order);
        int total = newsService.countSelective(title, subtitle, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", newsList);

        return ResponseUtil.ok(data);
    }

    private Object validate(LitemallNews news) {
        String title = news.getTitle();
        if (StringUtils.isEmpty(title)) {
            return ResponseUtil.badArgument();
        }
        String content = news.getContent();
        if (StringUtils.isEmpty(content)) {
            return ResponseUtil.badArgument();
        }
        BigDecimal price = news.getPrice();
        if (price == null) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @PostMapping("/create")
    public Object create(@LoginAdmin Integer adminId, @RequestBody LitemallNews news) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(news);
        if (error != null) {
            return error;
        }
        newsService.add(news);
        return ResponseUtil.ok(news);
    }

    @GetMapping("/read")
    public Object read(@LoginAdmin Integer adminId, @NotNull Integer id) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        LitemallNews news = newsService.findById(id);
        return ResponseUtil.ok(news);
    }

    @PostMapping("/update")
    public Object update(@LoginAdmin Integer adminId, @RequestBody LitemallNews news) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(news);
        if (error != null) {
            return error;
        }
        if (newsService.updateById(news) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(news);
    }

    @PostMapping("/delete")
    public Object delete(@LoginAdmin Integer adminId, @RequestBody LitemallNews news) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        newsService.deleteById(news.getId());
        return ResponseUtil.ok();
    }

}
