package org.linlinjava.litemall.wx.web;



import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.linlinjava.litemall.core.backcardAuth.BankcardAuthService;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.util.thirdParty.JDYXUtil;
import org.linlinjava.litemall.db.domain.LitemallBankcardAuthCallWithBLOBs;
import org.linlinjava.litemall.db.service.LitemallBankcardAuthCallService;
import org.linlinjava.litemall.wx.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : fujue
 * @version V1.0
 * @Project: litemall
 * @Package org.linlinjava.litemall.wx.web
 * @Description: 银行卡校验接口
 * @date Date : 2019年02月17日 17:27
 */
@RestController
@RequestMapping("/wx/bankcardAuth")
@Validated
public class WxBankcardAuthController {

    @Autowired
    private BankcardAuthService bankcardAuthService;
    @Autowired
    private LitemallBankcardAuthCallService litemallBankcardAuthCallService;

    @GetMapping("validate")
    public Object list(@RequestParam String name,
                       @RequestParam String idCardNo,
                       @RequestParam String mobile,
                       @RequestParam String bankCardNo,
                       HttpServletRequest request) {
        Map<String, String> postRespMap = null;

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(idCardNo) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(bankCardNo)) {
            return ResponseUtil.fail(402,"银行卡四要素验证参数缺失");
        }

        postRespMap = bankcardAuthService.verify(name,idCardNo,mobile,bankCardNo);

        //解析解密后的接口返回内容
        String result = postRespMap.get("responseBody");
        // 解析报文
        JSONObject jsonObject = JSONObject.fromObject(result);
        // 错误报文 code = 0 是正常，非 0 都是异常
        if (!jsonObject.containsKey("code") || !jsonObject.getString("code").equals("0")) {
            return ResponseUtil.fail(503,jsonObject.getString("msg"));
        }
        if (!jsonObject.containsKey("result")) {
            return ResponseUtil.fail(503,"四要素验证结果异常");
        }
        // 正确报文
        //status == S 表明结果一致  status == F 表明结果不一致
        JSONObject resultObject = jsonObject.getJSONObject("result");
        if (!"S".equals(resultObject.getString("status"))) {
            return ResponseUtil.fail(503,"您填写的信息不正确，请仔细检查");
        }


        //保存接口调用记录
        LitemallBankcardAuthCallWithBLOBs call = new LitemallBankcardAuthCallWithBLOBs();
        // Request
        call.setRequestUrl(JDYXUtil.BANK_CARD_AUTH_SETTING_URL);
        call.setRequestMethod("POST");
        call.setRequestCharset(Consts.UTF_8.toString());
        call.setRequestParameter(postRespMap.get("requestBody"));
        call.setRequestHeader(postRespMap.get("requestHeaders"));
        //Response
        call.setResponseContent(postRespMap.get("responseBody"));
        call.setResponseStatus(Integer.valueOf(postRespMap.get("responseStatus")));
        call.setResponseHeader(postRespMap.get("responseHeaders"));
        call.setOperator(mobile);
        call.setIp(IpUtil.client(request));
        litemallBankcardAuthCallService.add(call);

        // 保存绑定关系
        return ResponseUtil.ok("绑定成功");
    }


}
