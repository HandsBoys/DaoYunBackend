package com.dy.dto.login;

import lombok.Data;

/**
 * @author cxj
 */
@Data
public class TokenDto {
    private String token;

    public TokenDto(String token){
        this.token = token;
    }
}
