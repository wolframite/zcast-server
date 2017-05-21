package com.zalora.zcast.beans;

import com.hazelcast.config.*;
import com.hazelcast.core.*;
import com.zalora.zcast.domain.ItemRepository;
import com.zalora.zcast.domain.MemcachedItem;
import com.zalora.zcast.loader.JpaLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Wolfram Huesken <wolfram.huesken@zalora.com>
 */
@Slf4j
@Service
public class HazelcastInit {

    @Autowired
    private ItemRepository itemRepo;

    @PostConstruct
    public void init() {
        Config config = new Config();
        MapConfig mapConfig = config.getMapConfig("main");

        MapStoreConfig mapStoreConfig = new MapStoreConfig();
        mapStoreConfig.setFactoryImplementation(
            (MapStoreFactory<String, MemcachedItem>) (s, properties) -> new JpaLoader(itemRepo)
        );

        mapConfig.setMapStoreConfig(mapStoreConfig);
        Hazelcast.newHazelcastInstance(config);
    }

}
