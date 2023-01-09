//import areaData from "@common/area-data";
//import educationData from "@common/education-data";
//import nationData from "@common/nation-data";

areaData = require('../localData/area-data');
educationData = require('../localData/education-data');
nationData = require('../localData/nation-data');
genderData = require('../localData/gender-data');

/***
 *
 * @param idCard
 *
 * @param type
 *
 * @desc 隐藏身份证中间部分的内容，只显示首四位和尾四位
 *
 * @author wengym
 *
 * @date 2022/3/8 10:11
 *
 * @return
 *
 */
function getMaskOfIdCard(idCard, type) {
    if (!type) {
        type = '*';
    }
    let middle = '';
    for (let i = 1; i <= 4; i++ ) {
        middle += type;
    }
    return idCard.substring(0, 4) + middle + idCard.substring(idCard.length - 4);
}

/***
 * 
 * 根据身份证获取性别、出生年月日、年龄
 *
 * @param 
 *
 * @author wengym
 *
 * @date 2022/3/24 15:51 
 *
 * @return 
 *
 */
function getAnalysisIdCard(card, type) {
    if (type === 'INFO_BIRTH') {
        // 获取出生日期
        let birth = card.substring(6, 10) + '-' + card.substring(10, 12) + '-' + card.substring(12, 14);
        return birth;
    }
    if (type === 'INFO_SEX') {
        // 获取性别
        if (parseInt(card.substr(16, 1)) % 2 == 1) {
            // 男
            return '男';
        }
        // 女
        return '女';
    }
    if (type === 'INFO_AGE') {
        // 获取年龄
        const myDate = new Date();
        const month = myDate.getMonth() + 1;
        const day = myDate.getDate();
        let age = myDate.getFullYear() - card.substring(6, 10);
        if (card.substring(10, 12) < month || card.substring(10, 12) == month && card.substring(12, 14) <= day) {
            age++;
        }
        return age;
    }
}

/***
 *
 * 判断str是否为undefined、null、空串
 *
 * @param str
 *
 * @author wengym
 *
 * @date 2022/5/6 15:17
 *
 * @return
 *
 */
function isNullStr(str) {
    if (str === undefined || str === null || str === '') {
        return true;
    }
    return false;
}

/**
 * 基于值在对象数组中获取标签
 *
 * @param value
 *
 * @param options
 *
 * @author wengym
 *
 * @date 2022/7/14 13:52
 *
 * @return
 */
function getLabelByValue(value, options) {
    let result = options.find(item => item.value == value);
    if (result) {
        return result.label;
    }
    return value;
}

/**
 * 根据值获取学历标签
 *
 * @param value
 *
 * @author wengym
 *
 * @date 2022/7/14 13:53
 *
 * @return
 */
function getEducationLabelByValue(value) {
    return getLabelByValue(value, educationData);
}

/**
 * 根据值获取民族标签
 *
 * @param value
 *
 * @author wengym
 *
 * @date 2022/7/14 13:53
 *
 * @return
 */
function getNationLabelByValue(value) {
    return getLabelByValue(value, nationData);
}

function getNationJsonOfValueToLabel() {
    let nationObject = {};
    for (let object of nationData) {
        nationObject[object.value] = object.label;
    }
    return JSON.stringify(nationObject);
}

function getNationJsonOfLabelToValue() {
    let nationObject = {};
    for (let object of nationData) {
        nationObject[object.label] = object.value;
    }
    return JSON.stringify(nationObject);
}

function getGenderJsonOfValueToLabel() {
    let genderObject = {};
    for (let object of genderData) {
        genderObject[object.value] = object.label;
    }
    return JSON.stringify(genderObject);
}

function getEducationJsonOfValueToLabel() {
    let educationObject = {};
    for (let object of educationData) {
        educationObject[object.value] = object.label;
    }
    return JSON.stringify(educationObject);
}

/**
 * 根据值获取区域标签
 *
 * @param value
 *
 * @author wengym
 *
 * @date 2022/7/14 13:54
 *
 * @return
 */
function getAreaLabelByValue(value) {
    if (areaData[value]) {
        return areaData[value][0];
    }
    return "";
}

/**
 * 根据区域标签获取值
 *
 * @param label
 *
 * @author wengym
 *
 * @date 2022/7/14 13:54
 *
 * @return
 */
function getAreaValueByLabel(label) {
    for (let key in areaData) {
        if (areaData[key][0] === label) {
            return key;
        }
    }
    return "";
}

const object = {
    getMaskOfIdCard: getMaskOfIdCard,
    getAnalysisIdCard: getAnalysisIdCard,
    isNullStr: isNullStr
}

//export default object
let idCard = "44098219931009277X";
console.log("生日", getAnalysisIdCard(idCard, 'INFO_BIRTH'));
console.log("性别", getAnalysisIdCard(idCard, 'INFO_SEX'));
console.log("年龄", getAnalysisIdCard(idCard, 'INFO_AGE'));
console.log("判空", isNullStr(undefined));
console.log("判空", isNullStr(null));
console.log("判空", isNullStr(''));
console.log("判空", isNullStr(123));
console.log("中国时间", new Date(new Date().getTime()+(parseInt(new Date().getTimezoneOffset()/60) + 8)*3600*1000).getTime());
console.log("民族JSON", getNationJsonOfLabelToValue());
console.log("民族JSON", getNationJsonOfValueToLabel());
console.log("性别JSON", getGenderJsonOfValueToLabel());
console.log("教育JSON", getEducationJsonOfValueToLabel());
