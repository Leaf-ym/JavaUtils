package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncepu.model.CnaMemberModel;
import com.ncepu.model.TeacherNoSettingExportVo;
import com.ncepu.model.UserInfoDTO;
import com.ncepu.model.UserInfoModel;
import com.ncepu.util.BeanUtils;
import com.ncepu.util.CommonUtil;
import com.ncepu.util.DatabaseUtils.DatabaseUtils;
import com.ncepu.util.EasyExcelUtils.EasyExcelUtils;
import com.ncepu.util.PrintUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wengym
 * @version 1.0
 * @desc easyexcel工具测试类
 * @date 2022/10/21 14:03
 */
public class EasyExcelUtilsTest {

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
