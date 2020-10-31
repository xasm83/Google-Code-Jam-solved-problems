package leetcode;

import java.util.Iterator;
import java.util.LinkedHashMap;

//https://leetcode.com/problems/lru-cache/submissions/
public class LRUCache {

    private final LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<Integer, Integer>();
    private final int cap;
    private int size;

    public LRUCache(int capacity) {
        cap = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Integer res = cache.remove(key);
            cache.put(key, res);
            return res;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        } else if (size < cap) {
            size++;
        } else {
            Iterator<Integer> it = cache.keySet().iterator();
            it.next();
            it.remove();
        }
        cache.put(key, value);
    }
}

