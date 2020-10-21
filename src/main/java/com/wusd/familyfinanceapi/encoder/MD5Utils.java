package com.wusd.familyfinanceapi.encoder;

import org.springframework.util.DigestUtils;

public class MD5Utils {
    public static String md5(CharSequence str) {
        return DigestUtils.md5DigestAsHex(str.toString().getBytes()).toUpperCase();
    }
}
