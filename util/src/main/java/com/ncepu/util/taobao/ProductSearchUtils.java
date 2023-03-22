package com.ncepu.util.taobao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncepu.util.EasyExcelUtils.EasyExcelUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wengym
 * @version 1.0
 * @desc 搜索工具
 * @date 2023/3/21 8:41
 */
public class ProductSearchUtils {

    /**
     * 淘宝产品搜索
     *
     * @param key 搜索关键词，不能为空
     *
     * @param cookie
     *
     * @param pageIndex 页码，从1开始，1表示第一页
     *
     * @author wengym
     *
     * @date 2023/3/21 14:02
     *
     * @return java.lang.String
     */
    public static String taoBaoSearch(String key, String cookie, Integer pageIndex, String initiative_id) {
        if (key == null || key.trim().equals("")) {
            throw new NullPointerException("搜索关键词不能为空");
        }
        try {
            key = URLEncoder.encode(key, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }
        Integer pageSize = (pageIndex - 1) * 44;
        try {
            String url = "https://s.taobao.com/search?data-key=s&data-value="+pageSize+"&ajax=true&_ksTS=1679378121470_971&callback=jsonp972&q="+key+"&imgfile=&js=1&stats_click=search_radio_all:1&initiative_id="+initiative_id+"&ie=utf8&sort=sale-desc&bcoffset=0&p4ppushleft=,44&s=0&ntoffset=18&style=grid";
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Referer", "https://s.taobao.com/search?q="+key+"&imgfile=&js=1&stats_click=search_radio_all:1&initiative_id="+initiative_id+"&ie=utf8");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36");
            connection.setRequestProperty("Cookie", cookie);
            // 建立实际的连接
            connection.connect();
            //请求成功
            System.out.println("请求状态：" + connection.getResponseCode());
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //10MB的缓存
            byte[] buffer = new byte[10485760];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            String jsonString = baos.toString();
            baos.close();
            is.close();
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 导出产品标题
     *
     * @param list
     *
     * @param outPath
     *
     * @author wengym
     *
     * @date 2023/3/22 14:20
     *
     * @return void
     */
    public static void getProductTitles(List<ProductModel> list, String outPath) {
        if (list == null || list.isEmpty()) {
            throw new NullPointerException("产品信息列表不能为空");
        }
        List<List<String>> headList = new ArrayList<>();
        headList.add(new ArrayList<String>() {{
            add("产品标题");
        }});
        List<List<String>> dataList = new ArrayList<>();
        for (ProductModel model : list) {
            dataList.add(new ArrayList<String>() {{
                add(model.getRaw_title());
            }});
        }
        EasyExcelUtils.exportFile(dataList, headList, outPath);
    }

    /**
     * 获取产品信息列表
     *
     * @param key - 搜索关键词
     * @param page - 搜索多少页
     * @param url
     * @param cookie
     *
     * @author wengym
     *
     * @date 2023/3/21 14:22
     *
     * @return java.util.List<com.ncepu.util.taobao.ProductModel>
     */
    public static List<ProductModel> getProductList(String key, Integer page, String url, String cookie) {
        if (page == null || page == 0 || page < 0) {
            page = 1;
        }
        String initiative_id = getPropertyValue(url, "initiative_id");
        String jsonString;
        List<ProductModel> list;
        List<ProductModel> resultList = new ArrayList<>();
        for (int i = 1; i <= page; i++) {
            jsonString = ProductSearchUtils.taoBaoSearch(key, cookie, i, initiative_id);
            list = ProductSearchUtils.getProductList(jsonString);
            resultList.addAll(list);
        }
        return resultList;
    }

    /**
     * 获取产品信息列表
     *
     * @param scriptStr
     *
     * @author wengym
     *
     * @date 2023/3/22 8:53
     *
     * @return java.util.List<com.ncepu.util.taobao.ProductModel>
     */
    private static List<ProductModel> getProductList(String scriptStr) {
        scriptStr = scriptStr.replaceAll("jsonp[0-9]*\\(", "").replace(");", "");
        JSONObject jsonObject = JSON.parseObject(scriptStr);
        JSONArray jsonArray = jsonObject.getJSONObject("mods").getJSONObject("itemlist").getJSONObject("data").getJSONArray("auctions");
        List<ProductModel> list = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject product = (JSONObject) o;
            list.add(ProductModel.builder()
                    .raw_title(product.getString("raw_title"))
                    .view_sales(product.getString("view_sales"))
                    .build());
        }
        handleViewSales(list);
        return list;
    }

    public static void handleViewSales(List<ProductModel> list) {
        for (ProductModel model : list) {
            String view_sales = model.getView_sales();
            view_sales = view_sales.replace("+人收货", "");
            view_sales = view_sales.replace("万", "0000");
            model.setView_sales_num(Integer.valueOf(view_sales));
        }
    }

    public static String  getPropertyValue(String url, String key) {
        url = url.replaceFirst("\\?", "?&");
        url = url + "&";
        Matcher m = Pattern.compile("&"+key+".*?&").matcher(url);
        String value = "";
        while(m.find()) {
            value = m.group();
        }
        value = value.replace("&", "");
        value = value.split("=")[1];
        return value;
    }
}
