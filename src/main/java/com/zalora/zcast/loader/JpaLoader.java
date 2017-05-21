package com.zalora.zcast.loader;

import com.hazelcast.core.MapLoader;
import com.zalora.zcast.domain.ItemRepository;
import com.zalora.zcast.domain.MemcachedItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import java.io.Serializable;
import java.util.*;

import javax.cache.integration.CacheLoaderException;

/**
 * @author Wolfram Huesken <wolfram.huesken@zalora.com>
 */
@Slf4j
public class JpaLoader implements MapLoader<String, MemcachedItem>, Serializable {

    private static final long serialVersionUID = 8504061948261319292L;

    private ItemRepository itemRepo;

    public JpaLoader(ItemRepository itemRepo) {
        Assert.notNull(itemRepo, "Itemrepo must not be null");
        this.itemRepo = itemRepo;
    }

    @Override
    public MemcachedItem load(String key) throws CacheLoaderException {
        log.info("### Key: {}", key);
        MemcachedItem memcachedItem = itemRepo.findOne(key);

        log.info("### Value: {}", memcachedItem);
        if (memcachedItem == null) {
            return new MemcachedItem(key);
        }

        return memcachedItem;
    }

    @Override
    public Map<String, MemcachedItem> loadAll(Collection<String> keys) {
        Map<String, MemcachedItem> map = new HashMap<>();

        for (MemcachedItem item : itemRepo.findAll(keys)) {
            map.put(item.getKey(), item);
        }

        return map;
    }

    @Override
    public Iterable<String> loadAllKeys() {
        return new LinkedList<>();
    }

}
