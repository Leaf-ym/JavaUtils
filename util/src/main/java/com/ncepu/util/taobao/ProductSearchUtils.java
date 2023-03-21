package com.ncepu.util.taobao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public static String taoBaoSearch() {
        try {
            String url = "https://s.taobao.com/search?data-key=sort&data-value=sale-desc&ajax=true&_ksTS=1679361563031_934&callback=jsonp935&q=%E6%9D%AF%E5%AD%90&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20230321&ie=utf8&sort=sale-desc";
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Referer", "https://s.taobao.com/search?q=杯子&imgfile=&js=1&stats_click=search_radio_all:1&initiative_id=staobaoz_20230321&ie=utf8");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36");
            connection.setRequestProperty("Cookie", "cna=QAqTG1FX3UcCAXF3HHwKnAkC; t=91aca7b8b400cb7eae07523fb89a71fb; _m_h5_tk=f8b1ed1d4178c20bb5e767cc4719a8f5_1679368134464; _m_h5_tk_enc=6ab91ff663046c91db5036e68b93bd74; xlly_s=1; _samesite_flag_=true; cookie2=1a8d98bac0dde6d19bad1a5f9eae05e0; _tb_token_=e0b3b8b747535; sgcookie=E100ncyWwxvyY0xuOxm1ZQTBrka31o+YZleJhoCYUEUP4RcSh2vNuX1iQ47oYAjnUb6Ju3nataIJ44bOHetff+e73usQIi7DIMkC8lPOBtJhfjk=; unb=2215597361037; uc1=cookie14=UoezRmXnWKZ3Nw==&cookie21=U+GCWk/7pY/F&cookie16=VT5L2FSpNgq6fDudInPRgavC+Q==&pas=0&cookie15=U+GCWk/75gdr5Q==&existShop=false; uc3=vt3=F8dCsfeV6RxlvEE2Aas=&lg2=Vq8l+KCLz3/65A==&nk2=F5RBw5OAPqp/XSJLOg==&id2=UUpgQyzbDtqEX1e9Vw==; csg=f81f6155; lgc=tb46198901265; cancelledSubSites=empty; cookie17=UUpgQyzbDtqEX1e9Vw==; dnk=tb46198901265; skt=7a40f3d5bcf7f12d; existShop=MTY3OTM1OTU2MA==; uc4=nk4=0@FY4KpmmlWsGsOP33GOFtkkxxtAR4Sd9G&id4=0@U2gqzJqpAeFUnsRk6/zeZEQmw6NLCbyf; tracknick=tb46198901265; _cc_=WqG3DMC9EA==; _l_g_=Ug==; sg=57c; _nk_=tb46198901265; cookie1=URmvxY0ZNe8RWfHso9785r2mKFsowCHpRYw8IRHyNdU=; JSESSIONID=F41EA52380120DCFAA6B0061510133B6; isg=BL-_QRxD5-7jS-RcIgXivzkQTpNJpBNGKRdJsVGM_261YN7iWXG-lno2ojCeOOu-; l=fBachbogT33DHu4XBO5Bnurza779PIRb8sPzaNbMiIEGa6thtF94INCsB3-eSdtjgTfY-etz5TzfvdE2riadg2HvCbKrCyClrxJ6-bpU-L5..; tfstk=cq8NBdcTEV3ZiyRxewb2LXiFLuIOZTdMiy55jxdZ-nnznasGi2eAt3MfTsNUnGf..");
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

    public static List<ProductModel> getProductList(String scriptStr) {
        Matcher m = Pattern.compile("g_page_config.*;").matcher(scriptStr);
        String g_page_config = null;
        while (m.find()) {
            g_page_config = m.group();
        }
        g_page_config = g_page_config.replace("g_page_config = ", "");
        g_page_config = g_page_config.replace(";", "");
        JSONObject jsonObject = JSON.parseObject(g_page_config);
        return null;
    }

    public static List<ProductModel> getProductListBySort(String scriptStr) {
        scriptStr = scriptStr.replaceAll("jsonp[0-9]*\\(", "").replace(");", "");
        JSONObject jsonObject = JSON.parseObject(scriptStr);
        JSONArray jsonArray = jsonObject.getJSONObject("mods").getJSONObject("itemlist").getJSONObject("data").getJSONArray("auctions");
        List<ProductModel> list = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject product = (JSONObject)o;
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
}
