package com.ncepu.util;

/**
 * @author wengym
 * @version 1.0
 * @desc HTML文本工具类
 * @date 2022/10/11 11:52
 */
public class HtmlToTextUtils {

    /**
     * 去除HTML字符串中的所有标签
     *
     * @param htmlStr
     * @return java.lang.String
     * @author wengym
     * @date 2022/10/11 13:37
     */
    public static String deleteHtmlTags(String htmlStr) {
        htmlStr = deleteHtmlTagsIgnore(htmlStr, null);
        // 返回文本字符串
        return htmlStr.trim();
    }

    /**
     * 去除HTML字符串中的除了ignoreTag标签之外的所有标签
     *
     * @param htmlStr
     * @param ignoreTag
     * @return java.lang.String
     * @author wengym
     * @date 2022/10/11 13:39
     */
    public static String deleteHtmlTagsIgnore(String htmlStr, String ignoreTag) {
        //定义script的正则表达式，去除js可以防止注入
        String scriptRegex = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
        String styleRegex = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式，去除标签，只提取文字内容，保留特定标签
        String htmlRegex = "<[^>]+>";
        if (ignoreTag != null && ignoreTag.trim().length() > 0) {
            htmlRegex = "(?!<(" + ignoreTag + ").*?>)<.*?>";
        }
        //定义空格,回车\r,换行符\n,制表符\t
        String spaceRegex = "\\s*";

        // 过滤script标签
        htmlStr = htmlStr.replaceAll(scriptRegex, "");
        // 过滤style标签
        htmlStr = htmlStr.replaceAll(styleRegex, "");
        // 过滤html标签
        htmlStr = htmlStr.replaceAll(htmlRegex, "");
        // 过滤空格等
        htmlStr = htmlStr.replaceAll(spaceRegex, "");
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        // 返回文本字符串
        return htmlStr.trim();
    }

    /**
     * 获取HTML代码里的内容
     *
     * @param htmlStr
     * @return
     */
    public static String getTextFromHtml(String htmlStr) {
        if (CommonUtil.isNullStr(htmlStr)) {
            return htmlStr;
        }
        //去除html标签
        htmlStr = deleteHtmlTags(htmlStr);
        //去除空格" "
        htmlStr = htmlStr.replaceAll(" ", "");
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        return htmlStr;
    }

}
