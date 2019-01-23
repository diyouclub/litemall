package org.linlinjava.litemall.wx.service;


import org.linlinjava.litemall.db.domain.LitemallUser;
import org.linlinjava.litemall.db.service.LitemallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PyramidService {
    //0 普通用户 1 会员 2 市  3 省
    private final int AGENCY_LEVEL_NORMAL = 0;
    private final int AGENCY_LEVEL_MEMBER = 1;
    private final int AGENCY_LEVEL_CITY = 2;
    private final int AGENCY_LEVEL_PROVINCE = 3;

    private final String SUCCESS = "SUCCESS";
    private final String FAILURE = "FAILURE";
    //用户树
    //当前level

    //计算树
    private List<Map> lstSellCalcOrder = new ArrayList();
    private List<Map> lstAgencyCalcOrder = new ArrayList();

    public PyramidService() {

    }

    @Autowired
    private LitemallUserService userService;


    public List calcSell(BigDecimal totalFee, Integer param_user_id) {
        lstSellCalcOrder = init();
        BigDecimal bdRemail = new BigDecimal("0");
        List lstResult = new ArrayList();
        LitemallUser user = userService.findById(param_user_id);
        Assert.state(user != null, "用户不存在");


        //祖先树
        List<LitemallUser> lstParentTree = getParentTree(param_user_id);
        //
        if (lstParentTree == null || lstParentTree.size() <= 1) {
            Map map = new HashMap();
            map.put("code", SUCCESS);
            map.put("message", "没有祖先，不需计算");
            return lstResult;
        }

        //计算开始
        int cur_calc_level = 0;
        int cur_user_index = 1;
        int user_limit_index = 0;
        int level_limit = 0;


        for (int i = 0; i < lstSellCalcOrder.size(); i++) {
            //第一阶段 包括第1条规则
            // 计算阶段
            Map mapPhase = lstSellCalcOrder.get(i);
            int phaseNum = (int) mapPhase.get("phase");
            List<Map> lstPhase = (List<Map>) mapPhase.get("rule");

            //按阶段执行
                for (Map mCalc : lstPhase) {
                //计算条件
                cur_calc_level = (int) mCalc.get("agency_level");
                String scale = (String) mCalc.get("scale");
                String oper = (String) mCalc.get("agency_oper");
                String calc_flag = (String) mCalc.get("calc_flag");
                // 计算要素
                LitemallUser curUser = null;
                int user_level = 0;

                if (level_limit <= cur_calc_level) {

                    if (phaseNum == 2 && level_limit == cur_calc_level) {
                        //市级的情况

                    //}else if (phaseNum == 4 && level_limit == cur_calc_level) {

                    }else {


                    if ("once".equals(calc_flag) ||  "try".equals(calc_flag)) {
                        user_limit_index = lstParentTree.size() > cur_user_index ? cur_user_index + 1 : lstParentTree.size();

                    } else {
                        user_limit_index = lstParentTree.size();
                    }

                    for (int iUser = cur_user_index; iUser < user_limit_index; iUser++) {
                        curUser = lstParentTree.get(iUser);
                        user_level = curUser.getAgencyLevel();
//                        if (phaseNum == 2 && user_level == 2 ) {
//                            i++; //跳过
//                        }
                        level_limit = level_limit < user_level ? user_level : level_limit;


                        if ("once".equals(calc_flag)  ) {
                            if ( user_level == 3 && phaseNum != 5) {

                            }else {
                                cur_user_index = iUser + 1;
                            }

                        }

                        if ("final".equals(calc_flag)) {
                            cur_user_index = iUser + 1;
                        }
                        if (compareLevel(oper, user_level, cur_calc_level)) { //参与计算的条件
                            if ("loop".equals(calc_flag) && phaseNum == 2 && user_level == 2 ) {
                                cur_user_index = iUser + 1;
                            }
                            if ("try".equals(calc_flag)) {
                                cur_user_index = iUser + 1;
                            }
                            break;
                        } else {
                            if ("try".equals(calc_flag)) {
                                if (user_level <level_limit) {
                                    cur_user_index = iUser + 1;
                                }
                            }

                            curUser = null;
                        }


                    }
                    }
                }else if (level_limit > cur_calc_level) {
                    //第一父级 已经是 市级或升级
                    //进公账
                }


                if (curUser != null) {
                    Map mFee = new HashMap();
                    mFee.put("fee", totalFee.multiply(new BigDecimal(scale)).divide(new BigDecimal("100"), BigDecimal.ROUND_HALF_UP));

                    mFee.put("user_id", curUser.getId());
                    mFee.put("user_name", curUser.getNickname());
                    mFee.put("desc", mCalc.get("desc"));
                    mFee.put("scale", mCalc.get("scale"));
                    mFee.put("name", mCalc.get("name"));

                    lstResult.add(mFee);

                } else {
                    Map mFee = new HashMap();
                    mFee.put("fee", totalFee.multiply(new BigDecimal(scale)).divide(new BigDecimal("100"), BigDecimal.ROUND_HALF_UP));

                    mFee.put("user_id", 0);
                    mFee.put("user_name", "公账");
                    mFee.put("desc", mCalc.get("desc"));
                    mFee.put("scale", mCalc.get("scale"));
                    mFee.put("name", mCalc.get("name"));

                    lstResult.add(mFee);
                }

                    bdRemail  = bdRemail.add(totalFee.multiply(new BigDecimal(scale)).divide(new BigDecimal("100"), BigDecimal.ROUND_HALF_UP)) ;
            }
        }


        // 最后补上剩余金额
        Map mFee = new HashMap();
        mFee.put("fee", totalFee.subtract(bdRemail));

        mFee.put("user_id", 0);
        mFee.put("user_name", "公账");
        mFee.put("desc", "剩余金额");
        mFee.put("scale", "0");
        mFee.put("name", "计算剩余");

        lstResult.add(mFee);

        System.out.println("代理关系:");
        for (int i = 0; i < lstParentTree.size(); i++) {
            LitemallUser temp = lstParentTree.get(i);
            int agency_level = temp.getAgencyLevel();
            String agency_name = "普通用户";
            switch (agency_level) {
                case 1:
                    agency_name = "会员";
                    break;
                case 2:
                    agency_name = "市代";
                    break;
                case 3:
                    agency_name = "省代";
                    break;
            }


            System.out.print(temp.getNickname() + "[" + agency_name + "]<--");
            System.out.println();
        }
        System.out.println("-----------------------------");
        for (int i = 0; i < lstResult.size(); i++) {
            Map map = (Map) lstResult.get(i);

            //System.out.println("计算结果:第"+(i+1)+"条,规则："+map.get("desc")+"\n用户："+map.get("puser"));
            System.out.println("计算结果:第" + (i + 1) + "条，" + map.get("user_name") + ",金额= " + map.get("fee") + ",比例= " + map.get("scale") + "%，规则=" + map.get("name"));
        }




        return lstResult;
    }


    /**
     * once 固定坐标找1次
     * until 循环找到
     * loop
     * @return
     */
    private List<Map> init() {
        lstSellCalcOrder = new ArrayList();
        Map mapPhase1= new HashMap();
        Map mapPhase2= new HashMap();
        Map mapPhase3= new HashMap();
        Map mapPhase4= new HashMap();
        Map mapPhase5= new HashMap();


        List lstPhase1 = new ArrayList();
        Map item = new HashMap();

        item.put("calc_flag", "once");
        item.put("scale", "18");
        item.put("agency_level", AGENCY_LEVEL_MEMBER);
        item.put("agency_oper", ">=");
        item.put("desc", "第一条规则：第一父级获得18%");
        item.put("name", "上级");
        lstPhase1.add(item);
        mapPhase1.put("rule",lstPhase1);
        mapPhase1.put("phase",1);


        lstSellCalcOrder.add(mapPhase1);

        List lstPhase2 = new ArrayList();
        item = new HashMap();
        item.put("calc_flag", "loop");
        item.put("scale", "22");
        item.put("agency_level", AGENCY_LEVEL_CITY);
        item.put("agency_oper", ">=");
        item.put("desc", "第二条规则：第一上级代理获得22%");
        item.put("name", "上级代理（市代或省代）");
        lstPhase2.add(item);

        mapPhase2.put("rule",lstPhase2);
        mapPhase2.put("phase",2);

        lstSellCalcOrder.add(mapPhase2);




        List lstPhase3 = new ArrayList();
        item = new HashMap();
        item.put("calc_flag", "try");
        item.put("scale", "6");
        item.put("agency_level", AGENCY_LEVEL_CITY);
        item.put("agency_oper", "=");
        item.put("desc", "第三条规则：市代1获得6%");
        item.put("name", "市代1");
        lstPhase3.add(item);

        item = new HashMap();
        item.put("calc_flag", "try");
        item.put("scale", "8");
        item.put("agency_level", AGENCY_LEVEL_CITY);
        item.put("agency_oper", "=");
        item.put("desc", "第四条规则：市代2获得8%");
        item.put("name", "市代2");
        lstPhase3.add(item);

        mapPhase3.put("rule",lstPhase3);
        mapPhase3.put("phase",3);

        lstSellCalcOrder.add(mapPhase3);



        List lstPhase4 = new ArrayList();
        item = new HashMap();
        item.put("calc_flag", "final");
        item.put("scale", "8");
        item.put("agency_level", AGENCY_LEVEL_PROVINCE);
        item.put("agency_oper", ">=");
        item.put("desc", "第五条规则：第一省代获得8%");
        item.put("name", "省代");
        lstPhase4.add(item);


        mapPhase4.put("rule",lstPhase4);
        mapPhase4.put("phase",4);
        lstSellCalcOrder.add(mapPhase4);

        List lstPhase5 = new ArrayList();
        item = new HashMap();
        item.put("calc_flag", "once");
        item.put("scale", "1");
        item.put("agency_level", AGENCY_LEVEL_PROVINCE);
        item.put("agency_oper", "=");
        item.put("desc", "第六条规则：省代1获得1%");
        item.put("name", "省代1");
        lstPhase5.add(item);

        item = new HashMap();
        item.put("calc_flag", "once");
        item.put("scale", "2");
        item.put("agency_level", AGENCY_LEVEL_PROVINCE);
        item.put("agency_oper", "=");
        item.put("desc", "第七条规则：省代2获得2%");
        item.put("name", "省代2");
        lstPhase5.add(item);

        mapPhase5.put("rule",lstPhase5);
        mapPhase5.put("phase",5);


        lstSellCalcOrder.add(mapPhase5);
        return lstSellCalcOrder;
    }

    private boolean compareLevel(String oper, int user_level, int cur_calc_level) {
        switch (oper) {
            case ">=":
                if (user_level >= cur_calc_level) { //参与计算的条件
                    return true;
                }
                break;
            case "=":

                if (user_level == cur_calc_level) { //参与计算的条件
                    return true;
                }
                break;
            default:
        }

        return false;
    }


    private List<LitemallUser> getParentTree(Integer userId) {
        List lst = new ArrayList();
        LitemallUser user = userService.findById(userId);
        if (user != null) {
            lst.add(user);
            if (user.getPid() != null) {
                lst.addAll(getParentTree(user.getPid()));
            }
        }
        return lst;
    }

    public static void main(String[] args) {
        PyramidService pyramidService = new PyramidService();
        List<LitemallUser> lstParentTree = pyramidService.getParentTree(12);
        for (LitemallUser user : lstParentTree) {
            System.out.printf(user.getNickname());
        }
    }


    private List<Map> initAgency(Byte buy_agency_level) {
        lstAgencyCalcOrder = new ArrayList();
        Map mapPhase1= new HashMap();
        Map mapPhase2= new HashMap();
        Map mapPhase3= new HashMap();
        Map mapPhase4= new HashMap();

        if ( buy_agency_level == 2) {
            List lstPhase1 = new ArrayList();
            Map item = new HashMap();

            item.put("calc_flag", "once");
            item.put("scale", "20");
            item.put("agency_level", AGENCY_LEVEL_CITY);
            item.put("agency_oper", "=");
            item.put("desc", "第一条规则：市级代理1 获得20%");
            item.put("name", "市级代理1");
            lstPhase1.add(item);

            item = new HashMap();
            item.put("calc_flag", "once");
            item.put("scale", "40");
            item.put("agency_level", AGENCY_LEVEL_CITY);
            item.put("agency_oper", "=");
            item.put("desc", "第二条规则：市级代理2获得40%");
            item.put("name", "市级代理2");

            lstPhase1.add(item);

            mapPhase1.put("rule",lstPhase1);
            mapPhase1.put("phase",1);


            lstAgencyCalcOrder.add(mapPhase1);


            List lstPhase3 = new ArrayList();
            item = new HashMap();
            item.put("calc_flag", "final");
            item.put("scale", "25");
            item.put("agency_level", AGENCY_LEVEL_PROVINCE);
            item.put("agency_oper", "=");
            item.put("desc", "第三条规则：省代获得25%");
            item.put("name", "省代");
            lstPhase3.add(item);
            mapPhase3.put("rule",lstPhase3);
            mapPhase3.put("phase",3);
            lstAgencyCalcOrder.add(mapPhase3);

        }else {

            List lstPhase4 = new ArrayList();

            Map item = new HashMap();
            item.put("calc_flag", "once");
            item.put("scale", "20");
            item.put("agency_level", AGENCY_LEVEL_PROVINCE);
            item.put("agency_oper", "=");
            item.put("desc", "第四条规则：省代1获得20%");
            item.put("name", "省代1");
            lstPhase4.add(item);

            item = new HashMap();
            item.put("calc_flag", "once");
            item.put("scale", "30");
            item.put("agency_level", AGENCY_LEVEL_PROVINCE);
            item.put("agency_oper", "=");
            item.put("desc", "第五条规则：省代2获得30%");
            item.put("name", "省代2");
            lstPhase4.add(item);


            mapPhase4.put("rule",lstPhase4);
            mapPhase4.put("phase",4);

            lstAgencyCalcOrder.add(mapPhase4);
        }
        //






        return lstAgencyCalcOrder;
    }


    public List calcAgency(Byte buy_agency_level,BigDecimal totalFee, Integer param_user_id) {
//        if (buy_agency_level == 2) {
//            totalFee = new BigDecimal("300");
//        }else if (buy_agency_level == 3) {
//            totalFee = new BigDecimal("6800");
//        }

        BigDecimal bdRemail = new BigDecimal("0");
        lstAgencyCalcOrder = initAgency(buy_agency_level);
        List lstResult = new ArrayList();
        LitemallUser user = userService.findById(param_user_id);
        Assert.state(user != null, "用户不存在");


        //祖先树
        List<LitemallUser> lstParentTree = getParentTree(param_user_id);
        //
        if (lstParentTree == null || lstParentTree.size() <= 1) {
            Map map = new HashMap();
            map.put("code", SUCCESS);
            map.put("message", "没有祖先，不需计算");
            return lstResult;
        }


        //计算开始
        int cur_calc_level = 0;
        int cur_user_index = 1;
        int user_limit_index = 0;
        int level_limit = 0;


        for (int i = 0; i < lstAgencyCalcOrder.size(); i++) {
            //第一阶段 包括第1条规则
            // 计算阶段
            Map mapPhase = lstAgencyCalcOrder.get(i);
            int phaseNum = (int) mapPhase.get("phase");
//            if (buy_agency_level == 3 && phaseNum < 4) {
//                continue;
//            }
            List<Map> lstPhase = (List<Map>) mapPhase.get("rule");

            //按阶段执行
            for (Map mCalc : lstPhase) {
                //计算条件
                cur_calc_level = (int) mCalc.get("agency_level");
                String scale = (String) mCalc.get("scale");
                String oper = (String) mCalc.get("agency_oper");
                String calc_flag = (String) mCalc.get("calc_flag");
                // 计算要素
                LitemallUser curUser = null;
                int user_level = 0;

                if (level_limit <= cur_calc_level) {


                    if ("once".equals(calc_flag)) {
                        user_limit_index = lstParentTree.size() > cur_user_index ? cur_user_index + 1 : lstParentTree.size();

                    } else {
                        user_limit_index = lstParentTree.size();
                    }

                    for (int iUser = cur_user_index; iUser < user_limit_index; iUser++) {
                        curUser = lstParentTree.get(iUser);
                        user_level = curUser.getAgencyLevel();
//                        if (phaseNum == 2 && user_level == 2 ) {
//                            i++; //跳过
//                        }
                        level_limit = level_limit < user_level ? user_level : level_limit;

                        if (user_level > cur_calc_level) {
                            curUser = null;
                            break;
                        }

                        if ("once".equals(calc_flag) || "final".equals(calc_flag) ) {
                            cur_user_index = iUser + 1;
                        }
                        if (compareLevel(oper, user_level, cur_calc_level)) { //参与计算的条件
                            if ("loop".equals(calc_flag) && phaseNum == 2 && user_level == 1 ) {
                                cur_user_index = iUser + 1;
                            }
                            break;
                        } else {

                            curUser = null;
                        }


                    }
                }


                if (curUser != null) {
                    Map mFee = new HashMap();
                    mFee.put("fee", totalFee.multiply(new BigDecimal(scale)).divide(new BigDecimal("100"), BigDecimal.ROUND_HALF_UP));

                    mFee.put("user_id", curUser.getId());
                    mFee.put("user_name", curUser.getNickname());
                    mFee.put("desc", mCalc.get("desc"));
                    mFee.put("scale", mCalc.get("scale"));
                    mFee.put("name", mCalc.get("name"));
                    lstResult.add(mFee);

                } else {
                    Map mFee = new HashMap();
                    mFee.put("fee", totalFee.multiply(new BigDecimal(scale)).divide(new BigDecimal("100"), BigDecimal.ROUND_HALF_UP));
                    mFee.put("user_name", "公账");
                    mFee.put("desc", mCalc.get("desc"));
                    mFee.put("scale", mCalc.get("scale"));
                    mFee.put("name", mCalc.get("name"));
                    lstResult.add(mFee);
                }

                bdRemail = bdRemail.add(totalFee.multiply(new BigDecimal(scale)).divide(new BigDecimal("100"), BigDecimal.ROUND_HALF_UP)) ;
            }
        }

        if (buy_agency_level == 2) {
            boolean isExistProvince = false;
            String puser = "";
            Integer puser_id = 0;
            for (int i = lstResult.size() -1 ; i >=0; i--) {
                Map map = (Map) lstResult.get(i);
                if (isExistProvince) {
                    if ("公账".equals(map.get("user_name"))) {
                        map.put("user_name",puser);
                        map.put("user_id",puser_id);
                    }
                }
                if ("省代".equals(map.get("name"))) {
                    isExistProvince = true;
                    puser = map.get("user_name").toString();
                    puser_id = (Integer) map.get("user_id");
                }

            }
        }


        // 最后补上剩余金额
        Map mFee = new HashMap();
        mFee.put("fee", totalFee.subtract(bdRemail));

        mFee.put("user_id", 0);
        mFee.put("user_name", "公账");
        mFee.put("desc", "剩余金额");
        mFee.put("scale", "0");
        mFee.put("name", "计算剩余");


        System.out.println("代理关系:");
        for (int i = 0; i < lstParentTree.size(); i++) {
            LitemallUser temp = lstParentTree.get(i);
            int agency_level = temp.getAgencyLevel();
            String agency_name = "普通用户";
            switch (agency_level) {
                case 1:
                    agency_name = "会员";
                    break;
                case 2:
                    agency_name = "市代";
                    break;
                case 3:
                    agency_name = "省代";
                    break;
            }

            System.out.print(temp.getNickname() + "[" + agency_name + "]-->"+(i==0?"升级为"+(buy_agency_level == 2 ? "市级":"省级"):""));

            System.out.println();
        }
        System.out.println("-----------------------------");
        for (int i = 0; i < lstResult.size(); i++) {
            Map map = (Map) lstResult.get(i);

            //System.out.println("计算结果:第"+(i+1)+"条,规则："+map.get("desc")+"\n用户："+map.get("puser"));
            System.out.println("计算结果:第" + (i + 1) + "条，" + map.get("user_name") + ",金额= " + map.get("fee") + ",比例= " + map.get("scale") + "%，规则=" + map.get("name"));
        }



        return lstResult ;
    }

}
