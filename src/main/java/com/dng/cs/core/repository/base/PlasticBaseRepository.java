package com.dng.cs.core.repository.base;

import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.entity.PlasticEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlasticBaseRepository extends PagingAndSortingRepository<PlasticEntity, Long> {
    Optional<PlasticEntity> findFirstByCardNumber(String cardNumber);
}
