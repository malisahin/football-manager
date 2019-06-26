package com.works.leagueservice.service.impl;

import com.works.leagueservice.domain.Team;
import com.works.leagueservice.repository.TeamRepository;
import com.works.leagueservice.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    @Transactional()
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    @Transactional()
    public void delete(long teamId) {
        teamRepository.deleteById(teamId);
    }
}
