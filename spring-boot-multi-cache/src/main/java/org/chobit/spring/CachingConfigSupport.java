package org.chobit.spring;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

/**
 * @author robin
 */
//@Configuration
public class CachingConfigSupport extends CachingConfigurerSupport {

    private static final Logger logger = LoggerFactory.getLogger(CachingConfigSupport.class);


    @Value("${caching.spec}")
    private String commonSpec;

    @Bean
    //@ConfigurationProperties("caching.special")
    public Map<String, String> getSpecialCase() {
        return new HashMap<>(4);
    }

    @Override
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        if (isNotBlank(this.commonSpec)) {
            cacheManager.setCacheSpecification(this.commonSpec);
        }
        return cacheManager;
    }


    @Bean("special")
    public CacheManager specialCacheManager(Map<String, String> cases) {
        SimpleCacheManager manager = new SimpleCacheManager();
        if (cases != null) {
            List<CaffeineCache> caches =
                    cases.entrySet().stream()
                            .map(e -> buildCaffeineCache(e.getKey(), e.getValue()))
                            .collect(Collectors.toList());
            manager.setCaches(caches);
        }
        return manager;
    }

    private CaffeineCache buildCaffeineCache(String name, String spec) {
        logger.info("Cache {} specified with config:{}", name, spec);
        final Caffeine<Object, Object> caffeineBuilder = Caffeine.from(spec);
        return new CaffeineCache(name, caffeineBuilder.build());
    }


}
