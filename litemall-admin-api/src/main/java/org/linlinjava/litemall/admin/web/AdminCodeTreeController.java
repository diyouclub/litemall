package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallCodeTree;
import org.linlinjava.litemall.db.service.LitemallCodeTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/admin/codeTree")
@Validated
public class AdminCodeTreeController {
    private final Log logger = LogFactory.getLog(AdminCodeTreeController.class);

    @Autowired
    private LitemallCodeTreeService codeTreeService;

    @GetMapping("/getCodeTree")
    public Object getCodeTree(String code_type){
        if(StringUtils.isEmpty(code_type)){
            return ResponseUtil.badArgument();
        }
        LitemallCodeTree litemallCodeTree=codeTreeService.findByCodeType(code_type);
        List<LitemallCodeTree> litemallCodeTrees=new ArrayList<>();
        if(!StringUtils.isEmpty(litemallCodeTree)) {
             litemallCodeTrees = codeTreeService.findByPId(litemallCodeTree.getId());
        }
        return ResponseUtil.ok(litemallCodeTrees);

    }


}
