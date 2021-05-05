package com.dy.dto.login;

import com.dy.common.utils.AjaxResult;
import lombok.Data;

/**
 * @author cxj
 */
@Data
public class TokenDto {
    private String token;
    public AjaxResult ajaxResult;

    public TokenDto() {
        this.token = null;
    }
}
