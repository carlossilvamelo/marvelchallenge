package com.marvelchallenge.config;

import com.marvelchallenge.config.inmemorycache.CharactersIdsCacheSingleton;
import com.marvelchallenge.usecase.character.impl.GetCharactersIdsImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Component
public class CacheAspectInterceptor {

    @Around("execution(* com.marvelchallenge.usecase..*.*(..))")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {

        CharactersIdsCacheSingleton getCharactersIdsCache = CharactersIdsCacheSingleton
                .getInstance();

        Object target = joinPoint.getTarget();

        if ((target instanceof GetCharactersIdsImpl)
                && getCharactersIdsCache.isCached()) {
            log.debug("getCharactersIds cache response");
            return getCharactersIdsCache.getCache();
        }

        Object result = joinPoint.proceed();

        if ((target instanceof GetCharactersIdsImpl)
                && !getCharactersIdsCache.isCached()) {
            log.debug("Character cached");
            getCharactersIdsCache.setCache((List<Integer>) result);
        }
        return result;
    }
}
