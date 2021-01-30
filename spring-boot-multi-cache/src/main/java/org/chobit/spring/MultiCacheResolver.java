package org.chobit.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

/**
 * @author robin
 */
//@Component("multi")
public class MultiCacheResolver implements CacheResolver, InitializingBean {

    @Value("${caching.spec}")
    private String commonSpec;

    private CacheManager manager;

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<Cache> caches = new LinkedList<>();
        Set<String> cacheNames = context.getOperation().getCacheNames();
        for (String name : cacheNames) {
            caches.add(manager.getCache(name));
        }
        return caches;
    }


    public CacheManager newCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        if (isNotBlank(this.commonSpec)) {
            cacheManager.setCacheSpecification(this.commonSpec);
        }
        return cacheManager;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        manager = newCacheManager();
    }
}
