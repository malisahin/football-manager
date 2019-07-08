package com.works.leagueservice.service.impl;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * @author mali.sahin
 * @since 2019-07-08.
 */
public abstract class AbstractTestService {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
}
