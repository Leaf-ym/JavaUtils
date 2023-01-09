package com.ncepu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherNoSettingExportVo implements Serializable {

    private Integer serialNum;

    private String productType;

    private String productName;

    private String originFrom;

    private String phone;
}
