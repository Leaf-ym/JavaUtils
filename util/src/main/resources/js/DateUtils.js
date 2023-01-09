/* 时间格式 */
const FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
const FORMAT_YYYY_MM_DD_Z = "yyyy年MM月dd日";
const FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
const FORMAT_YYYY_MM_DD_HH_MI_SS_MIS = "yyyyMMddHHmmssSSS";
const FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";
const FORMAT_YYYYMMDD = "yyyyMMdd";
const FORMAT_YYYY_MM_DD_HM = "yyyy-MM-dd HH:mm";
const FORMAT_START = "yyyy/MM/dd HH:ss";

/***
 *
 * 时间格式处理
 * "yyyy-MM-dd"
 * "yyyy年MM月dd日"
 * "yyyy-MM-dd HH:mm:ss"
 * "yyyyMMddHHmmssSSS"
 * "yyyyMMddHHmmss"
 * "yyyyMMdd"
 * "yyyy-MM-dd HH:mm"
 * "yyyy/MM/dd HH:ss"
 *
 * dateFormat("yyyy-MM-dd HH:mm:ss", new Date("2022-04-13 10:48:30"))
 *
 * @param fmt
 *
 * @param date
 *
 * @author wengym
 *
 * @date 2022/4/13 10:48
 *
 * @return
 *
 */
function dateFormat(format, date) {
    if(!format || !date) {
        return '';
    }
    let ret;
    const opt = {
        "y+": date.getFullYear().toString(),        // 年
        "M+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "m+": date.getMinutes().toString(),         // 分
        "s+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(format);
        if (ret) {
            format = format.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        };
    };
    return format;
}

/***
 *
 * 获取当前时间，格式为format
 *
 * @param format
 *
 * @author wengym
 *
 * @date 2022/4/14 9:49
 *
 * @return
 *
 */
function getCurrentDatetime(format) {
    let date = new Date();
    let result = dateFormat(format, date);
    return result;
}

let date1 = new Date("2022-03-08 11:21:12");
console.log("format", dateFormat(FORMAT_YYYY_MM_DD, date1));
console.log("format", dateFormat(FORMAT_YYYY_MM_DD_Z, date1));
console.log("format", dateFormat("yyyy-MM-dd HH:mm:ss", new Date("2022-04-13 10:48:30")));
console.log("now", getCurrentDatetime(FORMAT_YYYY_MM_DD_HH_MI_SS));