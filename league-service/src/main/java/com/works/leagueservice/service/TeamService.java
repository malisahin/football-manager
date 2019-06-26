package com.works.leagueservice.service;

import com.works.leagueservice.domain.Team;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
public interface TeamService {
    Team save(Team team);

    void delete(long teamId);
}
