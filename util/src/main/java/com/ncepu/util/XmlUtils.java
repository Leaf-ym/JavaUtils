package com.ncepu.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/**
 * @author wengym
 * @version 1.0
 * @desc XML文档处理工具类
 * @date 2022/10/11 18:08
 */
public class XmlUtils {
    public static Document getDocumentByStr(String str, String encoding) {
        if (str == null || str.trim().length() == 0) {
            throw new NullPointerException("str");
        }
        SAXReader reader = new SAXReader();
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(str.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 获取指定名称的元素值
     *
     * @param element
     *
     * @param nodeName
     *
     * @author wengym
     *
     * @date 2022/10/11 19:18
     *
     * @return java.lang.String
     */
    public static String getStringValue(Element element, String nodeName) {
        if (element == null || nodeName == null || nodeName.trim().length() == 0) {
            return null;
        }
        Iterator iterator = element.elementIterator();
        String value = null;
        while (iterator.hasNext()) {
            Element node = (Element) iterator.next();
            if (isBaseElement(node)) {
                if (node.getName().equals(nodeName)) {
                    return node.getStringValue();
                }
                continue;
            }
            if (node.getName().equals(nodeName)) {
                return node.getStringValue();
            } else {
                value = getStringValue(node, nodeName);
            }
        }
        return value;
    }

    /**
     * 获取指定名称的元素值
     *
     * @param document
     *
     * @param nodeName
     *
     * @author wengym
     *
     * @date 2022/10/11 19:18
     *
     * @return java.lang.String
     */
    public static String getStringValue(Document document, String nodeName) {
        Element root = document.getRootElement();
        String value = getStringValue(root, nodeName);
        return value;
    }

    /**
     * 判断元素是否是基本元素（没有子元素的的元素）
     *
     * @param element
     *
     * @author wengym
     *
     * @date 2022/10/11 19:23
     *
     * @return java.lang.Boolean
     */
    public static Boolean isBaseElement(Element element) {
        Iterator iterator = element.elementIterator();
        if (iterator.hasNext()) {
            return false;
        }
        return true;
    }
}
