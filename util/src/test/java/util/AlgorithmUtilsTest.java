package util;

import com.ncepu.util.DatabaseUtils.example.UserModel;
import com.ncepu.util.PrintUtils;
import com.ncepu.util.StringUtils;
import com.ncepu.util.algorithm.AlgorithmUtils;
import com.ncepu.util.algorithm.SimHash;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * @author wengym
 * @version 1.0
 * @desc 算法工具测试类
 * @date 2023/09/02 10:40
 */
public class AlgorithmUtilsTest {
    @Test
    public void testIsCycle() {
        //String[] data  = {"P", "N", "休", "休", "A", "A", "P"};
        //String[] cycle = {"A", "A", "P", "N", "休", "休"};
        String[] data  = {"7", "8", "9", "11", "11", "7", "7"};
        String[] cycle = {"7", "7", "8", "9", "11", "11"};
        int cycleIndex = AlgorithmUtils.getCycleIndex(data, cycle);
        String[] newData = new String[7];
        int c = 0;
        // 寻找最后一位在循环中的位置
        int i = 0;
        while (i < data.length) {
            if (cycleIndex <= 6) {
                cycleIndex++;
            } else {
                newData[i++] = cycle[c % cycle.length];
            }
            c++;
        }
        PrintUtils.println(cycleIndex);
        PrintUtils.println(newData);
    }

    @Test
    public void testLevenshtein() {
        long startTime = System.currentTimeMillis();
        String str1 = "从研究对象而言，本研究不仅关注不孕女性的身心状况，同时分析了IVF-ET患者配偶的身心状况。近年来，研究者围绕IVF-ET女性患者心身状况及孕产结局等做了大量的报道，而对同样作为配子来源方的男性患者关注甚少。在IVF-ET诊疗过程中，受生理结构影响，医务人员更关注于女性的身心健康状况，除安排更多的诊疗、检查外，也会给予更多的讲解和帮助；对比来讲，男性患者往往在确定精液质量后，将被视为女方的陪同者身份来看待，缺乏必要的关注。受性观念影响，不能孕育子女常常与男性性功能及男子气概挂钩，可能给男性带来更大的性压力，产生自卑、自责等负性情绪，严重影响其心理健康。从身心症状的影响因素而言，本项目对不孕症女性心理困扰的因素探讨纳入家庭弹性变量。家庭弹性是指家庭适应压力和从逆境中恢复的能力，当家庭成员与有压力的生活事件作斗争时，可以提供一个支持性的环境。不孕症是一种影响整个家庭的持续疾病，由此产生的压力会对所有成员及其关系产生连锁反应。在中国，围绕不孕症的高度耻辱感通常会导致不孕女性不愿透露自己的问题，而拒绝寻求外部帮助；因此，家庭资源对处理不孕症至关重要。高的家庭弹性可以为开放和改善沟通提供良好的环境，这反过来可以鼓励家庭成员作为一个亲密的单位成功地应对与不孕相关的压力，逐渐适应IVF-ET治疗，减少心理困扰。目前，家庭弹性在不孕症人群中的作用未见报道。从干预方案而言，本研究基于互联网+的正念训练更适合于RPL人群。RPL患者除频繁胚胎停育流产外，多为无其他严重疾患的年轻群体，对网络设备的接受性好，且多为门诊就诊，多数患者都会在完善相关检查及诊疗后匆匆离院。因此，如果能够以网络设备为媒介与患者建立联系，将更易对其开展干预，本研究干预形式主要依托微信平台通过给研究对象发放正念音频的形式进行，具有自助式、灵活方便、简单易行的优点。";
        String str2 = "（1）国外研究情况分析发达国家自20世纪90年代末开始关注慢性病患者的护理问题，美国、英国等国为应对人口的老龄化，实现控制公共医疗开支，同时保证健康服务质量，不断强调慢性病护理问题及其对有效利用卫生资源的重要意义。有关慢性病的疾病管理，最早由美国疾病管理协会提出并加以强调。疾病管理（DiseaseManagement）是一个全民医疗保健的干预和沟通系统，强调病人的自我保健（selfcare）。这个定义被广泛接受，且通过了3个美国认证机构的认证。疾病管理支持良好的护患关系和医疗保健计划，应用循证指导实践和患者授权策略，强调评估的基础上制订计划与目标，以合作性的模式加以实现，并注重患者的自我管理及教育。然而，第一代的疾病管理方案运行过程中暴露出技术方面不是很成功的短板，美国疾病管理10年的经验为我们如何更好地管理慢性疾病提供了借鉴，行为改变科学显示另外一个巨大的机会，提高疾病管理方案、改变患者预期、授权患者管理疾病，成为下一阶段的探索方向。与本研究目标和路径相吻合。（2）国内研究情况分析当前，我国护理人员非常关注慢性病护理，许多护理工作者开展了一些有益的研究和实践，但是，由于我国社会发展的地域性差别，患者对疾病的认知以及主要照顾者的疾病管理参与程度参差不齐，无论是健康管理模式还是实际运作方式和内容上均有较大差异。国内慢性病自我护理研究普遍缺乏针对性、系统性和规范性，且均研究单一病种，缺乏干预前的认知评估，强调的是治疗和健康教育，尚未树立慢性病患者认知及健康管理观念。与国内外同类研究综合比较发现，本研究更符合国际慢性病疾病管理的发展要求，研制的工具库能客观评估包括早产儿及其父母在内的9类常见慢性病患者的认知水平，提高患者的疾病自我管理能力，强调参与疾病管理，取得显著成效，从研究病种的选取、设计思路的缜密、研究方法的严谨、干预手段的系统方面均更为科学、先进。";
        StringUtils.levenshtein(str1, str2);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + (float) (endTime - startTime) / 1000 + "s");
    }

    @Test
    public void testSimHashStr() throws IOException {
        long startTime = System.currentTimeMillis();
        String str1 = "从研究对象而言，本研究不仅关注不孕女性的身心状况，同时分析了IVF-ET患者配偶的身心状况。近年来，研究者围绕IVF-ET女性患者心身状况及孕产结局等做了大量的报道，而对同样作为配子来源方的男性患者关注甚少。在IVF-ET诊疗过程中，受生理结构影响，医务人员更关注于女性的身心健康状况，除安排更多的诊疗、检查外，也会给予更多的讲解和帮助；对比来讲，男性患者往往在确定精液质量后，将被视为女方的陪同者身份来看待，缺乏必要的关注。受性观念影响，不能孕育子女常常与男性性功能及男子气概挂钩，可能给男性带来更大的性压力，产生自卑、自责等负性情绪，严重影响其心理健康。从身心症状的影响因素而言，本项目对不孕症女性心理困扰的因素探讨纳入家庭弹性变量。家庭弹性是指家庭适应压力和从逆境中恢复的能力，当家庭成员与有压力的生活事件作斗争时，可以提供一个支持性的环境。不孕症是一种影响整个家庭的持续疾病，由此产生的压力会对所有成员及其关系产生连锁反应。在中国，围绕不孕症的高度耻辱感通常会导致不孕女性不愿透露自己的问题，而拒绝寻求外部帮助；因此，家庭资源对处理不孕症至关重要。高的家庭弹性可以为开放和改善沟通提供良好的环境，这反过来可以鼓励家庭成员作为一个亲密的单位成功地应对与不孕相关的压力，逐渐适应IVF-ET治疗，减少心理困扰。目前，家庭弹性在不孕症人群中的作用未见报道。从干预方案而言，本研究基于互联网+的正念训练更适合于RPL人群。RPL患者除频繁胚胎停育流产外，多为无其他严重疾患的年轻群体，对网络设备的接受性好，且多为门诊就诊，多数患者都会在完善相关检查及诊疗后匆匆离院。因此，如果能够以网络设备为媒介与患者建立联系，将更易对其开展干预，本研究干预形式主要依托微信平台通过给研究对象发放正念音频的形式进行，具有自助式、灵活方便、简单易行的优点。";
        String str2 = "（1）国外研究情况分析发达国家自20世纪90年代末开始关注慢性病患者的护理问题，美国、英国等国为应对人口的老龄化，实现控制公共医疗开支，同时保证健康服务质量，不断强调慢性病护理问题及其对有效利用卫生资源的重要意义。有关慢性病的疾病管理，最早由美国疾病管理协会提出并加以强调。疾病管理（DiseaseManagement）是一个全民医疗保健的干预和沟通系统，强调病人的自我保健（selfcare）。这个定义被广泛接受，且通过了3个美国认证机构的认证。疾病管理支持良好的护患关系和医疗保健计划，应用循证指导实践和患者授权策略，强调评估的基础上制订计划与目标，以合作性的模式加以实现，并注重患者的自我管理及教育。然而，第一代的疾病管理方案运行过程中暴露出技术方面不是很成功的短板，美国疾病管理10年的经验为我们如何更好地管理慢性疾病提供了借鉴，行为改变科学显示另外一个巨大的机会，提高疾病管理方案、改变患者预期、授权患者管理疾病，成为下一阶段的探索方向。与本研究目标和路径相吻合。（2）国内研究情况分析当前，我国护理人员非常关注慢性病护理，许多护理工作者开展了一些有益的研究和实践，但是，由于我国社会发展的地域性差别，患者对疾病的认知以及主要照顾者的疾病管理参与程度参差不齐，无论是健康管理模式还是实际运作方式和内容上均有较大差异。国内慢性病自我护理研究普遍缺乏针对性、系统性和规范性，且均研究单一病种，缺乏干预前的认知评估，强调的是治疗和健康教育，尚未树立慢性病患者认知及健康管理观念。与国内外同类研究综合比较发现，本研究更符合国际慢性病疾病管理的发展要求，研制的工具库能客观评估包括早产儿及其父母在内的9类常见慢性病患者的认知水平，提高患者的疾病自我管理能力，强调参与疾病管理，取得显著成效，从研究病种的选取、设计思路的缜密、研究方法的严谨、干预手段的系统方面均更为科学、先进。";
        //String str1 = "最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen），得到相似度最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）。";
        //String str2 = "最后：先最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）取两长度的最大值maxLen，用1-（需要操axLen），得到相似度。";
        SimHash hash1 = new SimHash(str1, 64);
        SimHash hash2 = new SimHash(str2, 64);
        PrintUtils.println("字符串：" + str1);
        PrintUtils.println("字符串：" + str2);
        PrintUtils.println("海明距离为：" + hash1.hammingDistance(hash2));
        PrintUtils.println("相似度为：" + hash1.similarity(hash2));
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + (float) (endTime - startTime) / 1000 + "s");
    }
    @Test
    public void testSimHash() throws IOException {
        long startTime = System.currentTimeMillis();
        String str1 = "11111111111112222222222";
        String str2 = "11111111111112222222222";
        String str3 = "马马马马马马马马马马马马马";
        String str4 = "最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen），得到相似度最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）。";
        String str5 = "最后：先最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）最后：先取两个字符串长度的最大值maxLen，用1-（需要操作数除maxLen）取两长度的最大值maxLen，用1-（需要操axLen），得到相似度。";
        String str6 = "牛牛牛牛牛牛牛牛牛牛牛牛牛";
        UserModel userModel1 = new UserModel();
        userModel1.setId("1");
        userModel1.setContent(str1);
        UserModel userModel2 = new UserModel();
        userModel2.setId("2");
        userModel2.setContent(str2);
        UserModel userModel3 = new UserModel();
        userModel3.setId("3");
        userModel3.setContent(str3);
        UserModel userModel4 = new UserModel();
        userModel4.setId("4");
        userModel4.setContent(str4);
        UserModel userModel5 = new UserModel();
        userModel5.setId("5");
        userModel5.setContent(str5);
        UserModel userModel6 = new UserModel();
        userModel6.setId("6");
        userModel6.setContent(str6);
        List<UserModel> list = new ArrayList<>();
        list.add(userModel1);
        list.add(userModel2);
        list.add(userModel3);
        list.add(userModel4);
        list.add(userModel5);
        list.add(userModel6);
        Set<String> hasHandle = new HashSet<>();
        for (UserModel s1 : list) {
            SimHash hash1 = new SimHash(s1.getContent(), 64);
            //List characters1 = new ArrayList();
            //List characters2 = new ArrayList();
            //characters1 = hash1.subByDistance(hash1, 3);
            for (UserModel s2 : list) {
                if (hasHandle.contains(s1.getId() + "-" + s2.getId()) || hasHandle.contains(s2.getId() + "-" + s1.getId())) {
                    continue;
                }
                hasHandle.add(s1.getId() + "-" + s2.getId());
                hasHandle.add(s2.getId() + "-" + s1.getId());
                if (!s1.getId().equals(s2.getId())) {
                    SimHash hash2 = new SimHash(s2.getContent(), 64);
                    int dishm = hash1.hammingDistance(hash2);
                    /*if (dishm <= 5) {
                        System.out.println("s1.id:" + s1.getId() + " s2.id:" + s2.getId() + " dishm:" + dishm);
                    }*/
                    System.out.println("s1.id:" + s1.getId() + " s2.id:" + s2.getId() + " dishm:" + dishm);
                    PrintUtils.println("字符串：" + s1.getContent());
                    PrintUtils.println("字符串：" + s2.getContent());
                    PrintUtils.println("相似度为：" + hash1.similarity(hash2));
                    //}
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + (float) (endTime - startTime) / 1000 + "s");
    }
}
