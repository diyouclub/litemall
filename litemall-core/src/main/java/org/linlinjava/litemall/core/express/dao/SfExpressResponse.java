package org.linlinjava.litemall.core.express.dao;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


/**
 * @author : fujue
 * @version V1.0
 * @Project: litemall
 * @Package org.linlinjava.litemall.core.express.dao
 * @Description: TODO
 * @date Date : 2019年02月19日 10:08
 */

@JacksonXmlRootElement(localName = "Response")
public class SfExpressResponse {
    @JacksonXmlProperty()
    private String service;
    @JacksonXmlProperty()
    private String Head;
    @JacksonXmlProperty()
    private String Body;
    @JacksonXmlProperty()
    private String ERROR;

    public void setService(String service){
        this.service = service;
    }
    public String getService(){
        return this.service;
    }
    public void setHead(String Head){
        this.Head = Head;
    }
    public String getHead(){
        return this.Head;
    }
    public String getBody() {
        return Body;
    }
    public void setBody(String body) {
        Body = body;
    }
    public String getERROR() {
        return ERROR;
    }
    public void setERROR(String ERROR) {
        this.ERROR = ERROR;
    }


}
