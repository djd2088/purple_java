package com.rui.xb.common.utils;

import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by du on 2017/12/7.
 */
public class HttpUtil {


    public static String get(String url) throws Exception{
        return get(url,null);
    }

    public static String get(String url,Map<String,String> header) throws Exception{

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        if (header != null){
            Set<String> keySet = header.keySet();
            for (String key : keySet){
                request.addHeader(key,header.get(key));
            }
        }
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String strResult = EntityUtils.toString(response.getEntity());
            return strResult;
        }
        return null;
    }


    public static String postForm(String url, Map<String, String> params) throws Exception {

       return postForm(url,params,null);
    }

    public static String postForm(String url, Map<String, String> params,Map<String,String> header) throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig config = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        httpPost.setConfig(config);
        httpPost.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString());
        if (header != null){
            Set<String> keySet = header.keySet();
            for (String key : keySet){
                httpPost.setHeader(key,header.get(key));
            }
        }
        List<NameValuePair> pairList = new ArrayList<>();
        for (String key : params.keySet()) {
            pairList.add(new BasicNameValuePair(key, params.get(key)));
        }
        HttpEntity entity = new UrlEncodedFormEntity(pairList, "utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        try {
            HttpEntity responseEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(responseEntity);
            }
        } finally {
            if (response != null)
                response.close();
            if (httpPost != null)
                httpPost.releaseConnection();
            if (httpClient != null)
                httpClient.close();

        }
        return null;
    }

    public static String postJson(String url, Map<String,Object> params) throws Exception{
        return postJson(url,new GsonBuilder().setDateFormat("yyyy-MM-dd hh-mm-ss").create().toJson(params));
    }

    public static String postJson(String url, String params) throws Exception {
        return postJson(url,params,null);
    }

    public static String postJson(String url, String params,Map<String,String> header) throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        HttpEntity httpEntity = new StringEntity(params);
        httpPost.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        if (header != null){
            Set<String> keySet = header.keySet();
            for (String key : keySet){
                httpPost.setHeader(key,header.get(key));
            }
        }
        httpPost.setEntity(httpEntity);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000).build();
        httpPost.setConfig(config);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } finally {
            if (response != null)
                response.close();
            if (httpPost != null)
                httpPost.releaseConnection();
            if (httpClient != null)
                httpClient.close();
        }

        return null;
    }


}
