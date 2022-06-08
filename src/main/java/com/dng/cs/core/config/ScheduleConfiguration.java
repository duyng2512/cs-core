package com.dng.cs.core.config;

import com.dng.cs.core.repository.base.StatusBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.List;


@Slf4j
@Configuration
@EnableScheduling
public class ScheduleConfiguration {

    private final HashMap<String, String> cacheStatus;
    private final StatusBaseRepository repository;

    public ScheduleConfiguration(@Qualifier("cacheContractStatus") HashMap<String, String> cacheStatus,
                                 StatusBaseRepository repository) {
        this.cacheStatus = cacheStatus;
        this.repository = repository;
    }

    @Scheduled(initialDelay = 100000, fixedRate = 300000)
    public void cacheStatus() {
        log.info("Loading Caching status from DB");
        List<StatusBaseRepository.codeAndName> listStatus = this.repository.findAllBy();
        for (StatusBaseRepository.codeAndName c : listStatus){
            this.cacheStatus.put(c.getCode(), c.getName());
            log.info(String.format("Caching status [%s] code [%s]", c.getName(), c.getCode()));
        }
    }

}
