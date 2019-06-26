package com.works.leagueservice.mappingservice;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mali.sahin
 * @since 2019-06-27.
 */

@Service
public abstract class AbstractMappingService {

    @Autowired
    protected DozerBeanMapper mapper;

}
