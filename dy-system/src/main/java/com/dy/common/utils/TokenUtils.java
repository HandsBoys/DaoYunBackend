package com.dy.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 */
@Component
public class TokenUtils {

    // 令牌自定义标识
    @Value("${jwt.tokenHeader}")
    private String header;

    // 令牌秘钥
    @Value("${jwt.secret}")
    private String secret;

    // 令牌前缀
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    private final String CLAIMS_USER = "sub";
    private final String CLAIMS_CREATED = "created";

    @Value("${jwt.expiration}")
    private Integer expirationTime;

    /**
     * 根据用户名生成token
     *
     * @param userDetails
     * @return
     */
    public String generatorToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_USER, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, new Date());
        return generatorToken(claims);
    }

    /**
     * 根据荷载生成token
     *
     * @param claims
     * @return
     */
    private String generatorToken(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(generateExpiration())
                .compact();
    }

    /**
     * 生成过期时间
     *
     * @return
     */
    private Date generateExpiration() {
        return new Date(System.currentTimeMillis() + expirationTime * 1000 * 60);
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        try{
            Claims claims = getClaimsFromToken(token);
            return claims.getSubject();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    /**
     * 根据token获取荷载
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 判断token是否有效
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String name = getUserNameFromToken(token);
        return name.equals(userDetails.getUsername()) && !isTokenExpire(token);
    }

    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    private boolean isTokenExpire(String token) {
        Date expiration = getExpirationFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 从token中获取失效时间
     * @param token
     * @return
     */
    private Date getExpirationFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断用户是否可以被刷新
     * @param token
     * @return
     */
    public  boolean canRefresh(String token){
        return !isTokenExpire(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIMS_CREATED,new Date());
        return generatorToken(claims);
    }


    public String getToken(HttpServletRequest request){
        String token = request.getHeader(header);
        if(StringUtils.isNotEmpty(token) && token.startsWith(tokenPrefix)){
            token = token.substring(tokenPrefix.length());
        }
        else {
            token = null;
        }
        return token;
    }

}
