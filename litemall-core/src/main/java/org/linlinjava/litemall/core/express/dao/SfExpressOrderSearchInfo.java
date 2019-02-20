package org.linlinjava.litemall.core.express.dao;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author : fujue
 * @version V1.0
 * @Project: litemall
 * @Package org.linlinjava.litemall.core.express.dao
 * @Description: 订单查询相应
 * @date Date : 2019年02月19日 10:15
 */
@JacksonXmlRootElement()
public class SfExpressOrderSearchInfo {
    @JacksonXmlProperty()
    private String orderid;
    @JacksonXmlProperty()
    private String mailno;
    @JacksonXmlProperty()
    private String origincode;
    @JacksonXmlProperty()
    private String destcode;
    @JacksonXmlProperty()
    private String filter_result;

    public void setOrderid(String orderid){
        this.orderid = orderid;
    }
    public String getOrderid(){
        return this.orderid;
    }
    public void setMailno(String mailno){
        this.mailno = mailno;
    }
    public String getMailno(){
        return this.mailno;
    }
    public void setOrigincode(String origincode){
        this.origincode = origincode;
    }
    public String getOrigincode(){
        return this.origincode;
    }
    public void setDestcode(String destcode){
        this.destcode = destcode;
    }
    public String getDestcode(){
        return this.destcode;
    }
    public void setFilter_result(String filter_result){
        this.filter_result = filter_result;
    }
    public String getFilter_result(){
        return this.filter_result;
    }
}
