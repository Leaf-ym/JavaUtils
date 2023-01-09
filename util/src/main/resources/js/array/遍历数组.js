let data = [1,2,3,"4","5",{a:1,b:2,c:3}];

console.log("用in遍历，item是元素在数组中的索引，从0开始")
for (let item in data) {
    console.log(item);
}
console.log("用of遍历，item是元素，从第一个元素开始")
for (let item of data) {
    console.log(item);
}