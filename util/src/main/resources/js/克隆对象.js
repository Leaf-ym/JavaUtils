let data = {text: "广东护理学会", value: "GD001"};
let data1 = Object.assign({},data);
data1.text = "天津护理学会";
data1.value = "TJ002";
console.log(data);
console.log(data1);
console.log(data === data1);