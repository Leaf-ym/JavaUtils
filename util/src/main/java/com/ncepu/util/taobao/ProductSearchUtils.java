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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
     * @param key       搜索关键词，不能为空
     * @param cookie
     * @param pageIndex 页码，从1开始，1表示第一页
     * @return java.lang.String
     * @author wengym
     * @date 2023/3/21 14:02
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
            String url = "https://s.taobao.com/search?data-key=s&data-value=" + pageSize + "&ajax=true&_ksTS=1679378121470_971&callback=jsonp972&q=" + key + "&imgfile=&js=1&stats_click=search_radio_all:1&initiative_id=" + initiative_id + "&ie=utf8&sort=sale-desc&bcoffset=0&p4ppushleft=,44&s=0&ntoffset=18&style=grid";
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Referer", "https://s.taobao.com/search?q=" + key + "&imgfile=&js=1&stats_click=search_radio_all:1&initiative_id=" + initiative_id + "&ie=utf8");
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
     * 过滤掉不在最小和最大区间内容的尾销词
     *
     * @param list
     * @param min
     * @param max
     * @return void
     * @author wengym
     * @date 2023/3/25 20:14
     */
    public static List<TailPinWordModel> filterTailPin(List<TailPinWordModel> list, Integer min, Integer max) {
        if (list == null || list.isEmpty()) {
            return list;
        }
        List<TailPinWordModel> filterList = list.stream().filter(
                item -> item.getTailPinNum() != null && item.getTailPinNum() >= min && item.getTailPinNum() <= max)
                .collect(Collectors.toList());
        return filterList;
    }

    /**
     * 查询长尾词产品尾销
     *
     * @param
     * @return void
     * @author wengym
     * @date 2023/3/25 16:58
     */
    public static void getProductTailPinList(List<TailPinWordModel> list) {
        Integer THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for (TailPinWordModel model : list) {
            newFixedThreadPool.execute(() -> {
                model.setTailPinNum(getProductTailPin(model.getKeyWord()));
            });
        }
        newFixedThreadPool.shutdown();
        while (true) {
            if (newFixedThreadPool.isTerminated()) {
                break;
            }
        }
    }

    private static Integer getProductTailPin(String key) {
        String url = "https://s.taobao.com/search?data-key=s&data-value=44&ajax=true&_ksTS=1679378121470_971&callback=jsonp972&q=杯子&imgfile=&js=1&stats_click=search_radio_all:1&initiative_id=staobaoz_20230321&ie=utf8&sort=sale-desc&bcoffset=0&p4ppushleft=,44&s=0&ntoffset=18&style=grid";
        String cookie = "cna=QAqTG1FX3UcCAXF3HHwKnAkC; t=91aca7b8b400cb7eae07523fb89a71fb; _m_h5_tk=f8b1ed1d4178c20bb5e767cc4719a8f5_1679368134464; _m_h5_tk_enc=6ab91ff663046c91db5036e68b93bd74; xlly_s=1; _samesite_flag_=true; cookie2=1a8d98bac0dde6d19bad1a5f9eae05e0; _tb_token_=e0b3b8b747535; sgcookie=E100ncyWwxvyY0xuOxm1ZQTBrka31o+YZleJhoCYUEUP4RcSh2vNuX1iQ47oYAjnUb6Ju3nataIJ44bOHetff+e73usQIi7DIMkC8lPOBtJhfjk=; unb=2215597361037; uc1=cookie14=UoezRmXnWKZ3Nw==&cookie21=U+GCWk/7pY/F&cookie16=VT5L2FSpNgq6fDudInPRgavC+Q==&pas=0&cookie15=U+GCWk/75gdr5Q==&existShop=false; uc3=vt3=F8dCsfeV6RxlvEE2Aas=&lg2=Vq8l+KCLz3/65A==&nk2=F5RBw5OAPqp/XSJLOg==&id2=UUpgQyzbDtqEX1e9Vw==; csg=f81f6155; lgc=tb46198901265; cancelledSubSites=empty; cookie17=UUpgQyzbDtqEX1e9Vw==; dnk=tb46198901265; skt=7a40f3d5bcf7f12d; existShop=MTY3OTM1OTU2MA==; uc4=nk4=0@FY4KpmmlWsGsOP33GOFtkkxxtAR4Sd9G&id4=0@U2gqzJqpAeFUnsRk6/zeZEQmw6NLCbyf; tracknick=tb46198901265; _cc_=WqG3DMC9EA==; _l_g_=Ug==; sg=57c; _nk_=tb46198901265; cookie1=URmvxY0ZNe8RWfHso9785r2mKFsowCHpRYw8IRHyNdU=; JSESSIONID=F41EA52380120DCFAA6B0061510133B6; isg=BL-_QRxD5-7jS-RcIgXivzkQTpNJpBNGKRdJsVGM_261YN7iWXG-lno2ojCeOOu-; l=fBachbogT33DHu4XBO5Bnurza779PIRb8sPzaNbMiIEGa6thtF94INCsB3-eSdtjgTfY-etz5TzfvdE2riadg2HvCbKrCyClrxJ6-bpU-L5..; tfstk=cq8NBdcTEV3ZiyRxewb2LXiFLuIOZTdMiy55jxdZ-nnznasGi2eAt3MfTsNUnGf..";
        // 只需要首页的产品标题
        List<ProductModel> resultList = ProductSearchUtils.getProductList(key, 1, url, cookie);
        if (resultList != null && !resultList.isEmpty()) {
            Integer tailPinNum = handleViewSales(resultList.get(resultList.size() - 1));
            return tailPinNum;
        }
        return -1;
    }

    /**
     * 导出产品标题
     *
     * @param list
     * @param outPath
     * @return void
     * @author wengym
     * @date 2023/3/22 14:20
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
     * @param key    - 搜索关键词
     * @param page   - 搜索多少页
     * @param url
     * @param cookie
     * @return java.util.List<com.ncepu.util.taobao.ProductModel>
     * @author wengym
     * @date 2023/3/21 14:22
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
     * @return java.util.List<com.ncepu.util.taobao.ProductModel>
     * @author wengym
     * @date 2023/3/22 8:53
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
            handleViewSales(model);
        }
    }

    public static Integer handleViewSales(ProductModel model) {
        String view_sales = model.getView_sales();
        view_sales = view_sales.replace("人收货", "");
        view_sales = view_sales.replace("+", "");
        view_sales = view_sales.replace("万", "0000");
        model.setView_sales_num(Integer.valueOf(view_sales));
        return model.getView_sales_num();
    }

    public static String getPropertyValue(String url, String key) {
        url = url.replaceFirst("\\?", "?&");
        url = url + "&";
        Matcher m = Pattern.compile("&" + key + ".*?&").matcher(url);
        String value = "";
        while (m.find()) {
            value = m.group();
        }
        value = value.replace("&", "");
        value = value.split("=")[1];
        return value;
    }
}
