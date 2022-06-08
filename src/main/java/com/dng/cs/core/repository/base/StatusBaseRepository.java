package com.dng.cs.core.repository.base;

import com.dng.cs.core.entity.ClientEntity;
import com.dng.cs.core.entity.StatusEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusBaseRepository  extends PagingAndSortingRepository<StatusEntity, Long> {

    interface codeAndName{
        String getCode();
        String getName();
    }

    @NotNull
    List<StatusBaseRepository.codeAndName> findAllBy();
}
