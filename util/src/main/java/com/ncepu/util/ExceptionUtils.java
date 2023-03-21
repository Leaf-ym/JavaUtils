package com.ncepu.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author wengym
 * @version 1.0
 * @desc 异常处理工具
 * @date 2023/3/16 18:19
 */
public class ExceptionUtils {
    /**
     * 获取异常的完整信息
     *
     * @param e
     *
     * @author wengym
     *
     * @date 2023/3/16 18:21
     *
     * @return java.lang.String
     */
    public static String getExceptionInfo(Exception e) {
        if (e == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(out));
        String info = new String(out.toByteArray());
        return info;
    }
}
