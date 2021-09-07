package com.marvelchallenge.config.inmemorycache;

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

    @Async
    @Scheduled(fixedDelay = 30000) // 30s
    public void clear() {
        GetCharactersIdsCache.getInstance().clear();
    }
}
