package org.chobit.spring;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class CachingConfig {

    private static final Logger logger = LoggerFactory.getLogger(CachingConfig.class);

    private final CacheProperties cacheProperties;

    public CachingConfig(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }


    @Primary
    @Bean("main")
    public CacheManager cacheManager() {
        CaffeineCacheManager manager = createCacheManager();
        List<String> cacheNames = this.cacheProperties.getCacheNames();
        if (!CollectionUtils.isEmpty(cacheNames)) {
            manager.setCacheNames(cacheNames);
        }
        return manager;
    }

    private CaffeineCacheManager createCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        String specification = this.cacheProperties.getCaffeine().getSpec();
        if (isNotBlank(specification)) {
            cacheManager.setCacheSpecification(specification);
        }
        return cacheManager;
    }


    @Bean("specialCase")
    public CacheManager cacheManager(Ticker ticker, Map<String, String> cases) {
        SimpleCacheManager manager = new SimpleCacheManager();
        if (cases != null) {
            List<CaffeineCache> caches =
                    cases.entrySet().stream()
                            .map(e -> buildCaffeineCache(e.getKey(), e.getValue(), ticker))
                            .collect(Collectors.toList());
            manager.setCaches(caches);
        }
        return manager;
    }

    private CaffeineCache buildCaffeineCache(String name, String spec, Ticker ticker) {
        logger.info("Cache {} specified with config:{}", name, spec);
        final Caffeine<Object, Object> caffeineBuilder = Caffeine.from(spec).ticker(ticker);
        return new CaffeineCache(name, caffeineBuilder.build());
    }


    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }

    @Bean
    @ConfigurationProperties("spring.cache.caffeine.special")
    public Map<String, String> getSpecialCase() {
        return new HashMap<>(4);
    }
}
