package org.linlinjava.litemall.admin.web;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.annotation.LoginAdmin;
import org.linlinjava.litemall.admin.dto.TabInfoDTO;
import org.linlinjava.litemall.admin.util.MultiRequestBody;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallTabInfo;
import org.linlinjava.litemall.db.domain.LitemallTabInfoContent;
import org.linlinjava.litemall.db.domain.LitemallTabInfoTag;
import org.linlinjava.litemall.db.domain.LitemallTabInfoTagRelated;
import org.linlinjava.litemall.db.service.LitemallTabInfoContentService;
import org.linlinjava.litemall.db.service.LitemallTabInfoService;
import org.linlinjava.litemall.db.service.LitemallTabInfoTagRelatedService;
import org.linlinjava.litemall.db.service.LitemallTabInfoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/tabInfo")
@Validated
public class AdminTabInfoController {
    private final Log logger = LogFactory.getLog(AdminTabInfoController.class);

    @Autowired
    private LitemallTabInfoService tabInfoService;
    @Autowired
    private LitemallTabInfoContentService tabInfoContentService;
    @Autowired
    private LitemallTabInfoTagService tabInfoTagService;
    @Autowired
    private LitemallTabInfoTagRelatedService tabInfoTagRelatedService;

    @GetMapping("/list")
    public Object list(
            String info_title,
            Integer cls_id,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @Sort @RequestParam(defaultValue = "create_time") String sort,
            @Order @RequestParam(defaultValue = "desc") String order) {

        List<LitemallTabInfo> tabInfos = tabInfoService.querySelective(info_title, page, limit, sort, order, cls_id);
        List<TabInfoDTO> tabInfoDTOS = new ArrayList<>();
        if (tabInfos != null) {
            for (LitemallTabInfo xx : tabInfos) {
                TabInfoDTO tabInfoDTO = new TabInfoDTO();

                if (xx.getScope() == 1) {//指定用户是有手机号码的
                    tabInfoDTO.setAssignPhone(xx.getAssignPhone());
                }

                tabInfoDTO.setInfoId(xx.getInfoId());
                tabInfoDTO.setInfoShortTitle(xx.getInfoShortTitle());
                tabInfoDTO.setInfoTitle(xx.getInfoTitle());
                tabInfoDTO.setInfoDescription(xx.getInfoDescription());
                tabInfoDTO.setInfoMainImg(xx.getInfoMainImg());
                tabInfoDTO.setClsId(xx.getClsId());
                tabInfoDTO.setShowIndex(xx.getShowIndex());
                tabInfoDTO.setScope(xx.getScope());
                tabInfoDTO.setTopRank(xx.getTopRank());
                tabInfoDTO.setOpenRelated(xx.getOpenRelated());

                LitemallTabInfoContent tabInfoContent = tabInfoContentService.findContent(xx.getInfoId());//资讯内容
                if (tabInfoContent != null) {
                    tabInfoDTO.setContentId(tabInfoContent.getId());
                    tabInfoDTO.setAuthor(tabInfoContent.getAuthor());
                    tabInfoDTO.setContent(tabInfoContent.getContent());
                }

                LitemallTabInfoTag tabInfoTag = tabInfoTagService.findTagName(xx.getInfoId());//资讯标签
                if (tabInfoTag != null) {
                    tabInfoDTO.setName(tabInfoTag.getName());
                }
                tabInfoDTOS.add(tabInfoDTO);
            }
        }

        int total = tabInfoService.countSelective(info_title, page, limit, sort, order, cls_id);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", tabInfoDTOS);
        return ResponseUtil.ok(data);
    }

    //验证参数不为空
    private Object validate(LitemallTabInfo tabInfo) {
        String infoTitle = tabInfo.getInfoTitle();
        if (StringUtils.isEmpty(infoTitle)) {
            return ResponseUtil.badArgument();
        }
        String shortTitle = tabInfo.getInfoShortTitle();
        if (StringUtils.isEmpty(shortTitle)) {
            return ResponseUtil.badArgument();
        }
        String description = tabInfo.getInfoDescription();
        if (StringUtils.isEmpty(description)) {
            return ResponseUtil.badArgument();
        }
        String mainImg = tabInfo.getInfoMainImg();
        if (StringUtils.isEmpty(mainImg)) {
            return ResponseUtil.badArgument();
        }
        Integer clsId = tabInfo.getClsId();
        if (clsId == null) {
            return ResponseUtil.badArgument();
        }
        Integer showIndex = tabInfo.getShowIndex();
        if (showIndex == null) {
            return ResponseUtil.badArgument();
        }
        Integer scope = tabInfo.getScope();
        if (scope == null) {
            return ResponseUtil.badArgument();
        }
//        String tags = tabInfo.getTags();
//        if (StringUtils.isEmpty(tags)) {
//            return ResponseUtil.badArgument();
//        }
        Integer openRelated = tabInfo.getOpenRelated();
        if (openRelated == null) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @PostMapping("/create")
    @ResponseBody
    public Object create(@LoginAdmin Integer adminId, @MultiRequestBody LitemallTabInfo tabInfo, @MultiRequestBody LitemallTabInfoContent content, @MultiRequestBody LitemallTabInfoTag tagName) {
        System.out.println(tabInfo);
        System.out.println(tagName);
        System.out.println(content);
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(tabInfo);
        if (error != null) {
            return error;
        }

        //保存资讯表
        Integer info_id = tabInfoService.add(tabInfo);

        content.setInfoId(info_id);

        //保存资讯内容表
        tabInfoContentService.add(content);

        String tag_name = tagName.getName();

        if (!StringUtils.isEmpty(tag_name)) {
            /*遍历出标签的名字，保存到标签表。返回值标签的id ，通过 标签的id 和 资讯主表id 保存标签关联表*/
            LitemallTabInfoTag tabInfoTag = new LitemallTabInfoTag();
            if (tag_name.indexOf(",") > -1) {//有多个标签
                String[] array = tag_name.split("\\,");
                for (int i = 0; i < array.length; i++) {

                    //保存标签表
                    String name = array[i];
                    LitemallTabInfoTag litemallTabInfoTag = tabInfoTagService.findByName(name);
                    if (litemallTabInfoTag != null) {//该标签存在

                        LitemallTabInfoTagRelated litemallTabInfoTagRelated = tabInfoTagRelatedService.findTagExist(tabInfo.getInfoId(), litemallTabInfoTag.getId());
                        if (litemallTabInfoTagRelated == null) {//不能重复关联相同的标签

                            //保存标签关联表
                            LitemallTabInfoTagRelated tabInfoTagRelated = new LitemallTabInfoTagRelated();
                            tabInfoTagRelated.setInfoId(info_id);//这是资讯表的id
                            tabInfoTagRelated.setTagId(litemallTabInfoTag.getId());//这是标签表的id
                            tabInfoTagRelatedService.add(tabInfoTagRelated);
                        }

                    } else {//保存标签和标签关联表
                        int tag_id = tabInfoTagService.add(tabInfoTag, name);//标签表id
                        LitemallTabInfoTagRelated tabInfoTagRelated = new LitemallTabInfoTagRelated();
                        tabInfoTagRelated.setInfoId(info_id);//这是资讯表的id
                        tabInfoTagRelated.setTagId(tag_id);//这是标签表的id
                        tabInfoTagRelatedService.add(tabInfoTagRelated);
                    }
                }
            } else {//只有一个标签
                LitemallTabInfoTag litemallTabInfoTag = tabInfoTagService.findByName(tag_name);
                if (litemallTabInfoTag != null) {//该标签存在

                    //保存标签关联表
                    LitemallTabInfoTagRelated tabInfoTagRelated = new LitemallTabInfoTagRelated();
                    tabInfoTagRelated.setInfoId(info_id);//这是资讯表的id
                    tabInfoTagRelated.setTagId(litemallTabInfoTag.getId());//这是标签表的id
                    tabInfoTagRelatedService.add(tabInfoTagRelated);
                } else {//保存标签和标签关联表
                    int tag_id = tabInfoTagService.add(tabInfoTag, tag_name);//标签表id

                    //保存标签关联表
                    LitemallTabInfoTagRelated tabInfoTagRelated = new LitemallTabInfoTagRelated();
                    tabInfoTagRelated.setInfoId(info_id);//这是资讯表的id
                    tabInfoTagRelated.setTagId(tag_id);//这是标签表的id
                    tabInfoTagRelatedService.add(tabInfoTagRelated);
                }
            }
        }
        return ResponseUtil.ok(tabInfo);
    }

//    /**
//     * 编辑查看接口
//     */
//    @GetMapping("/read")
//    public Object read(@LoginAdmin Integer adminId, @NotNull Integer id) {
//        if (adminId == null) {
//            return ResponseUtil.unlogin();
//        }
//        JSONObject jsonObject = new JSONObject();
//
//        LitemallTabInfo litemallTabInfo = tabInfoService.findById(id);
//        LitemallTabInfoContent litemallTabInfoContent = tabInfoContentService.findContent(id);
//        List<LitemallTabInfoTag> litemallTabInfoTag = tabInfoTagService.findInfoTag(id);
////        LitemallTabInfoTag litemallTabInfoTag = tabInfoTagService.findInfoTag(id);
//        jsonObject.put("tabInfo", litemallTabInfo);
//        jsonObject.put("content", litemallTabInfoContent);
//        jsonObject.put("tagName", litemallTabInfoTag);
//        return ResponseUtil.ok(jsonObject);
//    }

    @PostMapping("/update")
    public Object update(@LoginAdmin Integer adminId, @MultiRequestBody LitemallTabInfo tabInfo, @MultiRequestBody LitemallTabInfoContent content, @MultiRequestBody LitemallTabInfoTag tagName) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(tabInfo);
        if (error != null) {
            return error;
        }
        if (tabInfoService.updateById(tabInfo) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        if (tabInfoContentService.updateById(content) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        /*首先 获得资讯的id,查出他关联表中关联的数据，全部删除。然后接收从前端传入的新标签，操作资讯关联表，标签表（如果标签表中存在标签名相同的，只新增关联表）*/
        List<LitemallTabInfoTagRelated> tagRelateds = tabInfoTagRelatedService.selectAllTag(tabInfo.getInfoId());
        if (tagRelateds != null) {
            for (LitemallTabInfoTagRelated xx : tagRelateds) {
                tabInfoTagRelatedService.deleteById(xx.getId());
            }
        }

        String tag_name = tagName.getName();
        if (!StringUtils.isEmpty(tag_name)) {
            /*遍历出标签的名字，保存到标签表。返回值标签的id ，通过 标签的id 和 资讯主表id 保存标签关联表*/
            LitemallTabInfoTag tabInfoTag = new LitemallTabInfoTag();
            if (tag_name.indexOf(",") > -1) {//有多个标签
                String[] array = tag_name.split("\\,");
                for (int i = 0; i < array.length; i++) {

                    //保存标签表
                    String name = array[i];
//                    if (StringUtils){}
                    LitemallTabInfoTag litemallTabInfoTag = tabInfoTagService.findByName(name);
                    if (litemallTabInfoTag != null) {//该标签存在

                        LitemallTabInfoTagRelated litemallTabInfoTagRelated = tabInfoTagRelatedService.findTagExist(tabInfo.getInfoId(), litemallTabInfoTag.getId());
                        if (litemallTabInfoTagRelated == null) {//不能重复关联相同的标签

                            //保存标签关联表
                            LitemallTabInfoTagRelated tabInfoTagRelated = new LitemallTabInfoTagRelated();
                            tabInfoTagRelated.setInfoId(tabInfo.getInfoId());//这是资讯表的id
                            tabInfoTagRelated.setTagId(litemallTabInfoTag.getId());//这是标签表的id
                            tabInfoTagRelatedService.add(tabInfoTagRelated);
                        }

                    } else {//保存标签和标签关联表
                        int tag_id = tabInfoTagService.add(tabInfoTag, name);//标签表id
                        LitemallTabInfoTagRelated tabInfoTagRelated = new LitemallTabInfoTagRelated();
                        tabInfoTagRelated.setInfoId(tabInfo.getInfoId());//这是资讯表的id
                        tabInfoTagRelated.setTagId(tag_id);//这是标签表的id
                        tabInfoTagRelatedService.add(tabInfoTagRelated);
                    }
                }
            } else {//只有一个标签
                LitemallTabInfoTag litemallTabInfoTag = tabInfoTagService.findByName(tag_name);
                if (litemallTabInfoTag != null) {//该标签存在

                    //保存标签关联表
                    LitemallTabInfoTagRelated tabInfoTagRelated = new LitemallTabInfoTagRelated();
                    tabInfoTagRelated.setInfoId(tabInfo.getInfoId());//这是资讯表的id
                    tabInfoTagRelated.setTagId(litemallTabInfoTag.getId());//这是标签表的id
                    tabInfoTagRelatedService.add(tabInfoTagRelated);
                } else {//保存标签和标签关联表
                    int tag_id = tabInfoTagService.add(tabInfoTag, tag_name);//标签表id
                    LitemallTabInfoTagRelated tabInfoTagRelated = new LitemallTabInfoTagRelated();
                    tabInfoTagRelated.setInfoId(tabInfo.getInfoId());//这是资讯表的id
                    tabInfoTagRelated.setTagId(tag_id);//这是标签表的id
                    tabInfoTagRelatedService.add(tabInfoTagRelated);
                }
            }
        }
        return ResponseUtil.ok(tabInfo);
    }

    @PostMapping("/delete")
    public Object delete(@LoginAdmin Integer adminId, @RequestBody LitemallTabInfo tabInfo) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        tabInfoService.deleteById(tabInfo.getInfoId());
        return ResponseUtil.ok();
    }


    /**
     * 查所有标签（限制6个）
     */
    @GetMapping("/tag")
    public Object tag(@LoginAdmin Integer adminId) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallTabInfoTag> litemallTabInfoTag = tabInfoTagService.querySelective();
        return ResponseUtil.ok(litemallTabInfoTag);
    }

}
