package com.paprika.springbootaop.dao;

import com.paprika.springbootaop.domain.WebVisitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author adam
 * @date 2019/10/25
 */
@Repository
public interface WebVisitRepository extends JpaSpecificationExecutor<WebVisitInfo>, JpaRepository<WebVisitInfo,String> {
}
