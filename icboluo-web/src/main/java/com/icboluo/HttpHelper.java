package com.icboluo;

import com.icboluo.util.IcBoLuoException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author icboluo
 */
@Slf4j
public class HttpHelper {


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
    @SneakyThrows
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
    @SneakyThrows
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
            StringBuilder sb = new StringBuilder();
            String line;
            String NL = System.getProperty("line.separator");
            try (response) {
                InputStream is = response.getEntity().getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                try (br) {
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append(NL);
                    }
                }
            }
            return sb.toString();
        }
    }
}
