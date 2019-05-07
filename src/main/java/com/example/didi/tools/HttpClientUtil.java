package com.example.didi.tools;
/*
 *@author  zhangyufeng
 *@data 2018/8/14 下午7:00
 */

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.*;

public class HttpClientUtil {
    public static Logger logger = LoggerFactory.getLogger( HttpClientUtil.class );

    static final int maxTotalPool = 200;//连接池最大连接数

    static final int maxConPerRoute = 20;//每个路由的最大连接数

    static final int maxRoute = 10;//最大路由数

    static final int timeOut = 10000;//超时时间默认10秒

    static final String SECRETKEY = "&~5#!@*$^8*$@%";//jzt请求秘钥

    private static CloseableHttpClient httpClient = null;

    private final static Object syncLock = new Object();

    private static void config( HttpRequestBase httpRequestBase ) {
        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout( timeOut ).setConnectTimeout( timeOut ).setSocketTimeout( timeOut ).build();
        httpRequestBase.setConfig( requestConfig );
    }


    /**
     * 获取HttpClient对象
     */
    public static CloseableHttpClient getHttpClient( String url ) {
        String hostname = url.split( "/" )[2];
        int port = 80;
        if (hostname.contains( ":" )) {
            String[] arr = hostname.split( ":" );
            hostname = arr[0];
            port = Integer.parseInt( arr[1] );
        }
        if (httpClient == null) {
            synchronized (syncLock) {
                if (httpClient == null) {
                    httpClient = createHttpClient( maxTotalPool, maxConPerRoute, maxRoute, hostname, port );
                }
            }
        }
        return httpClient;
    }

    /**
     * 创建HttpClient对象
     */
    public static CloseableHttpClient createHttpClient( int maxTotal, int maxPerRoute, int maxRoute, String hostname, int port ) {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register( "http", plainsf ).register( "https", sslsf ).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager( registry );
        // 将最大连接数增加
        cm.setMaxTotal( maxTotal );
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute( maxPerRoute );
        HttpHost httpHost = new HttpHost( hostname, port );
        // 将目标主机的最大连接数增加
        cm.setMaxPerRoute( new HttpRoute( httpHost ), maxRoute );

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest( IOException exception, int executionCount, HttpContext context ) {
                if (executionCount >= 5) {// 如果已经重试了5次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt( context );
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager( cm ).setRetryHandler( httpRequestRetryHandler ).build();

        return httpClient;
    }

    private static void setPostParams( HttpPost httpost, Map<String, Object> params ) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add( new BasicNameValuePair( key, params.get( key ).toString() ) );
        }
        try {
            httpost.setEntity( new UrlEncodedFormEntity( nvps, "UTF-8" ) );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * POST请求URL获取内容
     */
    public static String post( String url, Map params, Map header ) {
        HttpPost httppost = new HttpPost( url );
        setHttpHeaderInfo( httppost, header );
        config( httppost );
        setPostParams( httppost, params );
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient( url ).execute( httppost, HttpClientContext.create() );
            if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString( entity, "utf-8" );
                EntityUtils.consume( entity );
                return result;
            }
        } catch (Exception e) {
            logger.error( "post请求发生异常,url:" + url + ",参数：" + JSONObject.toJSONString( params ), e );
        } finally {
            try {
                if (response != null) response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * GET请求URL获取内容
     */
    public static String get( String host, String param, Map<String, String> header ) {
//        try {
//            param=URLEncoder.encode(param,"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        String url = host + "?" + param;
        HttpGet httpget = new HttpGet( url );
        setHttpHeaderInfo( httpget, header );
        config( httpget );
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient( url ).execute( httpget, HttpClientContext.create() );
            if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString( entity, "utf-8" );
                EntityUtils.consume( entity );
                return result;
            }
        } catch (IOException e) {
            logger.error( "get请求发生异常，请求url：" + url, e );
        } finally {
            try {
                if (response != null) response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void setHttpHeaderInfo( HttpRequestBase httpMethod, Map<String, String> header ) {
        if (header != null && header.size() > 0) {
            Set<String> key = header.keySet();
            Iterator it = key.iterator();

            while (it.hasNext()) {
                String s = (String) it.next();
                httpMethod.setHeader( s, (String) header.get( s ) );
            }
        }
    }
}
