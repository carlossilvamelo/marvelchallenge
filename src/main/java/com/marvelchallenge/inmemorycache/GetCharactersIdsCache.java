package com.marvelchallenge.inmemorycache;


import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class GetCharactersIdsCache {

    private static GetCharactersIdsCache instance = null;
    private List<Integer> cache;
    private Instant startedAt;

    private GetCharactersIdsCache() {
    }

    public static GetCharactersIdsCache getInstance() {
        if (ObjectUtils.allNull(instance)) {
            instance = new GetCharactersIdsCache();
        }
        return instance;
    }

    public boolean isCached() {
        return ObjectUtils.allNotNull(instance) &&
                !CollectionUtils.isEmpty(instance.getCache());
    }

    public List<Integer> getCache() {
        return instance.cache;
    }

    public void setCache(List<Integer> cache) {
        this.instance.cache = cache;
        this.instance.startedAt = Instant.now();
    }

    public void clear() {
        if (ObjectUtils.allNotNull(this.instance, this.instance.startedAt)) {
            Long minutes = Duration
                    .between(this.instance.startedAt, Instant.now())
                    .toMinutes();

            if (minutes >= CacheManager.TTL)
                instance.cache = null;
        }
    }
}
