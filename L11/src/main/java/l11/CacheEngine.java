package l11;

public interface CacheEngine {
    void put(long key, Object value);

    Object get(long key);
}
