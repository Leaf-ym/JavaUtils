package com.ncepu.util.algorithm;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/** 
 * @author wengym
 * @version 1.0
 * @desc 基于哈希的文本相似度计算算法
 * @date 2023/9/2 14:30
 */
public class SimHash {

    /**
     * 字符串内容
     */
    private String content;

    /**
     * 数字指纹/签名（大整数）
     */
    private BigInteger intSimHash;

    /**
     * 数字指纹/签名（字符串）
     */
    private String strSimHash;

    /**
     * 哈希位数，默认是64位
     */
    private int hashBits = 64;

    public SimHash(String content) throws IOException {
        this.content = content;
        this.intSimHash = this.simHash();
    }

    public SimHash(String tokens, int hashBits) throws IOException {
        this.content = tokens;
        this.hashBits = hashBits;
        this.intSimHash = this.simHash();
    }

    /**
     * 生成数字指纹/签名
     *
     * @author wengym
     *
     * @date 2023/9/2 11:07
     *
     * @return java.math.BigInteger
     */
    public BigInteger simHash() throws IOException {
        // 定义特征向量/数组
        int[] v = new int[this.hashBits];
        // 英文分词
        // StringTokenizer stringTokens = new StringTokenizer(this.tokens);
        // while (stringTokens.hasMoreTokens()) {
        // String temp = stringTokens.nextToken();
        // }
        // 1、中文分词，分词器采用 IKAnalyzer3.2.8 ，仅供演示使用，新版 API 已变化。
        StringReader reader = new StringReader(this.content);
        // 当为true时，分词器进行最大词长切分
        IKSegmenter ik = new IKSegmenter(reader, true);
        Lexeme lexeme;
        String word;
        while ((lexeme = ik.next()) != null) {
            word = lexeme.getLexemeText();
            // 注意停用词会被干掉
            //System.out.println(word);
            // 2、将每一个分词hash为一组固定长度的数列.比如 64bit 的一个整数.
            BigInteger t = this.hash(word);
            for (int i = 0; i < this.hashBits; i++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(i);
                // 3、建立一个长度为64的整数数组(假设要生成64位的数字指纹,也可以是其它数字),
                // 对每一个分词hash后的数列进行判断,如果是1000...1,那么数组的第一位和末尾一位加1,
                // 中间的62位减一,也就是说,逢1加1,逢0减1.一直到把所有的分词hash数列全部判断完毕.
                if (t.and(bitmask).signum() != 0) {
                    // 这里是计算整个文档的所有特征的向量和
                    // 这里实际使用中需要 +- 权重，比如词频，而不是简单的 +1/-1，
                    v[i] += 1;
                } else {
                    v[i] -= 1;
                }
            }
        }
        BigInteger fingerprint = new BigInteger("0");
        StringBuffer simHashBuffer = new StringBuffer();
        for (int i = 0; i < this.hashBits; i++) {
            // 4、最后对数组进行判断,大于0的记为1,小于等于0的记为0,得到一个 64bit 的数字指纹/签名.
            if (v[i] >= 0) {
                fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
                simHashBuffer.append("1");
            } else {
                simHashBuffer.append("0");
            }
        }
        this.strSimHash = simHashBuffer.toString();
        //System.out.println(this.strSimHash + " length " + this.strSimHash.length());
        System.out.println(fingerprint.toString(2));
        return fingerprint;
    }

    /**
     * 哈希计算，返回指定位的整数
     *
     * @param source
     *
     * @author wengym
     *
     * @date 2023/9/2 10:22
     *
     * @return java.math.BigInteger
     */
    private BigInteger hash(String source) {
        if (source == null || source.length() == 0) {
            return new BigInteger("0");
        } else {
            char[] sourceArray = source.toCharArray();
            BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
            BigInteger m = new BigInteger("1000003");
            BigInteger mask = new BigInteger("2").pow(this.hashBits).subtract(new BigInteger("1"));
            for (char item : sourceArray) {
                BigInteger temp = BigInteger.valueOf((long) item);
                x = x.multiply(m).xor(temp).and(mask);
            }
            x = x.xor(new BigInteger(String.valueOf(source.length())));
            if (x.equals(new BigInteger("-1"))) {
                x = new BigInteger("-2");
            }
            return x;
        }
    }

    /**
     * 海明距离，0表示相似度是百分百，越大相似度就越小
     * 64位simhash，海明距离在3以内的文本都可以认为是近重复文本。
     *
     * @param other
     *
     * @author wengym
     *
     * @date 2023/9/2 10:25
     *
     * @return int
     */
    public int hammingDistance(SimHash other) {
        BigInteger x = this.intSimHash.xor(other.intSimHash);
        int tot = 0;
        // 统计x中二进制位数为1的个数
        // 我们想想，一个二进制数减去1，那么，从最后那个1（包括那个1）后面的数字全都反了，
        // 对吧，然后，n&(n-1)就相当于把后面的数字清0，
        // 我们看n能做多少次这样的操作就OK了。
        while (x.signum() != 0) {
            tot += 1;
            x = x.and(x.subtract(new BigInteger("1")));
        }
        return tot;
    }

    public int getDistance(String str1, String str2) {
        int distance;
        if (str1.length() != str2.length()) {
            distance = -1;
        } else {
            distance = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }

    public List subByDistance(SimHash simHash, int distance) {
        // 分成几组来检查
        int numEach = this.hashBits / (distance + 1);
        List characters = new ArrayList();
        StringBuffer buffer = new StringBuffer();
        int k = 0;
        //for (int i = 0; i < this.intSimHash.bitLength(); i++) {
        for (int i = 0; i < 64; i++) {
            // 当且仅当设置了指定的位时，返回 true
            boolean sr = simHash.intSimHash.testBit(i);
            if (sr) {
                buffer.append("1");
            } else {
                buffer.append("0");
            }
            if ((i + 1) % numEach == 0) {
                // 将二进制转为BigInteger
                BigInteger eachValue = new BigInteger(buffer.toString(), 2);
                System.out.println("----" + eachValue);
                buffer.delete(0, buffer.length());
                characters.add(eachValue);
            }
        }
        return characters;
    }

    /**
     * 计算相似度，1.0表示完全相似，越小相似度越低。
     *
     * @param other
     *
     * @author wengym
     *
     * @date 2023/9/2 11:05
     *
     * @return java.lang.Double
     */
    public Double similarity(SimHash other) {
        double d1 = this.intSimHash.doubleValue();
        double d2 = other.intSimHash.doubleValue();
        if (d1 > d2) {
            // 大于
            return d2 / d1;
        } else {
            return d1 / d2;
        }
    }
}
