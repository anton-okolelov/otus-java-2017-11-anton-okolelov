package l11;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class MyCacheEngine implements CacheEngine {

    private Map<Long, SoftReference<Object>> map = new HashMap<>();

    public void MyCacheEngine() {

    }

    @Override
    public void put(long key, Object value) {
        map.put(key, new SoftReference<Object>(value));
    }

    @Override
    public Object get(long key) {
        if (map.containsKey(key)) {
            return map.get(key).get();
        } else {
            return null;
        }
    }
}
