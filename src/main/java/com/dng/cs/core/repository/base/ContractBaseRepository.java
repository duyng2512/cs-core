package com.dng.cs.core.repository.base;

import com.dng.cs.core.entity.ContractEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractBaseRepository extends PagingAndSortingRepository<ContractEntity, Long> {



}
