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
public class UserDemo<T, F> {
    private T t;

    private F f;
}
