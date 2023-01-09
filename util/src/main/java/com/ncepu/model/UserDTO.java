package com.ncepu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wengym
 * @version 1.0
 * @desc dto
 * @date 2022/5/20 9:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    /**
     * 32位uuid
     */
    private String id;
    /**
     * 手机号
     */
    private String userId;
    /**
     * 密码
     */
    private String password;
    /**
     * 其他
     */
    private String other;
}
