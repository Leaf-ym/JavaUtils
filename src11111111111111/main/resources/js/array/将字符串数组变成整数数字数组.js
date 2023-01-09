// 方法一，radix：进制
let arr1 = ['0', '1', '2']
console.log(arr1)
console.log(arr1.map(i => parseInt(i, 10)));

// 方法二
let arr2 = ['0', '1', '2']
console.log(arr2.map(i => i * 1));
//或者
console.log(arr2.map(i => i / 1));
