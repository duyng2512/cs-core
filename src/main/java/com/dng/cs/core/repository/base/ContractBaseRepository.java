package com.dng.cs.core.repository.base;

import com.dng.cs.core.entity.ContractEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractBaseRepository extends PagingAndSortingRepository<ContractEntity, Long> {

    @Modifying
    @Query("update ContractEntity u set u.isReady = 'N', u.status = :newStatus where u.id = :id")
    void lockContract(@Param(value = "id") long id, @Param(value = "newStatus") String newStatus);

}
