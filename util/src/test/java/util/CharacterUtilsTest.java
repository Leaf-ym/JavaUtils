package util;

import com.ncepu.util.CharacterUtils;
import com.ncepu.util.PrintUtils;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc 字符工具测试类
 * @date 2023/1/11 9:32
 */
public class CharacterUtilsTest {
    @Test
    public void testToChar() {
        char r1 = CharacterUtils.toChar(97);
        PrintUtils.println(r1);
        char r2 = CharacterUtils.toChar(98);
        PrintUtils.println(r2);
        char r3 = CharacterUtils.toChar(65);
        PrintUtils.println(r3);
        char r4 = CharacterUtils.toChar(66);
        PrintUtils.println(r4);
    }

    @Test
    public void testToAscii() {
        int r1 = CharacterUtils.toAscii('a');
        PrintUtils.println(r1);
        int r2 = CharacterUtils.toAscii('A');
        PrintUtils.println(r2);
    }
}
