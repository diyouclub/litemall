package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.annotation.LoginAdmin;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallTabInfoClass;
import org.linlinjava.litemall.db.service.LitemallTabInfoClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/infoClass")
@Validated
public class AdminTabInfoClassController {
    private final Log logger = LogFactory.getLog(AdminTabInfoClassController.class);

    @Autowired
    private LitemallTabInfoClassService tabInfoClassService;

    @GetMapping("/list")
    public Object list(@LoginAdmin Integer adminId,
                       String cls_name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "id") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        List<LitemallTabInfoClass> infoClasses = tabInfoClassService.querySelective(cls_name, page, limit, sort, order);

        int total = tabInfoClassService.countSelective(cls_name, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", infoClasses);
        return ResponseUtil.ok(data);
    }

    private Object validate(LitemallTabInfoClass tabInfoClass) {
        String clsType = tabInfoClass.getClsType();
//        if (StringUtils.isEmpty(clsType)) {
//            return ResponseUtil.badArgument();
//        }
        Integer type = tabInfoClass.getType();
//        if (type == null) {
//            return ResponseUtil.badArgument();
//        }
        String clsName = tabInfoClass.getClsName();
        if (StringUtils.isEmpty(clsName)) {
            return ResponseUtil.badArgument();
        }
        String clsIcon = tabInfoClass.getClsIcon();
        if (StringUtils.isEmpty(clsIcon)) {
            return ResponseUtil.badArgument();
        }
//        Integer parentId = tabInfoClass.getParentId();
//        if (parentId == null) {
//            return ResponseUtil.badArgument();
//        }
        Integer showIndex = tabInfoClass.getShowIndex();
        if (showIndex == null) {
            return ResponseUtil.badArgument();
        }
        Integer indexLimit = tabInfoClass.getIndexLimit();
        if (indexLimit == null) {
            return ResponseUtil.badArgument();
        }

        return null;
    }

    @PostMapping("/create")
    public Object create(@LoginAdmin Integer adminId, @RequestBody LitemallTabInfoClass tabInfoClass) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
//        Object error = validate(tabInfoClass);
//        if (error != null) {
//            return error;
//        }
        tabInfoClassService.add(tabInfoClass);

        return ResponseUtil.ok(tabInfoClass);
    }

    @GetMapping("/read")
    public Object read(@LoginAdmin Integer adminId, @NotNull Integer id) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        LitemallTabInfoClass tabInfoClass = tabInfoClassService.findById(id);
        return ResponseUtil.ok(tabInfoClass);
    }

    @PostMapping("/update")
    public Object update(@LoginAdmin Integer adminId, @RequestBody LitemallTabInfoClass litemallTabInfoClass) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(litemallTabInfoClass);
        if (error != null) {
            return error;
        }
        if (tabInfoClassService.updateById(litemallTabInfoClass) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(litemallTabInfoClass);
    }

    @PostMapping("/delete")
    public Object delete(@LoginAdmin Integer adminId, @RequestBody LitemallTabInfoClass litemallTabInfoClass) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        tabInfoClassService.deleteById(litemallTabInfoClass.getId());
        return ResponseUtil.ok();
    }

}
