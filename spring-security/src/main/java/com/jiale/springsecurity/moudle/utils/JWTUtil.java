package com.jiale.springsecurity.moudle.utils;

import com.alibaba.fastjson2.JSON;
import com.jiale.springsecurity.moudle.model.Claims;
import com.jiale.springsecurity.moudle.model.ResponseEntity;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;


import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

public class JWTUtil {
    //密钥
    private static final String secret = "xpo1xgnl5ksinxkgu1nb6vcx3zaq1wsxvv";

    // 1000 * 60 * 60 * 24 * 1  一天
    //过期时间12h，单位毫秒
    private static final long EXPIRE = 1000 * 60 * 60 * 12;
    // 测试时为1min
    // private static final long EXPIRE = 1000 * 60 * 1;

    /**
     * 创建token
     *
     * @param claims 用户信息
     * @return 令牌
     */
    public static String createToken(Claims claims) {
        try {
            //对密钥进行签名
            JWSSigner jwsSigner = new MACSigner(secret);

            //准备JWS header
            JWSHeader jwsHeader = new JWSHeader
                    .Builder(JWSAlgorithm.HS256)
                    .type(JOSEObjectType.JWT)
                    .build();

            //准备JWS payload
            claims.setJti(UUID.randomUUID().toString());
            claims.setIat(new Date().getTime());
            claims.setExp(new Date(System.currentTimeMillis() + EXPIRE).getTime());

            Payload payload = new Payload(JSON.toJSONString(claims));

            //封装JWS对象
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);

            //签名
            jwsObject.sign(jwsSigner);

            return jwsObject.serialize();

        } catch (KeyLengthException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证并获取用户信息
     *
     * @param token 令牌
     * @return 解析后用户信息
     */
    public static ResponseEntity verifyToken(String token) {
        JWSObject jwsObject;
        ResponseEntity response = new ResponseEntity<>();
        try {
            jwsObject = JWSObject.parse(token);

            //HMAC验证器
            JWSVerifier jwsVerifier = new MACVerifier(secret);
            if (!jwsObject.verify(jwsVerifier)) {
                response.setCode(10008);
                response.setErrorMsg("token无效");
                return response;
            }

            String payload = jwsObject.getPayload().toString();
            Claims claims = JSON.parseObject(payload, Claims.class);
            if (claims.getExp() < new Date().getTime()) {
                response.setCode(10008);
                response.setErrorMsg("token无效");
                return response;
            }

            response.setCode(200);
            response.setData(claims);
            response.setMessage("解析成功");
            return response;
        } catch ( JOSEException | ParseException e) {
            e.printStackTrace();
        }
        response.setCode(10008);
        response.setErrorMsg("token无效");
        return response;
    }

}
