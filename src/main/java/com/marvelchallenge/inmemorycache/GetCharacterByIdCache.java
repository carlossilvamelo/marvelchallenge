package com.marvelchallenge.inmemorycache;


import com.marvelchallenge.models.Character;
import org.apache.commons.lang3.ObjectUtils;

import java.time.Duration;
import java.time.Instant;


public class GetCharacterByIdCache {

    private static GetCharacterByIdCache instance = null;
    private Character cache;
    private Instant startedAt;

    private GetCharacterByIdCache() {
    }

    public static GetCharacterByIdCache getInstance() {
        if (ObjectUtils.allNull(instance)) {
            instance = new GetCharacterByIdCache();
        }
        return instance;
    }

    public boolean isCached() {
        return ObjectUtils.allNotNull(instance, instance.cache);
    }

    public void setCache(Character cache) {
        this.instance.cache = cache;
        this.instance.startedAt = Instant.now();
    }

    public Character getCache() {
        return instance.cache;
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
