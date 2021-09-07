package com.marvelchallenge.config.inmemorycache;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableAsync
@Configuration
@EnableScheduling
public class CacheManager {

    @Async
    @Scheduled(fixedDelay = 30000) // 30s
    public void clear() {
        CharactersIdsCacheSingleton.getInstance().clear();
    }
}
