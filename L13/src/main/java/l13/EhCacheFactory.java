package l13;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class EhCacheFactory {
    private Cache cache;

    public EhCacheFactory() {
        CacheManager cacheManager = CacheManager.getInstance();
        cacheManager.addCache("mycache");
        Cache cache = cacheManager.getCache("mycache");

        // заполняем тестовыми данными, чтобы что-то отобразилось на странице статистики кеша

        cache.put(new Element("1", "Jan"));
        cache.put(new Element("2", "Feb"));
        cache.put(new Element("3", "Mar"));

        cache.get("1");
        cache.get("2");
        cache.get("5"); //miss

        this.cache = cache;
    }

    public Cache getCache() {
        return cache;
    }
}
