package com.marvelchallenge.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

@Slf4j
@UtilityClass
public class TokenUtils {

    public static String generateHashBy(String ts, String privateKey, String publicKey) {
        String values = String.format("%s%s%s"
                , ts, privateKey, publicKey);

        return DigestUtils.md5DigestAsHex(values.getBytes());
    }
}
