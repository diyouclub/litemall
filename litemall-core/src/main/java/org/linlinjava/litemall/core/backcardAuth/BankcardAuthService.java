package org.linlinjava.litemall.core.backcardAuth;

import net.sf.json.JSONObject;
import org.apache.http.client.methods.HttpPost;
import org.linlinjava.litemall.core.util.HTTPUtils;
import org.linlinjava.litemall.core.util.thirdParty.JDYXUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : fujue
 * @version V1.0
 * @Project: litemall
 * @Package org.linlinjava.litemall.core.backcardAuth
 * @Description: TODO
 * @date Date : 2019年02月17日 15:39
 */
@Service
public class BankcardAuthService {





    public  Map<String, String> verify(String name, String idCardNO, String mobile, String bankCardNO) {
        Map<String, String> postRespMap = null;

        JSONObject requestParams = getRequestParams(name, idCardNO, mobile, bankCardNO);
        //接口调用记录对象

        try {
            HttpPost httpPost = new HttpPost(JDYXUtil.BANK_CARD_AUTH_SETTING_URL);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("X-Access-Id", JDYXUtil.BANK_CARD_AUTH_SETTING_ACCESSID);
            httpPost.addHeader("X-Token", JDYXUtil.BANK_CARD_AUTH_SETTING_KEY);
            postRespMap = HTTPUtils.getPostResp(requestParams.toString() ,httpPost);
        } catch (Exception e) {
            e.printStackTrace();
            postRespMap.put("message","银行卡四要素请求失败");
            return postRespMap;
        } finally {

        }
        // 解析返回报文
        return postRespMap;
    }



    /**
     * 获取银行卡四要素所有请求参数
     *
     * @param name
     * @param idCardNO
     * @param mobile
     * @param bankCardNO
     */
    private JSONObject getRequestParams(String name, String idCardNO, String mobile, String bankCardNO) {
        JSONObject conditionJson = new JSONObject();
        conditionJson.put("type", "four");
        conditionJson.put("name", name);
        conditionJson.put("id_card", idCardNO);
        conditionJson.put("card", bankCardNO);
        conditionJson.put("mobile", mobile);
        return conditionJson;
    }


}
