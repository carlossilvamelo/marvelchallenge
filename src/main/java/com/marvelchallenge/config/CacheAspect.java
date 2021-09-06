package com.marvelchallenge.config;

import com.marvelchallenge.inmemorycache.GetCharactersIdsCache;
import com.marvelchallenge.usecase.impl.GetCharactersIdsImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class CacheAspect {

    @Around("execution(* com.marvelchallenge.usecase..*.*(..))")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {

        GetCharactersIdsCache getCharactersIdsCache = GetCharactersIdsCache
                .getInstance();

        var methodSignature = (MethodSignature) joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        var log = LoggerFactory.getLogger(methodSignature.getDeclaringTypeName());


        if ((target instanceof GetCharactersIdsImpl)
                && getCharactersIdsCache.isCached()) {
            log.debug("getCharactersIds cache response");
            return getCharactersIdsCache.getCache();
        }

        Object result = joinPoint.proceed();

        if ((target instanceof GetCharactersIdsImpl)
                && !getCharactersIdsCache.isCached()) {
            log.info("Character cached");
            getCharactersIdsCache.setCache((List<Integer>) result);
        }
        return result;
    }
}
