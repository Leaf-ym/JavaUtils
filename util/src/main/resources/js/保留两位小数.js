// https://www.jb51.net/article/134067.htm
// 四舍五入
let num = 2.446242342;
num = num.toFixed(2); // 输出为2.45
console.log("num",num);

// 不四舍五入
// 1.先乘以100，再向下取整，然后除以100
let num1 = Math.floor(15.7784514000 * 100) / 100; // 输出15.77
console.log("num1",num1);
// 2.把数字当作字符串，使用正则匹配
let num2 = Number(15.7784514000.toString().match(/^\d+(?:\.\d{0,2})?/)) // 输出结果为 15.77,不能用于整数如 10 必须写为10.0000
console.log("num2",num2);
