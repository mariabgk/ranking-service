package com.legalease.repository;

import com.legalease.repository.entities.RankingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RankingRepository extends JpaRepository<RankingEntity, Integer>, JpaSpecificationExecutor<RankingEntity> {

    Page<RankingEntity> findByFirm_FirmRegions_Region_Id(Integer regionId, PageRequest pageRequest);
}
