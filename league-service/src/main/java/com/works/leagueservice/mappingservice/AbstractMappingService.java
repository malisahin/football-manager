package com.works.leagueservice.mappingservice;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

/**
 * @author mali.sahin
 * @since 2019-06-27.
 */

@Service
public abstract class AbstractMappingService {


    protected final DozerBeanMapper mapper = new DozerBeanMapper();

}
