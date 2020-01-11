package com.language.originality.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.language.originality.constant.ConstantJWT;
import com.language.originality.ecceptions.ApplicationException;
import com.language.originality.enums.ResultStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JWT工具类
 *
 * @author hughes
 */
@Component
@Slf4j
public class JWTUtil {

    public static final int expireTime = 2 * 1000;
    public static final String SECRETE = "SECRETE";
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 生成签名
     *
     * @return
     */
    public static String sign() {
        System.out.println("签发时间：" + sdf.format(new Date()));
        sdf.format(new Date());
        Date date = new Date(System.currentTimeMillis() + expireTime);
        sdf.format(date);
        System.out.println("签发过期时间：" + sdf.format(date));


        Algorithm algorithm = Algorithm.HMAC256(SECRETE);
        return JWT.create().withExpiresAt(date).sign(algorithm);
    }


    /**
     * 验证签名
     */
    public static boolean verify(String accessToken) {
        Algorithm algorithm = Algorithm.HMAC256(SECRETE);
        try {
            JWTVerifier verifier = JWT.require(algorithm).acceptExpiresAt(2).build();
            verifier.verify(accessToken);
            return Boolean.TRUE;
        } catch (JWTVerificationException e) {
            return Boolean.FALSE;
        }
    }

    /**
     * @return boolean  true 过期  false 不过期
     * @Author zhaikaixuan
     * @Description 验证token是否过期
     * @Date 2020-01-11 21:21
     * @Param [token]
     **/
    public static boolean isTokenExpired(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Date expiresAt = jwt.getExpiresAt();
        System.out.println("签发过期时间：" + sdf.format(expiresAt));
        System.out.println("验证时间：" + sdf.format(new Date()));
        return jwt.getExpiresAt().before(new Date());
    }


    public static void main(String[] args) throws InterruptedException {
        String sign = sign();
        Thread.sleep(1000);
        System.out.println(sign);
        boolean verify = verify(sign);
        System.out.println(verify);
        boolean tokenExpired = isTokenExpired(sign);
        System.out.println(tokenExpired);
    }
}
