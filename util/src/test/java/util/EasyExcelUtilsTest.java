package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncepu.model.*;
import com.ncepu.util.*;
import com.ncepu.util.DatabaseUtils.DatabaseUtils;
import com.ncepu.util.EasyExcelUtils.EasyExcelUtils;
import com.ncepu.util.ImageUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * @author wengym
 * @version 1.0
 * @desc easyexcel工具测试类
 * @date 2022/10/21 14:03
 */
public class EasyExcelUtilsTest {

    /**
     * 视频：videoUrl，辅助材料地址：otherStuffUrl，发明照片：innovatePhoto，护士执业证图片：nurseNumberPhoto，证件照：userPicUrl
     *
     * @return void
     * @author wengym
     * @date 2024/1/15 14:36
     */
    @Test
    public void handleContributePapers() throws Exception {
        String path = "C:\\Users\\Administrator\\Desktop\\contribute_papers.xlsx";
        JSONObject jsonObject = EasyExcelUtils.readFile(path);
        JSONArray data = jsonObject.getJSONArray("data");
        ExecutorService threadPool = ThreadUtils.createThreadPool();
        for (Object m : data) {
            threadPool.execute(() -> {
                LinkedHashMap<Integer, String> single = (LinkedHashMap) m;
                String paperTitle = single.get(8);
                paperTitle = paperTitle.replace("/", "-");
                paperTitle = paperTitle.replace("+", "-");
                paperTitle = paperTitle.replace(" ", "");
                String author = single.get(4);
                String gender = "1".equals(single.get(23)) ? "男" : "女";
                String authorCell = single.get(21);
                String year = DateUtils.formatDate(single.get(16), DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS, "YYYY");
                // 附件
                String fileKey = single.get(11);
                // 视频
                String videoUrl = single.get(19);
                // 辅助材料
                JSONArray otherStuffUrl = JSONArray.parseArray(single.get(55));
                // 用户头像
                String userPicUrl = single.get(50);
                // 省份推荐意见附件
                String provinceOpinionFileUrl = single.get(60);
                // 身份证图片正面
                String idCardImgFront = single.get(63);
                // 身份证图片反面
                String idCardImgBack = single.get(64);
                // 护士证
                JSONArray nurseCertificate = JSONArray.parseArray(single.get(65));
                // 盖章的省市推荐意见文件
                String recommendationFileUrl = single.get(67);

                PrintUtils.println(paperTitle + "-" + author + "-" + gender + "-" + authorCell + "-" + year);
                String baseName = "-" + author + "-" + paperTitle;

                String baseDir = "C:\\Users\\Administrator\\Desktop\\杰出护理\\" + year + "\\" + author + "-" + authorCell + "-" + gender + "-" + paperTitle + "";
                if (FileUtils.isExist(baseDir + "【完毕】")) {
                    return;
                }
                FileUtils.deleteFiles(baseDir);
                try {
                    if (!CommonUtil.isNullStr(fileKey)) {
                        String suffix = FileUtils.getFileSuffix(fileKey);
                        String outPath = baseDir + "\\附件" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(fileKey, outPath);
                    }
                    if (!CommonUtil.isNullStr(videoUrl)) {
                        String name = "视频";
                        String url = videoUrl;
                        String suffix = FileUtils.getFileSuffix(url);
                        String safeVidelUrl = PlayUtil.getSafeUrl(url, 120, "");
                        String dir = baseDir + "\\视频\\";
                        String outPath = dir + name + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(safeVidelUrl, outPath);
                    }
                    if (otherStuffUrl != null && !otherStuffUrl.isEmpty()) {
                        for (Object o : otherStuffUrl) {
                            JSONObject object = (JSONObject) o;
                            String name = object.getString("name");
                            name = name.replace(".", "-");
                            name = name.replace(" ", "");
                            name = name.replace(",", "-");
                            name = name.replace("'", "-");
                            name = name.replace(":", "-");
                            String url = object.getString("url");
                            String suffix = FileUtils.getFileSuffix(url);
                            String dir = baseDir + "\\辅助材料\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            FileUtils.downloadNetworkFile(url, outPath);
                        }
                    }
                    if (!CommonUtil.isNullStr(userPicUrl)) {
                        String suffix = FileUtils.getFileSuffix(userPicUrl);
                        String outPath = baseDir + "\\用户头像" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(userPicUrl, outPath);
                    }
                    if (!CommonUtil.isNullStr(provinceOpinionFileUrl)) {
                        String suffix = FileUtils.getFileSuffix(provinceOpinionFileUrl);
                        String outPath = baseDir + "\\省份推荐意见附件" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(provinceOpinionFileUrl, outPath);
                    }
                    if (!CommonUtil.isNullStr(idCardImgFront)) {
                        String suffix = FileUtils.getFileSuffix(idCardImgFront);
                        String outPath = baseDir + "\\身份证图片正面" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(idCardImgFront, outPath);
                    }
                    if (!CommonUtil.isNullStr(idCardImgBack)) {
                        String suffix = FileUtils.getFileSuffix(idCardImgBack);
                        String outPath = baseDir + "\\身份证图片反面" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(idCardImgBack, outPath);
                    }
                    if (nurseCertificate != null && !nurseCertificate.isEmpty()) {
                        for (Object o : nurseCertificate) {
                            JSONObject object = (JSONObject) o;
                            String name = object.getString("name");
                            name = name.replace(".", "-");
                            name = name.replace(" ", "");
                            name = name.replace(",", "-");
                            name = name.replace("'", "-");
                            name = name.replace(":", "-");
                            String url = object.getString("url");
                            String suffix = FileUtils.getFileSuffix(url);
                            String dir = baseDir + "\\护士证\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            FileUtils.downloadNetworkFile(url, outPath);
                        }
                    }
                    if (!CommonUtil.isNullStr(recommendationFileUrl)) {
                        String suffix = FileUtils.getFileSuffix(recommendationFileUrl);
                        String outPath = baseDir + "\\盖章的省市推荐意见文件" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(recommendationFileUrl, outPath);
                    }

                    /////////////////////////////////
                    if (FileUtils.isExist(baseDir)) {
                        FileUtils.renameTo(baseDir, baseDir + "【完毕】");
                        PrintUtils.println("处理完毕");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    FileUtils.deleteFiles(baseDir);
                }
            });
        }
        threadPool.shutdown();
        while (true) {
            if (threadPool.isTerminated()) {
                break;
            }
        }
    }

    /**
     * 视频：videoUrl，辅助材料地址：otherStuffUrl，发明照片：innovatePhoto，护士执业证图片：nurseNumberPhoto，证件照：userPicUrl
     *
     * @return void
     * @author wengym
     * @date 2024/1/15 14:36
     */
    @Test
    public void handleContributeScienceReward() throws Exception {
        String path = "C:\\Users\\Administrator\\Desktop\\contribute_science_reward.xlsx";
        JSONObject jsonObject = EasyExcelUtils.readFile(path);
        JSONArray data = jsonObject.getJSONArray("data");
        ExecutorService threadPool = ThreadUtils.createThreadPool();
        for (Object m : data) {
            threadPool.execute(() -> {
                LinkedHashMap<Integer, String> single = (LinkedHashMap) m;
                String projectName = single.get(8);
                projectName = projectName.replace("/", "-");
                projectName = projectName.replace("+", "-");
                projectName = projectName.replace(" ", "");
                String majorAuthorName = single.get(7);
                String sex = CommonUtil.isNullStr(single.get(13)) ? "未知" : single.get(13);
                String cellPhone = single.get(11);
                String scienceYear = single.get(81);
                String workType = single.get(80);
                if (CommonUtil.isNullStr(workType)) {
                    workType = "旧版科技奖";
                } else {
                    if ("BOOK".equals(workType)) {
                        workType = "科普图书";
                    }
                    if ("VIDEO".equals(workType)) {
                        workType = "科普短视频";
                    }
                    if ("FORMAL".equals(workType)) {
                        workType = "新版科技奖";
                    }
                }
                // 主申报人标准证件照照片
                JSONArray userPic = JSONArray.parseArray(single.get(14));
                // 视频
                JSONArray videoUrl = JSONArray.parseArray(single.get(66));
                // 其他证明材料
                JSONArray otherSupportMaterialUrl = JSONArray.parseArray(single.get(87));
                // 附件材料
                String fileUrl = single.get(88);
                // 省份推荐意见附件
                String provinceOpinionFileUrl = single.get(89);
                // 附件材料压缩包
                String otherFileUrl = single.get(90);
                // 附件材料1到4
                String otherFileUrl1 = single.get(91);
                String otherFileUrl2 = single.get(92);
                String otherFileUrl3 = single.get(93);
                String otherFileUrl4 = single.get(94);

                PrintUtils.println(projectName + "-" + majorAuthorName + "-" + sex + "-" + cellPhone + "-" + scienceYear);
                String baseName = "-" + majorAuthorName + "-" + projectName;

                String baseDir = "C:\\Users\\Administrator\\Desktop\\科技奖\\" + scienceYear + "\\" + workType + "\\" + majorAuthorName + "-" + cellPhone + "-" + sex + "-" + projectName + "";
                if (FileUtils.isExist(baseDir + "【完毕】")) {
                    return;
                }
                FileUtils.deleteFiles(baseDir);
                try {
                    if (userPic != null && !userPic.isEmpty()) {
                        for (Object o : userPic) {
                            JSONObject object = (JSONObject) o;
                            String name = object.getString("name");
                            name = name.replace(".", "-");
                            name = name.replace(" ", "");
                            name = name.replace(",", "-");
                            name = name.replace("'", "-");
                            name = name.replace(":", "-");
                            String url = object.getString("url");
                            String suffix = FileUtils.getFileSuffix(url);
                            if (suffix.length() > 16) {
                                continue;
                            }
                            String dir = baseDir + "\\主申报人标准证件照照片\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            try {
                                FileUtils.downloadNetworkFile(url, outPath);
                            } catch (Exception e) {

                                e.printStackTrace();
                            }

                        }
                    }
                    if (videoUrl != null && !videoUrl.isEmpty()) {
                        for (Object o : videoUrl) {
                            JSONObject object = (JSONObject) o;
                            String name = object.getString("name");
                            name = name.replace(".", "-");
                            name = name.replace(" ", "");
                            name = name.replace(",", "-");
                            name = name.replace("'", "-");
                            name = name.replace(":", "-");
                            String url = object.getString("url");
                            String suffix = FileUtils.getFileSuffix(url);
                            String safeVidelUrl = PlayUtil.getSafeUrl(url, 120, "");
                            String dir = baseDir + "\\视频\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            FileUtils.downloadNetworkFile(safeVidelUrl, outPath);
                        }
                    }
                    if (otherSupportMaterialUrl != null && !otherSupportMaterialUrl.isEmpty()) {
                        for (Object o : otherSupportMaterialUrl) {
                            JSONObject object = (JSONObject) o;
                            String name = object.getString("name");
                            name = name.replace(".", "-");
                            name = name.replace(" ", "");
                            name = name.replace(",", "-");
                            name = name.replace("'", "-");
                            name = name.replace(":", "-");
                            String url = object.getString("url");
                            if (majorAuthorName.equals("申琳")) {
                                PrintUtils.println(majorAuthorName);
                            }
                            if (url.contains("uploadId")) {
                                url = url.replaceAll("\\?.*", "");
                            }
                            if (CommonUtil.isNullStr(url)) {
                                continue;
                            }
                            String suffix = FileUtils.getFileSuffix(url);
                            if (suffix.length() > 16) {
                                continue;
                            }
                            String dir = baseDir + "\\其他证明材料\\";
                            String outPath = dir + name + baseName + "." + suffix;

                            FileUtils.downloadNetworkFile(url, outPath);
                        }
                    }
                    if (!CommonUtil.isNullStr(fileUrl)) {
                        String suffix = FileUtils.getFileSuffix(fileUrl);
                        String outPath = baseDir + "\\附件材料" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(fileUrl, outPath);
                    }
                    if (!CommonUtil.isNullStr(provinceOpinionFileUrl)) {
                        String suffix = FileUtils.getFileSuffix(provinceOpinionFileUrl);
                        String outPath = baseDir + "\\省份推荐意见附件" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(provinceOpinionFileUrl, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl);
                        String outPath = baseDir + "\\附件材料压缩包" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl1)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl1);
                        String outPath = baseDir + "\\附件材料1" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl1, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl2)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl2);
                        String outPath = baseDir + "\\附件材料2" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl2, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl3)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl3);
                        String outPath = baseDir + "\\附件材料3" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl3, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl4)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl4);
                        String outPath = baseDir + "\\附件材料4" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl4, outPath);
                    }

                    /////////////////////////////////
                    if (FileUtils.isExist(baseDir)) {
                        FileUtils.renameTo(baseDir, baseDir + "【完毕】");
                        PrintUtils.println("处理完毕");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    FileUtils.deleteFiles(baseDir);
                }
            });
        }
        threadPool.shutdown();
        while (true) {
            if (threadPool.isTerminated()) {
                break;
            }
        }
    }

    /**
     * 视频：videoUrl，辅助材料地址：otherStuffUrl，发明照片：innovatePhoto，护士执业证图片：nurseNumberPhoto，证件照：userPicUrl
     *
     * @return void
     * @author wengym
     * @date 2024/1/15 14:36
     */
    @Test
    public void handleContributeInnovate() throws Exception {
        String path = "C:\\Users\\Administrator\\Desktop\\contribute_innovate.xlsx";
        JSONObject jsonObject = EasyExcelUtils.readFile(path);
        JSONArray data = jsonObject.getJSONArray("data");
        ExecutorService threadPool = ThreadUtils.createThreadPool();
        for (Object m : data) {
            threadPool.execute(() -> {
                LinkedHashMap<Integer, String> single = (LinkedHashMap) m;
                String innovateName = single.get(1);
                innovateName = FileUtils.validateFileName(innovateName);
                String userName = single.get(4);
                String gender = "1".equals(single.get(5)) ? "男" : "女";
                String cellPhone = single.get(9);
                String innovateYear = single.get(34);
                // 视频
                JSONArray videoUrl = JSONArray.parseArray(single.get(17));
                // 辅助材料
                JSONArray otherStuffUrl = JSONArray.parseArray(single.get(20));
                // 发明照片
                JSONArray innovatePhoto = JSONArray.parseArray(single.get(21));
                // 护士执业证图片
                JSONArray nurseNumberPhoto = JSONArray.parseArray(single.get(37));
                // 证件照
                JSONArray userPicUrl = JSONArray.parseArray(single.get(40));
                // PDF盖章文件
                String fileUrl = single.get(54);
                // 评审意见文件
                String adviceFileUrl = single.get(55);
                // 主申报人标准证件照照片
                JSONArray userPic = JSONArray.parseArray(single.get(71));
                // 附件材料压缩包
                String otherFileUrl = single.get(98);
                // 附件材料1到10
                String otherFileUrl1 = single.get(99);
                String otherFileUrl2 = single.get(100);
                String otherFileUrl3 = single.get(101);
                String otherFileUrl4 = single.get(102);
                String otherFileUrl5 = single.get(103);
                String otherFileUrl6 = single.get(104);
                String otherFileUrl7 = single.get(105);
                String otherFileUrl8 = single.get(106);
                String otherFileUrl9 = single.get(107);
                String otherFileUrl10 = single.get(108);
                // 证明照片
                String invoatePic = single.get(109);
                // 盖章的推荐单位评审意见文件
                String reviewCommentUrl = single.get(113);

                PrintUtils.println(innovateName + "-" + userName + "-" + gender + "-" + cellPhone + "-" + innovateYear);
                String baseName = "-" + userName + "-" + innovateName;
                String baseDir = "C:\\Users\\Administrator\\Desktop\\创新发明\\" + innovateYear + "\\" + userName + "-" + cellPhone + "-" + gender + "-" + innovateName + "";
                if (FileUtils.isExist(baseDir + "【完毕】")) {
                    return;
                }
                FileUtils.deleteFiles(baseDir);
                if ("张爱英".equals(userName)) {
                    PrintUtils.println(userName);
                }
                try {
                    if (videoUrl != null && !videoUrl.isEmpty()) {
                        for (Object o : videoUrl) {
                            JSONObject object = (JSONObject) o;
                            String name = object.getString("name");
                            if (name.endsWith(".mp4")) {
                                name = name.replace(".mp4", "");
                            }
                            if (name.endsWith(".m4v")) {
                                name = name.replace(".m4v", "");
                            }
                            name = FileUtils.validateFileName(name);
                            String url = object.getString("url");
                            String suffix = FileUtils.getFileSuffix(url);
                            String safeVidelUrl = PlayUtil.getSafeUrl(url, 120, "");
                            String dir = baseDir + "\\视频\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            FileUtils.downloadNetworkFile(safeVidelUrl, outPath);
                        }
                    }
                    if (otherStuffUrl != null && !otherStuffUrl.isEmpty()) {
                        for (Object o : otherStuffUrl) {
                            JSONObject object;
                            try {
                                object = (JSONObject) o;
                            } catch (Exception e) {
                                continue;
                            }
                            String name = object.getString("name");
                            name = FileUtils.validateFileName(name);
                            String url = object.getString("url");
                            if (CommonUtil.isNullStr(url)) {
                                continue;
                            }
                            String suffix = FileUtils.getFileSuffix(url);
                            String dir = baseDir + "\\辅助材料\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            FileUtils.downloadNetworkFile(url, outPath);
                        }
                    }
                    if (innovatePhoto != null && !innovatePhoto.isEmpty()) {
                        for (Object o : innovatePhoto) {
                            JSONObject object;
                            try {
                                object = (JSONObject) o;
                            } catch (Exception e) {
                                continue;
                            }
                            String name = object.getString("name");
                            name = FileUtils.validateFileName(name);
                            String url = object.getString("url");
                            if (url.startsWith("blob")) {
                                continue;
                            }
                            String suffix = FileUtils.getFileSuffix(url);
                            String dir = baseDir + "\\发明照片\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            FileUtils.downloadNetworkFile(url, outPath);

                        }
                    }
                    if (nurseNumberPhoto != null && !nurseNumberPhoto.isEmpty()) {
                        int index = 1;
                        for (Object o : nurseNumberPhoto) {
                            JSONObject object = (JSONObject) o;
                            String name = "护士执业证图片" + index;
                            index++;
                            name = FileUtils.validateFileName(name);
                            String url = object.getString("url");
                            if (url.startsWith("blob")) {
                                continue;
                            }
                            String suffix = FileUtils.getFileSuffix(url);
                            String dir = baseDir + "\\护士执业证图片\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            FileUtils.downloadNetworkFile(url, outPath);
                        }
                    }
                    if (userPicUrl != null && !userPicUrl.isEmpty()) {
                        for (Object o : userPicUrl) {
                            JSONObject object = (JSONObject) o;
                            String name = object.getString("name");
                            name = FileUtils.validateFileName(name);
                            String url = object.getString("url");
                            String suffix = FileUtils.getFileSuffix(url);
                            String dir = baseDir + "\\证件照\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            FileUtils.downloadNetworkFile(url, outPath);
                        }
                    }
                    if (!CommonUtil.isNullStr(fileUrl)) {
                        String suffix = FileUtils.getFileSuffix(fileUrl);
                        String outPath = baseDir + "\\PDF盖章文件" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(fileUrl, outPath);
                    }
                    if (!CommonUtil.isNullStr(adviceFileUrl)) {
                        String suffix = FileUtils.getFileSuffix(adviceFileUrl);
                        String outPath = baseDir + "\\评审意见文件" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(adviceFileUrl, outPath);
                    }
                    if (userPic != null && !userPic.isEmpty()) {
                        for (Object o : userPic) {
                            JSONObject object = (JSONObject) o;
                            String name = object.getString("name");
                            name = FileUtils.validateFileName(name);
                            String url = object.getString("url");
                            String suffix = FileUtils.getFileSuffix(url);
                            String dir = baseDir + "\\主申报人标准证件照照片\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            FileUtils.downloadNetworkFile(url, outPath);
                        }
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl);
                        String outPath = baseDir + "\\附件材料压缩包" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl1)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl1);
                        String outPath = baseDir + "\\附件材料1" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl1, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl2)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl2);
                        String outPath = baseDir + "\\附件材料2" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl2, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl3)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl3);
                        String outPath = baseDir + "\\附件材料3" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl3, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl4)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl4);
                        String outPath = baseDir + "\\附件材料4" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl4, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl5)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl5);
                        String outPath = baseDir + "\\附件材料5" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl5, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl6)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl6);
                        String outPath = baseDir + "\\附件材料6" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl6, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl7)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl7);
                        String outPath = baseDir + "\\附件材料7" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl7, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl8)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl8);
                        String outPath = baseDir + "\\附件材料8" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl8, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl9)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl9);
                        String outPath = baseDir + "\\附件材料9" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl9, outPath);
                    }
                    if (!CommonUtil.isNullStr(otherFileUrl10)) {
                        String suffix = FileUtils.getFileSuffix(otherFileUrl10);
                        String outPath = baseDir + "\\附件材料10" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(otherFileUrl10, outPath);
                    }
                    if (!CommonUtil.isNullStr(invoatePic) && !"[]".equals(invoatePic)) {
                        if (invoatePic.contains("[{")) {
                            JSONArray invoatePicArr = JSONArray.parseArray(invoatePic);
                            for (Object o : invoatePicArr) {
                                JSONObject object = (JSONObject) o;
                                String name = object.getString("name");
                                name = FileUtils.validateFileName(name);
                                String url = object.getString("url");
                                String suffix = FileUtils.getFileSuffix(url);
                                String dir = baseDir + "\\证明照片\\";
                                String outPath = dir + name + baseName + "." + suffix;
                                FileUtils.downloadNetworkFile(url, outPath);
                            }
                        } else {
                            String suffix = FileUtils.getFileSuffix(invoatePic);
                            String outPath = baseDir + "\\证明照片" + baseName + "." + suffix;
                            FileUtils.downloadNetworkFile(invoatePic, outPath);
                        }
                    }
                    if (!CommonUtil.isNullStr(reviewCommentUrl)) {
                        String suffix = FileUtils.getFileSuffix(reviewCommentUrl);
                        String outPath = baseDir + "\\盖章的推荐单位评审意见文件" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(reviewCommentUrl, outPath);
                    }
                    /////////////////////////////////
                    if (FileUtils.isExist(baseDir)) {
                        FileUtils.renameTo(baseDir, baseDir + "【完毕】");
                        PrintUtils.println("处理完毕");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    FileUtils.deleteFiles(baseDir);
                }
            });
        }
        threadPool.shutdown();
        while (true) {
            if (threadPool.isTerminated()) {
                break;
            }
        }
    }

    /**
     * 附件：annexes，盖章的申请书：stampApplyBookUrl，伦理批件：ethicalApproval，形式审查明细表：reviewDetailFileUrl
     *
     * @return void
     * @author wengym
     * @date 2024/1/15 14:36
     */
    @Test
    public void handleContributeResearchTopic() throws Exception {
        String path = "C:\\Users\\Administrator\\Desktop\\contribute_research_topic.xlsx";
        JSONObject jsonObject = EasyExcelUtils.readFile(path);
        JSONArray data = jsonObject.getJSONArray("data");
        ExecutorService threadPool = ThreadUtils.createThreadPool();
        for (Object m : data) {
            threadPool.execute(() -> {
                LinkedHashMap<Integer, String> single = (LinkedHashMap) m;
                String userName = single.get(22);
                String gender = single.get(23);
                String cellPhone = single.get(28);
                String topicName = single.get(39);
                topicName = topicName.replace("/", "-");
                topicName = topicName.replace("+", "-");
                topicName = topicName.replace(" ", "");
                JSONArray annexes = JSONArray.parseArray(single.get(65));
                String stampApplyBookUrl = single.get(66);
                String ethicalApproval = single.get(69);
                String reviewDetailFileUrl = single.get(70);
                PrintUtils.println(annexes + "-" + stampApplyBookUrl + "-" + ethicalApproval + "-" + reviewDetailFileUrl);
                String baseName = "-" + userName + "-" + topicName;
                String baseDir = "C:\\Users\\Administrator\\Desktop\\科研课题\\" + userName + "-" + cellPhone + "-" + gender + "-" + topicName + "";
                if (FileUtils.isExist(baseDir + "【完毕】")) {
                    return;
                }
                if (userName.equals("张志霞")) {
                    PrintUtils.println(userName);
                }
                FileUtils.deleteFiles(baseDir);
                try {
                    if (annexes != null && !annexes.isEmpty()) {
                        for (Object o : annexes) {
                            JSONObject object = (JSONObject) o;
                            String name = object.getString("name");
                            name = name.replace(".", "-");
                            name = name.replace(" ", "");
                            name = name.replace(",", "-");
                            name = name.replace("'", "-");
                            name = name.replace(":", "-");
                            String url = object.getString("url");
                            String suffix = FileUtils.getFileSuffix(url);
                            String dir = baseDir + "\\附件\\";
                            String outPath = dir + name + baseName + "." + suffix;
                            FileUtils.downloadNetworkFile(url, outPath);
                        }
                    }
                    if (!CommonUtil.isNullStr(stampApplyBookUrl)) {
                        String suffix = FileUtils.getFileSuffix(stampApplyBookUrl);
                        String outPath = baseDir + "\\盖章的申请书" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(stampApplyBookUrl, outPath);
                    }
                    if (!CommonUtil.isNullStr(ethicalApproval)) {
                        String suffix = FileUtils.getFileSuffix(ethicalApproval);
                        String outPath = baseDir + "\\伦理批件" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(ethicalApproval, outPath);
                    }
                    if (!CommonUtil.isNullStr(reviewDetailFileUrl)) {
                        String suffix = FileUtils.getFileSuffix(reviewDetailFileUrl);
                        String outPath = baseDir + "\\形式审查明细表" + baseName + "." + suffix;
                        FileUtils.downloadNetworkFile(reviewDetailFileUrl, outPath);
                    }
                    if (FileUtils.isExist(baseDir)) {
                        FileUtils.renameTo(baseDir, baseDir + "【完毕】");
                        PrintUtils.println("处理完毕");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    FileUtils.deleteFiles(baseDir);
                }
            });
        }
        threadPool.shutdown();
        while (true) {
            if (threadPool.isTerminated()) {
                break;
            }
        }
    }

    @Test
    public void handleMeetSignData() throws Exception {
        String path = "C:\\Users\\Administrator\\Desktop\\data1.json";
        String data = FileUtils.readFrom(path);
        PrintUtils.println(data);
        JSONArray jsonArray = JSONObject.parseObject(data).getJSONArray("RECORDS");
        List<MeetSignInModel> list = jsonArray.toJavaList(MeetSignInModel.class);
        int index = 1;
        for (MeetSignInModel m : list) {
            m.setIndex(index + "");
            m.setAge(CommonUtil.isNullStr(m.getIdCard()) ? "无" : DateUtils.getAge(IdCardUtils.getBirthday(m.getIdCard())));
            ImageUtils.base64StrToImage(m.getSignInImg().substring(m.getSignInImg().indexOf(",") + 1, m.getSignInImg().length()), "src/sign" + index + ".png");
            BufferedImage bufferedImage = ImageIO.read(new File("src/sign" + index + ".png"));
            bufferedImage = ImageUtils.rotateImage90(bufferedImage);
            bufferedImage = ImageUtils.zoomImage(bufferedImage, 0.3f, 0.3f);
            ImageIO.write(bufferedImage, "png", new File("src/sign" + index + ".png"));
            m.setSignImg(new File("src/sign" + index + ".png"));
            index++;
        }
        EasyExcelUtils.exportFile(list, "C:\\Users\\Administrator\\Desktop\\北京护理学会成立100周年暨第十二届分支机构换届大会.xlsx");
    }

    @Test
    public void handleMemberData() {
        // 1968
        String data12 = "C:\\Users\\Administrator\\Desktop\\cna_member1 - 副本.xlsx";
        List<CnaMemberModel> list12 = EasyExcelUtils.readFile(data12, CnaMemberModel.class);
        Integer nullNum = 0;
        Integer MNum = 0;
        Integer SNum = 0;
        Integer ANum = 0;
        Integer mNum = 0;
        Integer sNum = 0;
        Integer aNum = 0;
        Integer emNum = 0;
        Integer esNum = 0;
        Integer eaNum = 0;
        List<String> idCard = new ArrayList<>();
        for (CnaMemberModel model : list12) {
            if (CommonUtil.isNullStr(model.getIdCard())) {
                nullNum++;
                continue;
            }
            if (model.getIdCard().length() != 18 || model.getIdCard().contains("+")) {
                idCard.add(model.getIdCard());
                continue;
            }
            Integer year = Integer.valueOf(model.getIdCard().substring(6, 10));
            if (year.equals(1968)) {
                if (model.getMemberType().equals("M")) {
                    emNum++;
                }
                if (model.getMemberType().equals("S")) {
                    esNum++;
                }
                if (model.getMemberType().equals("A")) {
                    eaNum++;
                }
            } else if (year > 1968) {
                if (model.getMemberType().equals("M")) {
                    mNum++;
                }
                if (model.getMemberType().equals("S")) {
                    sNum++;
                }
                if (model.getMemberType().equals("A")) {
                    aNum++;
                }
            } else if (year < 1968) {
                if (model.getMemberType().equals("M")) {
                    MNum++;
                }
                if (model.getMemberType().equals("S")) {
                    SNum++;
                }
                if (model.getMemberType().equals("A")) {
                    ANum++;
                }
            }
        }
        return;
    }

    @Test
    public void handleWordData() {
        String data12 = "C:\\Users\\Administrator\\Desktop\\查询工作委员会数据1.xlsx";
        List<UserInfoModel> list12 = EasyExcelUtils.readFile(data12, UserInfoModel.class);
        Set<String> committeeSet12 = new HashSet<>();
        // 十二届的专委会单位
        Map<String, Set<String>> hasWorkUnitMap12 = new HashMap<>();
        for (UserInfoModel model : list12) {
            UserInfoModel userInfoModel = model;
            if (StringUtils.equals(userInfoModel.getGender(), "1")) {
                userInfoModel.setGender("男");
            } else {
                userInfoModel.setGender("女");
            }
            userInfoModel.setPolitical(CommonUtil.politicArray[Integer.parseInt(userInfoModel.getPolitical()) - 1]);
            userInfoModel.setNation(CommonUtil.nationArray[Integer.parseInt(userInfoModel.getNation()) - 1]);
            userInfoModel.setEducation(CommonUtil.educationArray[Integer.parseInt(userInfoModel.getEducation()) - 1]);
            userInfoModel.setIsDeputy("1".equals(userInfoModel.getIsDeputy()) ? "是" : "否");
            userInfoModel.setIsLeadingCadre("1".equals(userInfoModel.getIsLeadingCadre()) ? "是" : "否");
            userInfoModel.setIsReElection("1".equals(userInfoModel.getIsReElection()) ? "是" : "否");
            // 处理原学会职务
            JSONObject pnaPostJSONObject = JSONObject.parseObject(userInfoModel.getPnaPost());
            userInfoModel.setPnaPost("无");
            if (pnaPostJSONObject.getBoolean("isHoldPreviousPost") && pnaPostJSONObject.getBoolean("isHoldPreviousPost11")) {
                JSONArray pnaPostData11 = JSONArray.parseArray(pnaPostJSONObject.getString("pnaPostData11"));
                for (int i = 0; i < pnaPostData11.size(); i++) {
                    if (userInfoModel.getDesignatedSpecialCommittee().equals(pnaPostData11.getJSONObject(i).getString("committee"))
                            && userInfoModel.getDesignatedPosition().equals(pnaPostData11.getJSONObject(i).getString("position"))) {
                        userInfoModel.setPnaPost(pnaPostData11.getJSONObject(i).getString("position"));
                        break;
                    }
                }
            }
            // 十二届数据处理
            committeeSet12.add(model.getDesignatedSpecialCommittee());
            // 添加各专委会拥有的工作单位
            if (hasWorkUnitMap12.containsKey(model.getDesignatedSpecialCommittee())) {
                hasWorkUnitMap12.get(model.getDesignatedSpecialCommittee()).add(model.getDataFrom().trim());
            } else {
                hasWorkUnitMap12.put(model.getDesignatedSpecialCommittee(), DatabaseUtils.asSet(model.getDataFrom().trim()));
            }
        }
        // 根据专委会排序
        Collections.sort(list12, new Comparator<UserInfoModel>() {
            @Override
            public int compare(UserInfoModel o1, UserInfoModel o2) {
                return o1.getDesignatedSpecialCommittee().compareTo(o2.getDesignatedSpecialCommittee());
            }
        });
        EasyExcelUtils.exportFile(BeanUtils.modelListToDTOList(list12, UserInfoDTO.class), "C:\\Users\\Administrator\\Desktop\\123111.xlsx");
        PrintUtils.println(committeeSet12);
    }

    @Test
    public void handleProData() {
        String data12 = "C:\\Users\\Administrator\\Desktop\\导出专委会数据（通过医院审核的）1.xlsx";
        String data11 = "C:\\Users\\Administrator\\Desktop\\第十一届各委员会委员名录.xlsx";
        List<UserInfoModel> list12 = EasyExcelUtils.readFile(data12, UserInfoModel.class);
        List<UserInfoModel> list11 = EasyExcelUtils.readFile(data11, UserInfoModel.class);
        Set<String> committeeSet12 = new HashSet<>();
        Set<String> committeeSet11 = new HashSet<>();
        // 十二届的专委会单位
        Map<String, Set<String>> hasWorkUnitMap12 = new HashMap<>();
        // 十一届的专委会单位
        Map<String, Set<String>> hasWorkUnitMap11 = new HashMap<>();
        for (UserInfoModel model : list12) {
            UserInfoModel userInfoModel = model;
            if (StringUtils.equals(userInfoModel.getGender(), "1")) {
                userInfoModel.setGender("男");
            } else {
                userInfoModel.setGender("女");
            }
            userInfoModel.setPolitical(CommonUtil.politicArray[Integer.parseInt(userInfoModel.getPolitical()) - 1]);
            userInfoModel.setNation(CommonUtil.nationArray[Integer.parseInt(userInfoModel.getNation()) - 1]);
            userInfoModel.setEducation(CommonUtil.educationArray[Integer.parseInt(userInfoModel.getEducation()) - 1]);
            userInfoModel.setIsDeputy("1".equals(userInfoModel.getIsDeputy()) ? "是" : "否");
            userInfoModel.setIsLeadingCadre("1".equals(userInfoModel.getIsLeadingCadre()) ? "是" : "否");
            userInfoModel.setIsReElection("1".equals(userInfoModel.getIsReElection()) ? "是" : "否");
            // 处理原学会职务
            JSONObject pnaPostJSONObject = JSONObject.parseObject(userInfoModel.getPnaPost());
            userInfoModel.setPnaPost("无");
            if (pnaPostJSONObject.getBoolean("isHoldPreviousPost") && pnaPostJSONObject.getBoolean("isHoldPreviousPost11")) {
                JSONArray pnaPostData11 = JSONArray.parseArray(pnaPostJSONObject.getString("pnaPostData11"));
                for (int i = 0; i < pnaPostData11.size(); i++) {
                    if (userInfoModel.getDesignatedSpecialCommittee().equals(pnaPostData11.getJSONObject(i).getString("committee"))
                            && userInfoModel.getDesignatedPosition().equals(pnaPostData11.getJSONObject(i).getString("position"))) {
                        userInfoModel.setPnaPost(pnaPostData11.getJSONObject(i).getString("position"));
                        break;
                    }
                }
            }
            // 十二届数据处理
            committeeSet12.add(model.getDesignatedSpecialCommittee());
            // 添加各专委会拥有的工作单位
            if (hasWorkUnitMap12.containsKey(model.getDesignatedSpecialCommittee())) {
                hasWorkUnitMap12.get(model.getDesignatedSpecialCommittee()).add(model.getDataFrom().trim());
            } else {
                hasWorkUnitMap12.put(model.getDesignatedSpecialCommittee(), DatabaseUtils.asSet(model.getDataFrom().trim()));
            }
        }
        for (UserInfoModel model : list11) {
            // 十一届数据处理
            committeeSet11.add(model.getDesignatedSpecialCommittee());
            // 添加各专委会拥有的工作单位
            if (hasWorkUnitMap11.containsKey(model.getDesignatedSpecialCommittee())) {
                hasWorkUnitMap11.get(model.getDesignatedSpecialCommittee()).add(model.getWorkUnit().trim());
            } else {
                hasWorkUnitMap11.put(model.getDesignatedSpecialCommittee(), DatabaseUtils.asSet(model.getWorkUnit().trim()));
            }
        }
        // 十一届中拥有的单位
        Map<String, Set<String>> existWorkUnitMap11 = new HashMap<>();
        // 十一届中没有单位
        Map<String, Set<String>> noExistWorkUnitMap11 = new HashMap<>();
        for (String committee : committeeSet12) {
            Set<String> workUnitSet12 = hasWorkUnitMap12.get(committee);
            Set<String> workUnitSet11 = hasWorkUnitMap11.get(committee);
            for (String str : workUnitSet12) {
                // 遍历十二届某专委会的单位集合，如果在十一届某专委会的单位集合中没有，则存到noExistWorkUnitMap11，有则存在existWorkUnitMap11
                if (workUnitSet11 == null || !workUnitSet11.contains(str)) {
                    if (CommonUtil.isNullStr(noExistWorkUnitMap11.get(committee))) {
                        noExistWorkUnitMap11.put(committee, DatabaseUtils.asSet(str));
                    } else {
                        noExistWorkUnitMap11.get(committee).add(str);
                    }
                } else {
                    if (CommonUtil.isNullStr(existWorkUnitMap11.get(committee))) {
                        existWorkUnitMap11.put(committee, DatabaseUtils.asSet(str));
                    } else {
                        existWorkUnitMap11.get(committee).add(str);
                    }
                }
            }
        }
        // 拼接同一个人的第十一届内容
        for (UserInfoModel model : list12) {
            // 基于手机号查询同一个人
            List<UserInfoModel> filterList = list11.stream().filter(item -> item.getTel().equals(model.getTel())).collect(Collectors.toList());
            if (filterList != null && !filterList.isEmpty()) {
                model.setName11(filterList.get(0).getName());
                model.setDesignatedPosition11(filterList.get(0).getDesignatedPosition());
                model.setGender11(filterList.get(0).getGender());
                model.setBirth11(filterList.get(0).getBirth());
                model.setNation11(filterList.get(0).getNation());
                model.setPolitical11(filterList.get(0).getPolitical());
                model.setWorkUnit11(filterList.get(0).getWorkUnit());
                model.setDepartment11(filterList.get(0).getDepartment());
                model.setWorkPost11(filterList.get(0).getWorkPost());
                model.setPositionTitle11(filterList.get(0).getPositionTitle());
                model.setEducation11(filterList.get(0).getEducation());
                model.setOfficeTel11(filterList.get(0).getOfficeTel());
                model.setTel11(filterList.get(0).getTel());
                model.setEmail11(filterList.get(0).getEmail());
                model.setDesignatedSpecialCommittee11(filterList.get(0).getDesignatedSpecialCommittee());
            }
        }
        // 根据专委会排序
        Collections.sort(list12, new Comparator<UserInfoModel>() {
            @Override
            public int compare(UserInfoModel o1, UserInfoModel o2) {
                return o1.getDesignatedSpecialCommittee().compareTo(o2.getDesignatedSpecialCommittee());
            }
        });
        List<UserInfoModel> newList = new ArrayList<>();
        String existWorkUnits1 = "";
        if (existWorkUnitMap11.containsKey(list12.get(0).getDesignatedSpecialCommittee())) {
            existWorkUnits1 = StringUtils.join(existWorkUnitMap11.get(list12.get(0).getDesignatedSpecialCommittee()), "，");
        }
        UserInfoModel existNewModel1 = UserInfoModel.builder()
                .name(list12.get(0).getDesignatedSpecialCommittee())
                .gender("第十一届专委会中存在的工作单位")
                .nation(existWorkUnits1)
                .build();
        String noExistWorkUnits1 = "";
        if (noExistWorkUnitMap11.containsKey(list12.get(0).getDesignatedSpecialCommittee())) {
            noExistWorkUnits1 = StringUtils.join(noExistWorkUnitMap11.get(list12.get(0).getDesignatedSpecialCommittee()), "，");
        }
        UserInfoModel noExistNewModel1 = UserInfoModel.builder()
                .name(list12.get(0).getDesignatedSpecialCommittee())
                .gender("第十一届专委会中不存在的工作单位")
                .nation(noExistWorkUnits1)
                .build();
        newList.add(existNewModel1);
        newList.add(noExistNewModel1);
        for (int i = 0; i < list12.size(); i++) {
            newList.add(list12.get(i));
            if (i > 0
                    && i < list12.size() - 1
                    && !list12.get(i).getDesignatedSpecialCommittee().equals(list12.get(i + 1).getDesignatedSpecialCommittee())) {
                String newCommittee = list12.get(i + 1).getDesignatedSpecialCommittee();
                String existWorkUnits = "";
                if (existWorkUnitMap11.containsKey(newCommittee)) {
                    existWorkUnits = StringUtils.join(existWorkUnitMap11.get(newCommittee), "，");
                }
                UserInfoModel existNewModel = UserInfoModel.builder()
                        .name(newCommittee)
                        .gender("第十一届专委会中存在的工作单位")
                        .nation(existWorkUnits)
                        .build();
                String noExistWorkUnits = "";
                if (noExistWorkUnitMap11.containsKey(newCommittee)) {
                    noExistWorkUnits = StringUtils.join(noExistWorkUnitMap11.get(newCommittee), "，");
                }
                UserInfoModel noExistNewModel = UserInfoModel.builder()
                        .name(newCommittee)
                        .gender("第十一届专委会中不存在的工作单位")
                        .nation(noExistWorkUnits)
                        .build();
                newList.add(existNewModel);
                newList.add(noExistNewModel);
            }
        }
        EasyExcelUtils.exportFile(BeanUtils.modelListToDTOList(newList, UserInfoDTO.class), "C:\\Users\\Administrator\\Desktop\\123.xlsx");
        PrintUtils.println(committeeSet12);
        PrintUtils.println(committeeSet11);
    }

    @Test
    public void testExportMultiSheetFile() {
        // 列表头
        List<List<List<String>>> headLists = new ArrayList<>();
        List<List<String>> headList1 = new ArrayList<List<String>>() {{
            add(new ArrayList<String>() {{
                add("列一");
            }});
            add(new ArrayList<String>() {{
                add("列二");
            }});
        }};
        List<List<String>> headList2 = new ArrayList<List<String>>() {{
            add(new ArrayList<String>() {{
                add("列一点一");
            }});
            add(new ArrayList<String>() {{
                add("列二点二");
            }});
        }};
        headLists.add(headList1);
        headLists.add(headList2);
        // 列表数据
        List<List<List<String>>> dataLists = new ArrayList<>();
        List<List<String>> dataList1 = new ArrayList<>();
        List<String> data1 = new ArrayList<String>() {{
            add("1");
            add("1");
        }};
        List<String> data11 = new ArrayList<String>() {{
            add("11");
            add("11");
        }};
        dataList1.add(data1);
        dataList1.add(data11);
        List<List<String>> dataList2 = new ArrayList<>();
        List<String> data2 = new ArrayList<String>() {{
            add("2");
            add("2");
        }};
        List<String> data22 = new ArrayList<String>() {{
            add("22");
            add("2211111111111111111111111114444444444444444444444111111111111111111");
        }};
        dataList2.add(data2);
        dataList2.add(data22);
        dataLists.add(dataList1);
        dataLists.add(dataList2);
        String outFilePath = "D:\\template\\easyexcel\\multiSheetFile.xlsx";
        EasyExcelUtils.exportMultiSheetFile(dataLists, headLists, outFilePath);
    }

    @Test
    public void testMultipleList() {
        String templateFilePath = "D:\\template\\easyexcel\\病例样例_v1.xlsx";
        String outFilePath = "D:\\template\\easyexcel\\病例样例_v1_Out.xlsx";
        Map<String, Object> dataMap = EasyExcelUtils.getDataMap();
        EasyExcelUtils.exportFileFromTemplate(dataMap, templateFilePath, outFilePath);
    }

    @Test
    public void testExportFileFromTemplate() {
        String templateFilePath = "D:\\template\\easyexcel\\productTemplate.xlsx";
        String outFilePath = "D:\\template\\easyexcel\\productOut.xlsx";
        List<TeacherNoSettingExportVo> dataList = EasyExcelUtils.getDataList();
        EasyExcelUtils.exportFileFromTemplate(dataList, templateFilePath, outFilePath);
        String templateFilePath2 = "D:\\template\\easyexcel\\productTemplate2.xlsx";
        String outFilePath2 = "D:\\template\\easyexcel\\productOut2.xlsx";
        Map<String, Object> dataMap = EasyExcelUtils.getDataMap();
        EasyExcelUtils.exportFileFromTemplate(dataMap, templateFilePath2, outFilePath2);
    }
}
