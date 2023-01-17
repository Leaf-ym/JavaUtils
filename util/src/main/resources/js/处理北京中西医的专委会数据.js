let labelArr = ['妇产科专业委员会','耳鼻咽喉专业委员会','肝病专业委员会','皮肤性病专业委员会','心血管内科专业委员会','肿瘤专业委员会','周围血管专业委员会','基础理论专业委员会','急救医学专业委员会','神经内科专业委员会','大肠肛门病专业委员会','普外科专业委员会','消化内科专业委员会','眼科专业委员会','骨伤科专业委员会','老年病专业委员会','临床营养治疗专业委员会','烧伤专业委员会','肾脏病专业委员会','糖尿病专业委员会','药学专业委员会','超声医学专业委员会','传染病专业委员会','儿科专业委员会','呼吸内科专业委员会','精神卫生专业委员会','口腔科专业委员会','男科专业委员会','变态反应专业委员会','放射医学专业委员会','风湿病专业委员会','检验医学专业委员会','泌尿外科专业委员会','全科医学专业委员会','中西医结合护理专业委员会','核医学专业委员会','环境与健康专业委员会','脊柱微创专业委员会','康复医学业委员会','麻醉与镇痛专业委员会','脑心同治专业委员会','生殖内分泌专业委员会','生殖医学专业委员会','围产医学专业委员会','消化内镜学专业委员会','信息化专业委员会','血液学专业委员会','医学美容专业委员会','灾害医学专业委员会','卒中专业委员会','安宁疗护专业委员会','多动抽动症专业委员会','感染专业委员会','更年期专业委员会','宫廷正骨学术研究专业委员会','国际交流与技术合作专业委员会','科技成果转化专业委员会','六经气化专业委员会','内分泌专业委员会','中医适宜技术专业委员会','糖尿病足专业委员会','足踝医学专业委员会','肿瘤外科专业委员会','医养结合专业委员会','甲状腺病专业委员会','养生专业委员会','重症医学专业委员会','睡眠专业委员会','心理健康专业委员会','治未病专业委员会','肥胖与代谢病专业委员会','胸科专业委员会','腹膜透析专业委员会'];
let userIdArr = ['BC0001','BC0002','BC0003','BC0004','BC0005','BC0006','BC0007','BC0008','BC0009','BC0010','BC0011','BC0012','BC0013','BC0014','BC0015','BC0016','BC0017','BC0018','BC0019','BC0020','BC0021','BC0022','BC0023','BC0024','BC0025','BC0026','BC0027','BC0028','BC0029','BC0030','BC0031','BC0032','BC0033','BC0034','BC0035','BC0036','BC0037','BC0038','BC0039','BC0040','BC0041','BC0042','BC0043','BC0044','BC0045','BC0046','BC0047','BC0048','BC0049','BC0050','BC0051','BC0052','BC0053','BC0054','BC0055','BC0056','BC0057','BC0058','BC0059','BC0060','BC0061','BC0062','BC0063','BC0064','BC0065','BC0066','BC0067','BC0068','BC0069','BC0070','BC0071','BC0072'];
let arr = [];
for (let i = 0; i < labelArr.length; i++) {
    arr.push({
       label: labelArr[i],
       value: labelArr[i],
       userId: labelArr[i],
       url: ''
    });
}
console.log(JSON.stringify(arr));