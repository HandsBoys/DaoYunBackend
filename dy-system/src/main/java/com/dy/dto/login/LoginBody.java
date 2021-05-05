package com.dy.dto.login;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author cxj
 */
@Data
public class LoginBody {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 手机号
     */
    private String phone;

}
