package util;

import com.ncepu.util.DateUtils;
import com.ncepu.util.EasyExcelUtils.EasyExcelUtils;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.taobao.ProductModel;
import com.ncepu.util.taobao.ProductSearchUtils;
import com.ncepu.util.taobao.TailPinWordModel;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wengym
 * @version 1.0
 * @desc 产品搜索工具测试类
 * @date 2023/3/21 8:42
 */
public class ProductSearchUtilsTest {
    @Test
    public void testGetProductTailPinList() {
        String filePath = "D:\\杯子-长尾词.xlsx";
        List<TailPinWordModel> longTailList = EasyExcelUtils.readFile(filePath, TailPinWordModel.class);
        ProductSearchUtils.getProductTailPinList(longTailList);
        PrintUtils.println(longTailList);
        List<TailPinWordModel> filterList = ProductSearchUtils.filterTailPin(longTailList, 0, 800);
        PrintUtils.println(filterList);
    }

    @Test
    public void testGetProductTitles() {
        String url = "https://s.taobao.com/search?data-key=s&data-value=44&ajax=true&_ksTS=1679378121470_971&callback=jsonp972&q=杯子&imgfile=&js=1&stats_click=search_radio_all:1&initiative_id=staobaoz_20230321&ie=utf8&sort=sale-desc&bcoffset=0&p4ppushleft=,44&s=0&ntoffset=18&style=grid";
        String cookie = "cna=QAqTG1FX3UcCAXF3HHwKnAkC; t=91aca7b8b400cb7eae07523fb89a71fb; _m_h5_tk=f8b1ed1d4178c20bb5e767cc4719a8f5_1679368134464; _m_h5_tk_enc=6ab91ff663046c91db5036e68b93bd74; xlly_s=1; _samesite_flag_=true; cookie2=1a8d98bac0dde6d19bad1a5f9eae05e0; _tb_token_=e0b3b8b747535; sgcookie=E100ncyWwxvyY0xuOxm1ZQTBrka31o+YZleJhoCYUEUP4RcSh2vNuX1iQ47oYAjnUb6Ju3nataIJ44bOHetff+e73usQIi7DIMkC8lPOBtJhfjk=; unb=2215597361037; uc1=cookie14=UoezRmXnWKZ3Nw==&cookie21=U+GCWk/7pY/F&cookie16=VT5L2FSpNgq6fDudInPRgavC+Q==&pas=0&cookie15=U+GCWk/75gdr5Q==&existShop=false; uc3=vt3=F8dCsfeV6RxlvEE2Aas=&lg2=Vq8l+KCLz3/65A==&nk2=F5RBw5OAPqp/XSJLOg==&id2=UUpgQyzbDtqEX1e9Vw==; csg=f81f6155; lgc=tb46198901265; cancelledSubSites=empty; cookie17=UUpgQyzbDtqEX1e9Vw==; dnk=tb46198901265; skt=7a40f3d5bcf7f12d; existShop=MTY3OTM1OTU2MA==; uc4=nk4=0@FY4KpmmlWsGsOP33GOFtkkxxtAR4Sd9G&id4=0@U2gqzJqpAeFUnsRk6/zeZEQmw6NLCbyf; tracknick=tb46198901265; _cc_=WqG3DMC9EA==; _l_g_=Ug==; sg=57c; _nk_=tb46198901265; cookie1=URmvxY0ZNe8RWfHso9785r2mKFsowCHpRYw8IRHyNdU=; JSESSIONID=F41EA52380120DCFAA6B0061510133B6; isg=BL-_QRxD5-7jS-RcIgXivzkQTpNJpBNGKRdJsVGM_261YN7iWXG-lno2ojCeOOu-; l=fBachbogT33DHu4XBO5Bnurza779PIRb8sPzaNbMiIEGa6thtF94INCsB3-eSdtjgTfY-etz5TzfvdE2riadg2HvCbKrCyClrxJ6-bpU-L5..; tfstk=cq8NBdcTEV3ZiyRxewb2LXiFLuIOZTdMiy55jxdZ-nnznasGi2eAt3MfTsNUnGf..";
        String[] keys = {"杯子", "刷子", "行李箱", "雨伞", "手电筒", "吹风机", "插座", "圆珠笔", "鼠标垫", "窗帘"};
        for (String key : keys) {
            // 只需要首页的产品标题
            List<ProductModel> resultList = ProductSearchUtils.getProductList(key, 1, url, cookie);
            String outPath = "D:\\" + key + "-产品标题" + DateUtils.getNowDate(DateUtils.FORMAT_YYYYMMDDHHMISS) + ".xlsx";
            ProductSearchUtils.getProductTitles(resultList, outPath);
        }
    }

    @Test
    public void testTaoBaoSearch() throws UnsupportedEncodingException {
        String url = "https://s.taobao.com/search?data-key=s&data-value=44&ajax=true&_ksTS=1679378121470_971&callback=jsonp972&q=杯子&imgfile=&js=1&stats_click=search_radio_all:1&initiative_id=staobaoz_20230321&ie=utf8&sort=sale-desc&bcoffset=0&p4ppushleft=,44&s=0&ntoffset=18&style=grid";
        String cookie = "cna=QAqTG1FX3UcCAXF3HHwKnAkC; t=91aca7b8b400cb7eae07523fb89a71fb; _m_h5_tk=f8b1ed1d4178c20bb5e767cc4719a8f5_1679368134464; _m_h5_tk_enc=6ab91ff663046c91db5036e68b93bd74; xlly_s=1; _samesite_flag_=true; cookie2=1a8d98bac0dde6d19bad1a5f9eae05e0; _tb_token_=e0b3b8b747535; sgcookie=E100ncyWwxvyY0xuOxm1ZQTBrka31o+YZleJhoCYUEUP4RcSh2vNuX1iQ47oYAjnUb6Ju3nataIJ44bOHetff+e73usQIi7DIMkC8lPOBtJhfjk=; unb=2215597361037; uc1=cookie14=UoezRmXnWKZ3Nw==&cookie21=U+GCWk/7pY/F&cookie16=VT5L2FSpNgq6fDudInPRgavC+Q==&pas=0&cookie15=U+GCWk/75gdr5Q==&existShop=false; uc3=vt3=F8dCsfeV6RxlvEE2Aas=&lg2=Vq8l+KCLz3/65A==&nk2=F5RBw5OAPqp/XSJLOg==&id2=UUpgQyzbDtqEX1e9Vw==; csg=f81f6155; lgc=tb46198901265; cancelledSubSites=empty; cookie17=UUpgQyzbDtqEX1e9Vw==; dnk=tb46198901265; skt=7a40f3d5bcf7f12d; existShop=MTY3OTM1OTU2MA==; uc4=nk4=0@FY4KpmmlWsGsOP33GOFtkkxxtAR4Sd9G&id4=0@U2gqzJqpAeFUnsRk6/zeZEQmw6NLCbyf; tracknick=tb46198901265; _cc_=WqG3DMC9EA==; _l_g_=Ug==; sg=57c; _nk_=tb46198901265; cookie1=URmvxY0ZNe8RWfHso9785r2mKFsowCHpRYw8IRHyNdU=; JSESSIONID=F41EA52380120DCFAA6B0061510133B6; isg=BL-_QRxD5-7jS-RcIgXivzkQTpNJpBNGKRdJsVGM_261YN7iWXG-lno2ojCeOOu-; l=fBachbogT33DHu4XBO5Bnurza779PIRb8sPzaNbMiIEGa6thtF94INCsB3-eSdtjgTfY-etz5TzfvdE2riadg2HvCbKrCyClrxJ6-bpU-L5..; tfstk=cq8NBdcTEV3ZiyRxewb2LXiFLuIOZTdMiy55jxdZ-nnznasGi2eAt3MfTsNUnGf..";
        String key = "杯子";
        List<ProductModel> resultList = ProductSearchUtils.getProductList(key, 2, url, cookie);
        PrintUtils.println(resultList.size());
        PrintUtils.println(resultList);
        EasyExcelUtils.exportFile(resultList, "D:\\" + key + "-产品词" + System.currentTimeMillis() + ".xlsx");
    }

    @Test
    public void testGetPropertyValue() {
        String url = "https://s.taobao.com/search?data-key=s&data-value=44&ajax=true&_ksTS=1679378121470_971&callback=jsonp972&q=杯子&imgfile=&js=1&stats_click=search_radio_all:1&initiative_id=staobaoz_20230321&ie=utf8&sort=sale-desc&bcoffset=0&p4ppushleft=,44&s=0&ntoffset=18&style=grid";
        String initiative_id = ProductSearchUtils.getPropertyValue(url, "initiative_id");
        PrintUtils.println(initiative_id);
    }
}
