package com.ljm.repository;

import com.ljm.domain.DemoInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * DemoInfo持久化类
 */
public interface DemoInfoRepository extends CrudRepository<DemoInfo, Long> {
}
