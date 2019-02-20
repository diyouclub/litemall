package org.linlinjava.litemall.core.express;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.linlinjava.litemall.core.express.config.SfExpressProperties;
import org.linlinjava.litemall.core.express.dao.SfExpressOrderSearchInfo;
import org.linlinjava.litemall.core.express.dao.SfExpressOrderServiceInfo;
import org.linlinjava.litemall.core.express.dao.SfExpressResponse;
import org.linlinjava.litemall.db.domain.LitemallExpressOrder;
import org.linlinjava.litemall.db.domain.LitemallExpressOrderCargo;
import org.linlinjava.litemall.db.service.LitemallExpressOrderCargoService;
import org.linlinjava.litemall.db.service.LitemallExpressOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 顺丰丰桥物流服务
 * <p>
 *
 */
@Service
public class SfExpressService {
    @Autowired
    LitemallExpressOrderService expressOrderService;
    @Autowired
    LitemallExpressOrderCargoService expressOrderCargoService;
    //请求url
    private String ReqURL = "http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";

    private String ExecSuccess ="OK";
    private String ExecError ="ERR";

    private SfExpressProperties properties = new SfExpressProperties();

    public SfExpressProperties getProperties() {
        return properties;
    }

    public void setProperties(SfExpressProperties properties) {
        this.properties = properties;
    }



    /**
     * 订单结果查询接口
     *
     * @param orderid
     * @return
     */
    public String execOrderSearch(String orderid) {
        try {
            properties = new SfExpressProperties();
            properties.setCheckWord("2aquhvzkddTu6JwQXEo7WReCrZRxpmqk");
            properties.setClientCode("SPYKJ");
            properties.setCustid("7551234567");
            properties.setReqUrl("http://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService");


            String reqXml = initRequest("OrderSearch",orderid);
            //String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(properties.getReqUrl(),reqXml,properties.getClientCode(),properties.getCheckWord());
            String respXml = "<?xml version='1.0' encoding='UTF-8'?><Response service=\"OrderSearchService\"><Head>ERR</Head><ERROR code=\"6150\">找不到该订单</ERROR></Response>";
            ObjectMapper objMap = new XmlMapper();
            SfExpressResponse sfExpressResponse = objMap.readValue(respXml, SfExpressResponse.class);
            System.out.println(sfExpressResponse.getHead());

            ObjectMapper omBody = new XmlMapper();
            if (ExecSuccess.equals(sfExpressResponse.getHead())) {
                //执行成功
                SfExpressOrderSearchInfo orderSearchInfo = omBody.readValue(sfExpressResponse.getBody(), SfExpressOrderSearchInfo.class);

                return orderSearchInfo.getOrderid();
            }else  {
                //执行出现错误
                return sfExpressResponse.getERROR();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    /*
    下订单接口
     */
    public String execOrderService(String orderid){

        try {
            properties = new SfExpressProperties();
            properties.setCheckWord("2aquhvzkddTu6JwQXEo7WReCrZRxpmqk");
            properties.setClientCode("SPYKJ");
            properties.setCustid("7551234567");
            properties.setReqUrl("http://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService");


            String reqXml = initRequest("OrderService",orderid);


            //String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(properties.getReqUrl(),reqXml,properties.getClientCode(),properties.getCheckWord());
            String respXml = "<?xml version='1.0' encoding='UTF-8'?><Response service=\"OrderSearchService\"><Head>ERR</Head><ERROR code=\"6150\">找不到该订单</ERROR></Response>";
            ObjectMapper objMap = new XmlMapper();
            SfExpressResponse sfExpressResponse = objMap.readValue(respXml, SfExpressResponse.class);
            System.out.println(sfExpressResponse.getHead());

            ObjectMapper omBody = new XmlMapper();
            if (ExecSuccess.equals(sfExpressResponse.getHead())) {
                //执行成功
                SfExpressOrderServiceInfo orderServiceInfo = omBody.readValue(sfExpressResponse.getBody(), SfExpressOrderServiceInfo.class);

                return orderServiceInfo.getOrderid();
            }else  {
                //执行出现错误
                return sfExpressResponse.getERROR();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String initRequest(String reqType, String orderid) {
        StringBuilder requestXml = new StringBuilder("<Request service='");
        requestXml.append("orderSearch").append("Service' lang='zh-CN'>");

        requestXml.append("<Head>"+properties.getClientCode()+"</Head><Body>");
        switch (reqType) {
            case "OrderSearch":
                //requestXml.append("<OrderSearch orderid='QIAO-20180524001'/>");
                requestXml.append("<OrderSearch orderid='"+orderid+"'/>");

                break;
            case "OrderServer":
                requestXml.append("<Order ");
                LitemallExpressOrder litemallExpressOrder = expressOrderService.findByOrderId(orderid);
                requestXml.append(" orderid=").append(litemallExpressOrder.getOrderid());
                requestXml.append(" j_company=").append(litemallExpressOrder.getjCompany());
                requestXml.append(" j_contact=").append(litemallExpressOrder.getjContact());
                requestXml.append(" j_tel=").append(litemallExpressOrder.getjTel());
                requestXml.append(" j_mobile=").append(litemallExpressOrder.getjMobile());
                requestXml.append(" j_province=").append(litemallExpressOrder.getjProvince());
                requestXml.append(" j_city=").append(litemallExpressOrder.getjCity());
                requestXml.append(" j_county=").append(litemallExpressOrder.getjCounty());
                requestXml.append(" j_address=").append(litemallExpressOrder.getjAddress());
                requestXml.append(" d_contact=").append(litemallExpressOrder.getdContact());
                requestXml.append(" d_mobile=").append(litemallExpressOrder.getdMobile());
                requestXml.append(" d_province=").append(litemallExpressOrder.getdProvince());
                requestXml.append(" d_city=").append(litemallExpressOrder.getdCity());
                requestXml.append(" d_county=").append(litemallExpressOrder.getdCounty());
                requestXml.append(" d_address=").append(litemallExpressOrder.getdAddress());
                requestXml.append(" express_type=").append(litemallExpressOrder.getExpressType());
                requestXml.append(" pay_method=").append(litemallExpressOrder.getPayMethod());
                requestXml.append(" custid=").append(litemallExpressOrder.getCustid());
                requestXml.append(" parcel_quantity=").append(litemallExpressOrder.getParcelQuantity());
                requestXml.append(" is_docall=").append(litemallExpressOrder.getIsDocall());
                requestXml.append(" sendstarttime=").append(litemallExpressOrder.getSendstarttime());
                requestXml.append(" order_source=").append(litemallExpressOrder.getOrderSource());
                requestXml.append(" remark=").append(litemallExpressOrder.getRemark());
                requestXml.append(">");

                //cargo begin
                LitemallExpressOrderCargo expressOrderCargo = expressOrderCargoService.findByExpressOrderId(litemallExpressOrder.getId());

                requestXml.append("<Cargo ");
                requestXml.append(" name=").append(expressOrderCargo.getName());
                requestXml.append(" count=").append(expressOrderCargo.getCount());
                requestXml.append(" unit=").append(expressOrderCargo.getUnit());
                requestXml.append(" weight=").append(expressOrderCargo.getWeight());
                requestXml.append(" amount=").append(expressOrderCargo.getAmount());
                requestXml.append(" currency=").append(expressOrderCargo.getCurrency());
                requestXml.append(" source_area=").append(expressOrderCargo.getSourceArea());

                requestXml.append("/>");
                requestXml.append("/Order>");
                break;

        }

        requestXml.append("</Body></Request>");
        System.out.println(requestXml.toString());
        return requestXml.toString();
    }


}
//
//<Order
//                    orderid="SFKD-20160219000019"
//                            j_company="深圳宝龙达信息技术股份有限公司"
//                            j_contact="邓丽君"
//                            j_tel="15323233432"
//                            j_mobile="15322234342"
//                            j_province="广东省"
//                            j_city="深圳市"
//                            j_county="南山区"
//                            j_address="广东省深圳市南山区西丽镇塘朗同富裕工业城7栋"
//                            d_contact="四海" d_tel="15023434543"
//                            d_mobile="15423456545"
//                            d_province="广东省"
//                            d_city="深圳市"
//                            d_county="南山区"
//                            d_address="科技园软件产业基地"
//                            express_type="1"
//                            pay_method="1"
//                            custid="7551234567"
//                            parcel_quantity="1"
//                            is_docall="0"
//                            sendstarttime=""
//                            order_source="宝龙达"
//                            remark="电子产品 笔记本+显卡">
//<Cargo
//                    name='Dresses'
//                            count='1'
//                            unit='piece'
//                            weight='1.000'
//                            amount='1'
//                            currency='USD'
//                            source_area='CN'/>
//</Order>
//
