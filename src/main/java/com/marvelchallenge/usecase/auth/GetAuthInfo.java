package com.marvelchallenge.usecase.auth;

import com.marvelchallenge.models.AuthInfo;
import com.marvelchallenge.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public abstract class GetAuthInfo {

    @Value("${api-keys.public}")
    protected String publicKey;
    @Value("${api-keys.private}")
    protected String privateKey;

    public AuthInfo getAuthInfo() {
        final String timestamp = LocalDateTime.now().toString();
        final String hash = TokenUtils.generateHashBy(
                timestamp
                , privateKey
                , publicKey
        );
        return AuthInfo.builder()
                .timestamp(timestamp)
                .hash(hash)
                .build();
    }
}
