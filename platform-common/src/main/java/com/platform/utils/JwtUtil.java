package com.platform.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * 生成jwt
     *
     * @param secreKey  jwt密钥
     * @param ttlMillis jwt过期时间
     * @param claims    设置信息
     * @return
     */
    public static String createJWT(String secreKey, long ttlMillis, Map<String, Object> claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成jwt时间
        long expmillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expmillis);

        //设置生成jwt的body
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .signWith(signatureAlgorithm, secreKey.getBytes(StandardCharsets.UTF_8))
                .setExpiration(exp);

        return builder.compact();
    }


    public static Claims parseJWT(String secreKey, String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secreKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token).getBody();
        return claims;
    }
}