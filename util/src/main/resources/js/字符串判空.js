//判断字符是否为空的方法
function isEmpty(str){
    if (typeof str === "undefined" || str === null) {
        return true;
    }
    // 去掉字符串的首尾空格
    let result = str.replace(/(^\s+)|(\s+$)/g,"");
    console.log(result);
    if(result === ""){
        return true;
    }else{
        return false;
    }
}

let str = undefined; // undefined,null,"","   ","  2   1 "

console.log(isEmpty(str))