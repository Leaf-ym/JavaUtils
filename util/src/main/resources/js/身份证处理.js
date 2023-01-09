// 让只显示首位的各四个字符，中间的用四个星号代替
let idCard='44098219931009277x';


console.log("用in遍历，item是元素在数组中的索引，从0开始")
for (let item in data) {
    console.log(item);
}
console.log("用of遍历，item是元素，从第一个元素开始")
for (let item of data) {
    console.log(item);
}