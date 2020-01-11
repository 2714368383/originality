package com.language.originality.utils;

import com.language.originality.ecceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;


/**
 * base64工具类
 *
 * @author hughes
 */
@Slf4j
public class Base64Util {

    public static String encode(String str) {
        try {
            byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
            return new String(encodeBytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApplicationException("base64编码异常");
        }
    }

    public static String decode(String str) {
        try {
            byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes("utf-8"));
            return new String(decodeBytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApplicationException("base64解码异常");
        }
    }
}
