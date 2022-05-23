package com.dng.cs.core.repository.base;

import com.dng.cs.core.entity.ClientEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientBaseRepository extends PagingAndSortingRepository<ClientEntity, Long> {

    List<ClientEntity> findClientEntitiesByClientCat(String clientCat);

    Boolean existsClientEntityByRegNumber(String regNumber);


}
