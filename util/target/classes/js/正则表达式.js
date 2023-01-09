/***
 * 正则表达式是描述字符模式的对象。正则表达式用于对字符串模式匹配及检索替换，是对字符串执行模式匹配的强大工具。
 * 语法：
 * let pattern = new RegExp(pattern, modifiers);
 * 或者更简单的方式
 * let pattern = /pattern/modifiers;
 * pattern（模式）描述了表达式的模式
 * modifiers（修饰符）用于指定全局匹配、区分大小写的匹配和多行匹配
 * 注意：当使用构造函数创造正则对象时，需要常规的字符转义规则（在前面加反斜杠 \）。比如，以下是等价的：
 * let re = new RegExp("\\w+");
 * let re = /\w+/;
 * 修饰符：修饰符用于执行区分大小写和全局匹配:
 * i：执行对大小写不敏感的匹配。
 * g：执行全局匹配（查找所有匹配而非在找到第一个匹配后停止）。
 * m：执行多行匹配。
 *
 * RegExp 对象方法
 * exec	检索字符串中指定的值。返回找到的值，并确定其位置。
 * test	检索字符串中指定的值。返回 true 或 false。
 * toString	返回正则表达式的字符串。
 *
 * 支持正则表达式的 String 对象的方法
 * search	检索与正则表达式相匹配的值。
 * match	找到一个或多个正则表达式的匹配。
 * replace	替换与正则表达式匹配的子串。
 * split	把字符串分割为字符串数组。
 *
 */

let str = "Visit Runoob!";
let n = str.search(/Runoob/i);
console.log("目标索引：",n)

// 邮政编码：6位数字
let zipCode = /^[0-9]{6}$/;
console.log(zipCode.test("123"));
console.log(zipCode.test("123456"));
// 护士执业证书编号：4位年份+2位编码+6位流水号
let nurseNumber = /^(1949|19[5-9]\d|20\d{2}|2100)\d{2}\d{6}$/;
console.log(nurseNumber.test("2021"));
console.log(nurseNumber.test("202164000001"));
// 手机号：1开头+1位非0非2的数字+9位数字，一共11位
let cellphone = /^1[3456789]\d{9}$/;
console.log(cellphone.test("1021888888"));
console.log(cellphone.test("17325302081"));
// 身份证号
let idCardReg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;
// 电子邮箱：1个以上的任意字符开头 + @ + 1个以上的任意字符开头 + 点. + 1个以上的任意字符开头
let email = /^.+@.+\..+/;
