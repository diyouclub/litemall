package org.linlinjava.litemall.core.express.dao;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


/**
 * @author : fujue
 * @version V1.0
 * @Project: litemall
 * @Package org.linlinjava.litemall.core.express.dao
 * @Description: 下订单相应javabean
 * @date Date : 2019年02月19日 10:08
 */

@JacksonXmlRootElement(localName = "OrderResponse")
public class SfExpressOrderServiceInfo {
    @JacksonXmlProperty()
    private String orderid;
    @JacksonXmlProperty()
    private String mailno;
    @JacksonXmlProperty()
    private String return_tracking_no;
    @JacksonXmlProperty()
    private String origincode;
    @JacksonXmlProperty()
    private String destcode;
    @JacksonXmlProperty()
    private String filter_result;
    @JacksonXmlProperty()
    private String remark;
    @JacksonXmlProperty()
    private String agentMailno;
    @JacksonXmlProperty()
    private String mapping_mark;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getMailno() {
        return mailno;
    }

    public void setMailno(String mailno) {
        this.mailno = mailno;
    }

    public String getReturn_tracking_no() {
        return return_tracking_no;
    }

    public void setReturn_tracking_no(String return_tracking_no) {
        this.return_tracking_no = return_tracking_no;
    }

    public String getOrigincode() {
        return origincode;
    }

    public void setOrigincode(String origincode) {
        this.origincode = origincode;
    }

    public String getDestcode() {
        return destcode;
    }

    public void setDestcode(String destcode) {
        this.destcode = destcode;
    }

    public String getFilter_result() {
        return filter_result;
    }

    public void setFilter_result(String filter_result) {
        this.filter_result = filter_result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAgentMailno() {
        return agentMailno;
    }

    public void setAgentMailno(String agentMailno) {
        this.agentMailno = agentMailno;
    }

    public String getMapping_mark() {
        return mapping_mark;
    }

    public void setMapping_mark(String mapping_mark) {
        this.mapping_mark = mapping_mark;
    }
}
