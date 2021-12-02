package mt.spacewebapp.services.impl.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CacheService {

    @Autowired
    CacheManager cacheManager;

    public Collection<String> getCacheNames() {
        return cacheManager.getCacheNames();
    }

    public void evictCacheValues(String cacheName) {
        cacheManager.getCache(cacheName).clear();
    }
}
