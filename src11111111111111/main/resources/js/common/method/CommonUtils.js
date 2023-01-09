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
let idCard = '44098219931009277x';
console.log("123", getMaskOfIdCard(idCard, '*'));

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

const object = {
    getMaskOfIdCard: getMaskOfIdCard,
    getAnalysisIdCard: getAnalysisIdCard,
    isNullStr: isNullStr
}

//export default object

console.log("生日", getAnalysisIdCard(idCard, 'INFO_BIRTH'));
console.log("性别", getAnalysisIdCard(idCard, 'INFO_SEX'));
console.log("年龄", getAnalysisIdCard(idCard, 'INFO_AGE'));
console.log("判空", isNullStr(undefined));
console.log("判空", isNullStr(null));
console.log("判空", isNullStr(''));
console.log("判空", isNullStr(123));
