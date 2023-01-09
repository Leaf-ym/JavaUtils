package com.ncepu.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author wengym
 * @version 1.0
 * @desc HTTP方法请求工具
 * @date 2022/3/28 11:33
 */
@Slf4j
public class HttpUtils {


    public enum ContentTypeEnum {
        /**
         * 空值
         */
        NONE("空值", "none"),
        /**
         * 表单数据
         */
        MULTIPART_FORM_DATA("表单数据", "multipart/form-data"),
        /**
         * JSON数据
         */
        APPLICATION_JSON("JSON数据", "application/json"),
        /**
         * XML数据
         */
        APPLICATION_XML("XML数据", "application/xml"),
        /**
         * JavaScript数据
         */
        APPLICATION_JAVASCRIPT("JavaScript数据", "application/javascript"),
        /**
         * 普通文本数据
         */
        TEXT_PLAIN("普通文本数据", "text/plain"),
        /**
         * HTML数据
         */
        TEXT_HTML("HTML数据", "text/html");

        private String label;
        private String value;

        ContentTypeEnum(String label, String value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public String getValue() {
            return value;
        }
    }

    public static String get(String url, Map<String, Object> params, Map<String, Object> headers, String encoding) {
        System.out.println("访问URL为：[" + url + "]，请求参数为：[" + params + "]，请求头为：[" + headers + "]");
        // 参数预处理
        if (url == null || url.trim().length() == 0) {
            return null;
        }
        if (encoding == null || "".equals(encoding.trim())) {
            encoding = "UTF-8";
        }
        // 定义GET方法
        GetMethod method = new GetMethod(url);
        // 设置请求头
        dealGetHeader(method, headers);
        // 处理请求参数
        dealGetParams(method, params);
        // 请求方法并返回响应内容
        String content = null;
        try {
            // 请求方法
            HttpClient httpClient = new HttpClient();
            Integer statusCode = httpClient.executeMethod(method);
            System.out.println("GET请求的状态码为：[" + statusCode + "]，状态行为[" + method.getStatusLine() + "]");
            // 获取响应内容，并按指定编码方式编码
            content = new String(method.getResponseBody(), encoding);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
        return content;
    }

    /**
     * HTTP的POST请求
     *
     * @param url
     * @return java.lang.String
     * @author wengym
     * @date 2022/8/19 14:07
     */
    public static String post(String url) {
        String content = post(url, new HashMap<>(), new HashMap<>(), ContentTypeEnum.TEXT_PLAIN);
        return content;
    }

    /**
     * HTTP的POST请求
     *
     * @param url
     * @return java.lang.String
     * @author wengym
     * @date 2022/8/19 14:07
     */
    public static String post(String url, Map<String, Object> params, ContentTypeEnum contentType) {
        String content = post(url, params, new HashMap<>(), null, contentType.getValue());
        return content;
    }

    /**
     * HTTP的POST请求
     *
     * @param url
     * @return java.lang.String
     * @author wengym
     * @date 2022/8/19 14:07
     */
    public static String post(String url, Map<String, Object> params, Map<String, Object> headers, ContentTypeEnum contentType) {
        String content = post(url, params, headers, null, contentType.getValue());
        return content;
    }

    /**
     * HTTP的POST请求
     *
     * @param url
     * @return java.lang.String
     * @author wengym
     * @date 2022/8/19 14:07
     */
    public static String post(String url, Map<String, Object> params, Map<String, Object> headers, String encoding, String contentType) {
        System.out.println("访问URL为：[" + url + "]，请求参数为：[" + params + "]，请求头为：[" + headers + "]");
        try {
            // URL中不能带有中文，需要转码
            url = new URI(url, false, "UTF-8").toString();
            System.out.println("URL转码：" + new URI(url, false, "UTF-8").toString());
        } catch (URIException e) {
            e.printStackTrace();
        }
        // 参数预处理
        if (url == null || url.trim().length() == 0) {
            return null;
        }
        if (encoding == null || "".equals(encoding.trim())) {
            encoding = "UTF-8";
        }
        // 定义POST方法
        PostMethod method = new PostMethod(url);
        // 设置请求头
        dealPostHeader(method, headers);
        // 处理请求参数
        dealPostParams(method, params, encoding, contentType);
        // 请求方法并返回响应内容
        String content = null;
        try {
            // 请求方法
            HttpClient httpClient = new HttpClient();
            Integer statusCode = httpClient.executeMethod(method);
            System.out.println("POST请求的状态码为：[" + statusCode + "]，状态行为[" + method.getStatusLine() + "]");
            // 获取响应内容，并按指定编码方式编码
            content = new String(method.getResponseBody(), encoding);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
        return content;
    }

    private static void dealGetHeader(GetMethod method, Map<String, Object> headers) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        // 设置请求头
        headers.put("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
        for (String str : headers.keySet()) {
            method.setRequestHeader(str, headers.get(str) + "");
        }
    }

    private static void dealPostHeader(PostMethod method, Map<String, Object> headers) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        // 设置请求头
        headers.put("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
        for (String str : headers.keySet()) {
            method.setRequestHeader(str, headers.get(str) + "");
        }
    }

    /**
     * 处理POST请求的参数
     *
     * @param method
     * @param params
     * @param contentType
     *
     * @author wengym
     *
     * @date 2022/8/19 15:44
     *
     * @return void
     */
    private static void dealPostParams(PostMethod method, Map<String, Object> params, String encoding, String contentType) {
        if (params == null) {
            params = new HashMap<>();
        }
        if (params != null && params.keySet().size() < 1) {
            return;
        }
        if (ContentTypeEnum.APPLICATION_JSON.getValue().equals(contentType)) {
            // JSON请求参数
            try {
                RequestEntity entity = new StringRequestEntity(JSON.toJSONString(params), ContentTypeEnum.APPLICATION_JSON.getValue(), encoding);
                method.setRequestEntity(entity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if (ContentTypeEnum.MULTIPART_FORM_DATA.getValue().equals(contentType)) {
            // 表单请求参数
            for (String str : params.keySet()) {
                Object o = params.get(str);
                if (o != null) {
                    if (o instanceof String) {
                        method.addParameter(new NameValuePair(str, o.toString()));
                    }
                    if (o instanceof String[]) {
                        String[] s = (String[]) o;
                        for (int i = 0; i < s.length; i++) {
                            method.addParameter(new NameValuePair(str, s[i]));
                        }
                    }
                }
            }
        }
    }

    /**
     * 处理请求的参数
     *
     * @param method
     * @param params
     *
     * @author wengym
     *
     * @date 2022/8/19 15:44
     *
     * @return void
     */
    private static void dealGetParams(GetMethod method, Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        if (params != null && params.keySet().size() < 1) {
            return;
        }
        List<NameValuePair> list = new ArrayList<>();
        for (String str : params.keySet()) {
            list.add(new NameValuePair(str, params.get(str) + ""));
        }
        NameValuePair[] arr = (NameValuePair[])list.toArray();
        method.setQueryString(arr);
    }
}

