package com.marvelchallenge.inmemorycache;


import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.CollectionUtils;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class GetCharactersIdsCache {

    private static final Integer TTL = 3;
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
        return !CollectionUtils.isEmpty(this.cache);
    }

    public List<Integer> getCache() {
        return this.cache;
    }

    public void setCache(List<Integer> cache) {
        this.cache = cache;
        this.startedAt = Instant.now();
    }

    public void clear() {
        if (ObjectUtils.allNotNull(this.startedAt)) {
            Long minutes = Duration
                    .between(this.startedAt, Instant.now())
                    .toMinutes();

            if (minutes >= TTL)
                this.cache = null;
        }
    }
}
