package com.dng.cs.core.config;

import com.dng.cs.core.repository.base.StatusBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

@Configuration
@Slf4j
public class InitialConfiguration {

    @Bean(name = "cacheContractStatus")
    HashMap<String, String> cacheContractStatus (StatusBaseRepository repository) {
        HashMap<String, String> map = new HashMap<>();
        List<StatusBaseRepository.codeAndName> listStatus = repository.findAllBy();
        for (StatusBaseRepository.codeAndName c : listStatus){
            map.put(c.getCode(), c.getName());
            log.info(String.format("Caching status [%s] code [%s]", c.getName(), c.getCode()));
        }
        return map;
    }

}
