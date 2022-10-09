package com.zeeshan.tms.repository;

import com.zeeshan.tms.domain.AssetType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AssetType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetTypeRepository extends JpaRepository<AssetType, Long> {}
