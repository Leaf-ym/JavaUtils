package util;

import com.ncepu.util.PrintUtils;
import com.ncepu.util.taobao.ProductModel;
import com.ncepu.util.taobao.ProductSearchUtils;
import org.junit.Test;

import java.util.List;

/**
 * @author wengym
 * @version 1.0
 * @desc 产品搜索工具测试类
 * @date 2023/3/21 8:42
 */
public class ProductSearchUtilsTest {

    @Test
    public void testTaoBaoSearch() {
        String jsonString = ProductSearchUtils.taoBaoSearch();
        List<ProductModel> list = ProductSearchUtils.getProductListBySort(jsonString);
        PrintUtils.println(list);
    }
}
