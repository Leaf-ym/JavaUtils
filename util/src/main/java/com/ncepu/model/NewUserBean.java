package com.ncepu.model;

import com.ncepu.util.DatabaseUtils.annotation.DatabaseField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wengym
 * @version 1.0
 * @desc TODO
 * @date 2021/11/17 9:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserBean extends ReqDTO {
    @DatabaseField
    private Integer id;
    @DatabaseField
    private int mlId;
    @DatabaseField
    private String userName;
    @DatabaseField
    private String password;
    @DatabaseField
    private String fillDate;
    @DatabaseField
    private Date updateDate;
}
