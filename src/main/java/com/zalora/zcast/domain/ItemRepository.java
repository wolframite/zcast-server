package com.zalora.zcast.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional(rollbackOn = Exception.class)
public interface ItemRepository extends PagingAndSortingRepository<MemcachedItem, String> {}
