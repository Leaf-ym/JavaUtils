// 对象的属性名称，可用引号括起来，也可不用
let data = {"text": "广东护理学会", value: "GD001", cost: 1000};
Object.defineProperty(data, "age", {value:"forever 18", enumerable: false});
Object.prototype.protoPer1 = function() {console.log("proto");};
Object.prototype.protoPer2 = 2;
// 对象属性的遍历，不能用for of
// for in可用于遍历对象的可枚举属性，包括自有属性、继承自原型的属性（不包括Symbol属性）
for (let index in data) {
    console.log(index);
}
