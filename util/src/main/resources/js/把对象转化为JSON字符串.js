// 把json字符串转化为对象
let object = {
    positionInfo: [
        {
            name: "time",
            label: "时间",
            type: "DatePicker",
            inputType: "daterange",
            valueFormat: "yyyy-MM-dd",
            placeholder: "请输入",
        },
        {
            name: "job",
            label: "职位",
            type: "Input",
            inputType: "text",
            placeholder: "请输入职位",
        },
        {
            name: "positionTitle",
            label: "职称",
            type: "Input",
            inputType: "text",
            placeholder: "请输入职称",
        },
    ],
    schedulingInfo: [
        {
            name: "year",
            label: "年份",
            type: "DatePicker",
            inputType: "year",
            valueFormat: "yyyy",
            placeholder: "请选择年份",
        },
        {
            name: "TotalWorkingHours",
            label: "总工作时长(h)",
            type: "InputNumber",
            inputType: "number",
            min: 0,
            placeholder: "请输入总工作时长",
        },
    ],
    spouseChildrenInfo: [
        {
            name: "relationship",
            label: "关系",
            type: "Input",
            inputType: "text",
            placeholder: "请输入关系",
        },
        {
            name: "userName",
            label: "姓名",
            type: "Input",
            inputType: "text",
            placeholder: "请输入姓名",
        },
        {
            name: "gender",
            label: "性别",
            type: "Select",
            options: [{label: "男", value: "男"}, {label: "女", value: "女"}],
            placeholder: "请选择性别",
        },
        {
            name: "idCard",
            label: "身份证号",
            type: "Input",
            inputType: "text",
            placeholder: "请输入身份证号",
        },
        {
            name: "nowAddress",
            label: "现居住于",
            type: "Input",
            inputType: "text",
            placeholder: "请输入现居住于",
        }
    ],
    meetTrain: [
        {
            name: "name",
            label: "会议名称",
            type: "Input",
            inputType: "text",
            placeholder: "请输入会议名称",
        },
        {
            name: "startDate",
            label: "培训开始时间",
            type: "DateTimePicker",
            inputType: "datetime",
            valueFormat: "yyyy-MM-dd HH:mm:ss",
            placeholder: "请选择培训开始时间",
        },
        {
            name: "endDate",
            label: "培训结束时间",
            type: "DateTimePicker",
            inputType: "datetime",
            valueFormat: "yyyy-MM-dd HH:mm:ss",
            placeholder: "请选择培训结束时间",
        },
        {
            name: "result",
            label: "培训结果",
            type: "Input",
            inputType: "text",
            placeholder: "请输入培训结果",
        }
    ],
    positionTrain: [
        {
            name: "name",
            label: "岗位名称",
            type: "Input",
            inputType: "text",
            placeholder: "请输入岗位名称",
        },
        {
            name: "startDate",
            label: "培训开始时间",
            type: "DateTimePicker",
            inputType: "datetime",
            valueFormat: "yyyy-MM-dd HH:mm:ss",
            placeholder: "请选择培训开始时间",
        },
        {
            name: "endDate",
            label: "培训结束时间",
            type: "DateTimePicker",
            inputType: "datetime",
            valueFormat: "yyyy-MM-dd HH:mm:ss",
            placeholder: "请选择培训结束时间",
        },
        {
            name: "result",
            label: "培训结果",
            type: "Input",
            inputType: "text",
            placeholder: "请输入培训结果",
        }
    ],
    basicSkillTrain: [
        {
            name: "name",
            label: "基础技能名称",
            type: "Input",
            inputType: "text",
            placeholder: "请输入基础技能名称",
        },
        {
            name: "startDate",
            label: "培训开始时间",
            type: "DateTimePicker",
            inputType: "datetime",
            valueFormat: "yyyy-MM-dd HH:mm:ss",
            placeholder: "请选择培训开始时间",
        },
        {
            name: "endDate",
            label: "培训结束时间",
            type: "DateTimePicker",
            inputType: "datetime",
            valueFormat: "yyyy-MM-dd HH:mm:ss",
            placeholder: "请选择培训结束时间",
        },
        {
            name: "result",
            label: "培训结果",
            type: "Input",
            inputType: "text",
            placeholder: "请输入培训结果",
        }
    ],
    academicMeet: [
        {
            name: "name",
            label: "学术会议名称",
            type: "Input",
            inputType: "text",
            placeholder: "请输入学术会议名称",
        },
        {
            name: "startDate",
            label: "培训开始时间",
            type: "DateTimePicker",
            inputType: "datetime",
            valueFormat: "yyyy-MM-dd HH:mm:ss",
            placeholder: "请选择培训开始时间",
        },
        {
            name: "endDate",
            label: "培训结束时间",
            type: "DateTimePicker",
            inputType: "datetime",
            valueFormat: "yyyy-MM-dd HH:mm:ss",
            placeholder: "请选择培训结束时间",
        },
        {
            name: "result",
            label: "培训结果",
            type: "Input",
            inputType: "text",
            placeholder: "请输入培训结果",
        }
    ],
    specialistNurse: [
        {
            name: "name",
            label: "专科名称",
            type: "Input",
            inputType: "text",
            placeholder: "请输入专科名称",
        },
        {
            name: "startDate",
            label: "培训开始时间",
            type: "DateTimePicker",
            inputType: "datetime",
            valueFormat: "yyyy-MM-dd HH:mm:ss",
            placeholder: "请选择培训开始时间",
        },
        {
            name: "endDate",
            label: "培训结束时间",
            type: "DateTimePicker",
            inputType: "datetime",
            valueFormat: "yyyy-MM-dd HH:mm:ss",
            placeholder: "请选择培训结束时间",
        },
        {
            name: "result",
            label: "培训结果",
            type: "Input",
            inputType: "text",
            placeholder: "请输入培训结果",
        }
    ],
    examineInfo: [
        {
            name: "year",
            label: "年份",
            type: "DatePicker",
            inputType: "year",
            valueFormat: "yyyy",
            placeholder: "请选择年份",
        },
        {
            name: "timeUnit",
            label: "时间单位",
            type: "Select",
            options: [{label: "季度", value: "季度"}, {label: "月度", value: "月度"}],
            placeholder: "请选择时间单位",
        },
        {
            name: "time",
            label: "时间",
            type: "InputNumber",
            inputType: "number",
            min: 1,
            max: 12,
            placeholder: "请输入时间（季度1-4，月度1-12）",
        },
        {
            name: "passCount",
            label: "达标次数",
            type: "InputNumber",
            inputType: "number",
            min: 0,
            placeholder: "请输入达标次数",
        }
    ],
    courseLearn: [
        {
            name: "time",
            label: "时间",
            type: "DatePicker",
            inputType: "date",
            valueFormat: "yyyy-MM-dd",
            placeholder: "请选择时间",
        },
        {
            name: "allStudyTime",
            label: "学习总时长（分钟）",
            type: "InputNumber",
            inputType: "number",
            min: 0,
            placeholder: "请输入学习总时长（分钟）",
        },
        {
            name: "subCourseStudyNum",
            label: "课程学习节数",
            type: "InputNumber",
            inputType: "number",
            min: 0,
            placeholder: "请输入子级课程学习节数",
        }
    ],
    workExperience: [
        {
            name: "startDate",
            label: "入职时间",
            type: "DatePicker",
            inputType: "date",
            valueFormat: "yyyy-MM-dd",
            placeholder: "请选择入职时间",
        },
        {
            name: "endDate",
            label: "离职时间",
            type: "DatePicker",
            inputType: "date",
            valueFormat: "yyyy-MM-dd",
            placeholder: "请选择离职时间",
        },
        {
            name: "companyName",
            label: "单位名称",
            type: "Input",
            inputType: "text",
            placeholder: "请输入单位名称",
        },
        {
            name: "companyNature",
            label: "单位性质",
            type: "Input",
            inputType: "text",
            placeholder: "请输入单位性质",
        },
        {
            name: "jobDesc",
            label: "工作描述",
            type: "Input",
            inputType: "text",
            placeholder: "请输入工作描述",
        }
    ],
    material: [
        {
            name: "time",
            label: "时间",
            type: "DatePicker",
            inputType: "date",
            valueFormat: "yyyy-MM-dd",
            placeholder: "请选择时间",
        },
        {
            name: "name",
            label: "材料名称",
            type: "Input",
            inputType: "text",
            placeholder: "请输入材料名称",
        },
        {
            name: "url",
            label: "材料详情",
            type: "Upload",
            inputType: "text",
            placeholder: "上传材料",
        }
    ]
};
let json = JSON.stringify(object);
console.log(json);