let arr = [{"code":"guizhou","name":"贵州"},{"code":"fujian","name":"福建"},{"code":"beijing","name":"北京"},{"code":"gansu","name":"甘肃"},{"code":"zhejiang","name":"浙江"},{"code":"yunnan","name":"云南"},{"code":"hainan","name":"海南"},{"code":"sichuan","name":"四川"},{"code":"ningxia","name":"宁夏"},{"code":"jilin","name":"吉林"},{"code":"liaoning","name":"辽宁"},{"code":"qinghai","name":"青海"},{"code":"shaanxi","name":"陕西"},{"code":"jiangsu","name":"江苏"},{"code":"shanxi","name":"山西"},{"code":"chongqing","name":"重庆"},{"code":"jiangxi","name":"江西"},{"code":"shandong","name":"山东"},{"code":"guangdong","name":"广东"},{"code":"hunan","name":"湖南"},{"code":"anhui","name":"安徽"},{"code":"qingdao","name":"青岛"},{"code":"xiamen","name":"厦门"},{"code":"dalian","name":"大连"},{"code":"ningbo","name":"宁波"},{"code":"shenzhen","name":"深圳"},{"code":"neimeng","name":"内蒙"},{"code":"shanghai","name":"上海"},{"code":"xinjiang","name":"新疆"},{"code":"xizang","name":"西藏"},{"code":"henan","name":"河南"},{"code":"guangxi","name":"广西"},{"code":"hebei","name":"河北"},{"code":"heilongjiang","name":"黑龙江"},{"code":"hubei","name":"湖北"},{"code":"tianjin","name":"天津"}];
let str = "";
for (let item of arr) {
    str += "put(\"" + item.code + "\", \"" + item.name + "\");"
}
console.log(str);