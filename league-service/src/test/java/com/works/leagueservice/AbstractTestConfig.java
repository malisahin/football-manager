package com.works.leagueservice;

import com.google.gson.Gson;
import org.dozer.DozerBeanMapper;
import org.springframework.http.MediaType;

/**
 * @author mali.sahin
 * @since 2019-06-30.
 */
public abstract class AbstractTestConfig {

    protected static final MediaType DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON;
    protected final DozerBeanMapper mapper = new DozerBeanMapper();
    protected final Gson gson = new Gson();
}
