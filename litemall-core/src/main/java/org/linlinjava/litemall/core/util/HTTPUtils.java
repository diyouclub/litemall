/*
 * Copyright 2010-2016 icl-network.com. All rights reserved.
 * Support: http://www.icl-network.com
 */
package org.linlinjava.litemall.core.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Utils - HTTP GET
 * 
 * @author zk 2017/9/6 modify
 * @version 5.0
 */
public final class HTTPUtils {

    /** UTF-8字符集编码 */
    public static final String UTF_8 = "UTF-8";

    /**
     * 不可实例化
     */
    private HTTPUtils() {

    }

    /**
     * 获取响应信息
     * 
     * @param url 请求地址
     * @param httpParameters 请求参数
     * @return 响应数据
     */
    public static Map<String, String> getGetResp(String url, List<NameValuePair> httpParameters)  {
        return getGetResp(url, httpParameters, "GB2312");
    }

    /**
     * 获取响应信息
     *
     * @param url 请求地址
     * @param httpParameters 请求参数
     * @return 响应数据
     */
    public static Map<String, String> getGetResp(String url, List<NameValuePair> httpParameters, String charset) {
        String sendstr = null;
        if (httpParameters != null && httpParameters.size() > 0)
            try {
                sendstr = "?" + EntityUtils.toString(new UrlEncodedFormEntity(httpParameters, UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyMap();
            } 

        // URL封装
        HttpGet httpGet = new HttpGet(url);
        try {
            httpGet.setURI(new URI(httpGet.getURI().toString() + sendstr));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }

        // 获取http请求响应内容
        Map<String, String> responseMap = getHttpResponseMap(httpGet, charset);
        responseMap.put("requestBody", getBody(httpParameters).toString());
        return responseMap;
    }

    /**
     * 获取响应信息
     * 
     * @param url 请求地址
     * @param json 请求参数
     * @return 响应数据
     */
    public static Map<String, String> getPostResp(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        return getPostResp(json, httpPost);
    }
  
    /**
     * 获取响应信息
     * 
     * @param json 请求参数
     * @param httpPost http请求
     * @return 响应数据
     */
    public static Map<String, String> getPostResp(String json, HttpPost httpPost) {
        StringEntity httpEntity = new StringEntity(json, Charset.forName(UTF_8));
        httpPost.setEntity(httpEntity);
        
        // 获取http请求响应内容
        Map<String, String> responseMap = getHttpResponseMap(httpPost, UTF_8);
        responseMap.put("requestBody", json);
        return responseMap;
    }

    /**
     * 获取响应信息
     *
     * @param url 请求地址
     * @param httpParameters 请求参数
     * @return 响应数据
     */
    public static Map<String, String> getPostByFormResp(String url, List<NameValuePair> httpParameters)  {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setEntity(new UrlEncodedFormEntity(httpParameters, Charset.forName(UTF_8)));

        // 获取http请求响应内容
        Map<String, String> responseMap = getHttpResponseMap(httpPost, UTF_8);
        responseMap.put("requestBody", getBody(httpParameters).toString());
        return responseMap;
    }

    /**
     * 请求HTTP 并把响应内容封装为Map类型
     * 
     * @param request HTTP请求
     * @param charset 响应字符集处理
     * @return HTTP请求的响应内容
     * @throws Exception
     */
    private static Map<String, String> getHttpResponseMap(HttpUriRequest request, String charset) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse;
        try {
            //执行请求
            httpResponse = httpClient.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        } 
        String responseBody;
        try {
            //获取响应体
            responseBody = EntityUtils.toString(httpResponse.getEntity(), charset);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        } 
        //获取响应码
        int responseStatus = httpResponse.getStatusLine().getStatusCode();
        
        // 流关闭
        IOUtils.closeQuietly(httpResponse);
        IOUtils.closeQuietly(httpClient);

        // HTTP响应内容处理
        Map<String, String> map = new HashMap<String, String>();
        map.put("requestUrl", request.getURI().toString());
        map.put("requestMethod", request.getMethod());
        map.put("requestTime", DateUtils.formatTime(new Date()));
        map.put("requestHeaders", getHeaders(request.getAllHeaders()));
        map.put("responseStatus", String.valueOf(responseStatus));
        map.put("responseBody", responseBody);
        map.put("responseTime", DateUtils.formatTime(new Date()));
        map.put("responseHeaders", getHeaders(httpResponse.getAllHeaders()));
        return map;
    }

    /**
     * 获取通信头部信息
     *
     * @param headers 数组
     * @return 响应数据
     */
    private static String getHeaders(Header... headers) {
        StringBuffer headerstr = new StringBuffer();
        if (headers != null && headers.length > 0) {
            for (Header header : headers) {
                headerstr.append(header.getName());
                headerstr.append("→");
                headerstr.append(header.getValue());
                headerstr.append("|");
            }
        }
        return headerstr.toString();
    }

    /**
     * 获取通信参数信息
     *
     * @param httpParameters 参数列表
     * @return 响应数据
     */
    private static String getBody(List<NameValuePair> httpParameters) {
        if (httpParameters == null || httpParameters.isEmpty()) {
            return "";
        }
        StringBuffer requestBody = new StringBuffer();
        Iterator<NameValuePair> it = httpParameters.iterator();
        while (it.hasNext()) {
            NameValuePair parameter = (NameValuePair) it.next();
            String encodedName = parameter.getName();
            String encodedValue = parameter.getValue();
            if (requestBody.length() > 0) {
                requestBody.append("&");
            }
            requestBody.append(encodedName);
            if (encodedValue != null) {
                requestBody.append("=");
                requestBody.append(encodedValue);
            }
        }
        return requestBody.toString();
    }
    
    /**
     * 从请求中获取请求参数列表
     * 
     * @param request httq请求
     * @param ignoreParamNames 忽略的http请求参数名称
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List<NameValuePair> getParametersByRequest(HttpServletRequest request, String... ignoreParamNames) {
        List<NameValuePair> requestParameters = new ArrayList<NameValuePair>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if (ArrayUtils.contains(ignoreParamNames, paramName))
               continue ;
            NameValuePair nameValuePair = getNameValuePair(paramName, request);
            if (nameValuePair != null)
                requestParameters.add(nameValuePair);
        }
        return requestParameters;
    }

    /**
     * 从请求中获取请求参数
     * 
     * @param request
     * @return
     */
    public static List<NameValuePair> getParametersByRequest(HttpServletRequest request) {
        return getParametersByRequest( request, "");
    }

    /**
     * 根据Http请求参数名返回NameValuePair对象
     * 
     * @param paramName Http请求参数名
     * @return NameValuePair对象
     */
    private static NameValuePair getNameValuePair(String paramName, HttpServletRequest request) {
        String[] paramValues = request.getParameterValues(paramName);
        if (paramValues.length == 1) {
            if ("user_pass".equals(paramName))
              //  paramValues[0] = KeyUtils.decrypt("user_pass", request);// RSA密码解密
            return new BasicNameValuePair(paramName, paramValues[0]);
        }
        return null;
    }

}