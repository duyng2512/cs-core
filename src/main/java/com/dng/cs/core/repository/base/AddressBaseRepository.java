package com.dng.cs.core.repository.base;

import com.dng.cs.core.entity.AddressEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBaseRepository extends PagingAndSortingRepository<AddressEntity, Long> {

}
