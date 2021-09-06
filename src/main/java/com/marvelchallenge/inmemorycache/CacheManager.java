package com.marvelchallenge.inmemorycache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableAsync
@Slf4j
@Configuration
@EnableScheduling
public class CacheManager {
    public static final Integer TTL = 3;

    @Async
    @Scheduled(fixedDelay = 30000, initialDelay = 30000) // 30s
    public void clear() {
        GetCharacterByIdCache.getInstance().clear();
        GetCharactersIdsCache.getInstance().clear();
    }
}
