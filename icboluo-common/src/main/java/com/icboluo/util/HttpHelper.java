package com.icboluo.util;

import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author icboluo
 * @date 2020/10/27 22:56
 */
public class HttpHelper {

    /**
     * 下载的时候，给http响应里面写入对应类型参数
     *
     * @param response http response
     * @param fileName text.xlsx
     */
    public static void writeDownloadData(HttpServletResponse response, String fileName) {
        //设置ConetentType CharacterEncoding Header,需要在excelWriter.write()之前设置
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("mutipart/form-data");
    }

    /**
     * 发送get请求
     *
     * @param url        http url
     * @param paramName  param name
     * @param paramValue param value
     */
    public static String sendGet(String url, String paramName, String paramValue) {
        url = url + "/?" + paramName + "=" + paramValue;
        return sendGet(url);

    }

    /**
     * 这种方式发送和接收并不是同时关闭的，可以改成同时关闭的方式
     *
     * @param httpUrl 完整url地址
     */
    @SneakyThrows({IOException.class, URISyntaxException.class})
    private static String sendGet(String httpUrl) {
        CloseableHttpClient client = HttpClients.createDefault();
        try (client) {
            URL url = new URL(httpUrl);
            URI uri = new URI("http", url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), null);
            HttpGet httpGet = new HttpGet(uri);

            CloseableHttpResponse response = client.execute(httpGet);
            String result;
            try (response) {
                HttpEntity entity = response.getEntity();
//                如果关闭response就读不到里面的entity了，所以这个result写到里面
                result = EntityUtils.toString(entity);
            }
            return result;
        }
    }

    /**
     * 发送post请求
     *
     * @param url        http url
     * @param paramName  param name
     * @param paramValue param value
     * @return result
     */
    @SneakyThrows({IOException.class, URISyntaxException.class})
    public static String sendPost(String url, String paramName, String paramValue) {
        //设置参数
        List<NameValuePair> nvp = Collections.singletonList(
                new BasicNameValuePair(paramName, paramValue));
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(3000).setConnectionRequestTimeout(3000)
                .setSocketTimeout(3000).build();
        // 定义HttpClient
        CloseableHttpClient client = HttpClients.createDefault();
        try (client) {
            URI uri = new URI(url);
            // 实例化HTTP方法
            HttpPost httpPost = new HttpPost();
            httpPost.setURI(uri);
            httpPost.setEntity(new UrlEncodedFormEntity(nvp, StandardCharsets.UTF_8));
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse response = client.execute(httpPost);

            int code = response.getStatusLine().getStatusCode();
            if (code != 200) {
                throw new IcBoLuoException("状态码异常");
            }
            try (response) {
                InputStream is = response.getEntity().getContent();
                return IOHelper.readBufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            }
        }
    }
}
