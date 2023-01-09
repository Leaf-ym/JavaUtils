areaData = require('./area-data');
educationData = require('./education-data');
nationData = require('./nation-data');

function getAllProvinceByLabelValue() {
    let provinceObject = {};
    for (let key in areaData) {
        if (areaData[key][1] == '1') {
            provinceObject[areaData[key][0]] = key;
        }
    }
    return provinceObject;
}

function getAllProvinceByValueLabel() {
    let provinceObject = {};
    for (let key in areaData) {
        if (areaData[key][1] == '1') {
            provinceObject[key] = areaData[key][0];
        }
    }
    return provinceObject;
}

function getLabelByValue(value, options) {
    let result = options.find(item => item.value == value);
    if (result) {
        return result.label;
    }
    return value;
}

function getEducationLabelByValue(value) {
    return getLabelByValue(value, educationData);
}

function getNationLabelByValue(value) {
    return getLabelByValue(value, nationData);
}

function getAreaLabelByValue(value) {
    if (areaData[value]) {
        return areaData[value][0];
    }
    return "";
}

function getAreaValueByLabel(label) {
    for (let key in areaData) {
        if (areaData[key][0] === label) {
            return key;
        }
    }
    return "";
}

/**
 * 获取某个省份的所有城市数据
 *
 * @param province
 *
 * @author wengym
 *
 * @date 2022/7/13 16:41
 *
 * @return
 */
function getAllCityOfProvince(province, type) {
    let provinceCityObject = {};
    for (let key in areaData) {
        if(areaData[key][1] == province) {
            provinceCityObject[key] = areaData[key][0];
        }
    }
    if (type === 1) {
        let provinceCityArr = [];
        for (let key in provinceCityObject) {
            provinceCityArr.push({
                label:provinceCityObject[key],
                value:key
            });
        }
        return provinceCityArr;
    }
    return provinceCityObject;
}

/**
 * 获取城市下的所有区县
 *
 * @param cityCode
 *
 * @author wengym
 *
 * @date 2022/9/1 15:54
 *
 * @return
 */
function getTownObjectByCityCode(cityCode) {
    let townObject = {};
    for (let key in areaData) {
        if (areaData[key][1] === cityCode) {
            townObject[key] = areaData[key][0];
        }
    }
    return townObject;
}

/**
 * 获取城市下的所有区县
 *
 * @param cityCode
 *
 * @author wengym
 *
 * @date 2022/9/1 15:54
 *
 * @return
 */
function getTownListByCityCode(cityCode) {
    let townList = [];
    for (let key in areaData) {
        if (areaData[key][1] === cityCode) {
            townList.push(areaData[key][0]);
        }
    }
    return townList;
}

/*
console.log(getLabelByValue("330000"));
console.log(getValueByLabel("浙江省"));
console.log(getAllProvinceByLabelValue());
console.log(getAllProvinceByValueLabel());*/
//console.log(getAllCityOfProvince("330000", 1));
console.log(getEducationLabelByValue(6))
console.log(getNationLabelByValue(1))
console.log(getAreaLabelByValue("110000"))
console.log(getAreaValueByLabel("浙江省"))
// 提取某个城市的所有区县
console.log(getTownObjectByCityCode("110100"));
console.log(JSON.stringify(getTownListByCityCode("110100")));

