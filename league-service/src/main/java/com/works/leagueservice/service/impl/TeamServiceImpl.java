package com.works.leagueservice.service.impl;

import com.works.leagueservice.domain.Constants;
import com.works.leagueservice.domain.Team;
import com.works.leagueservice.repository.TeamRepository;
import com.works.leagueservice.service.TeamService;
import com.works.sharedlibrary.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Service
public class TeamServiceImpl extends BaseService implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    @Transactional()
    public Team save(Team team) {
        return Optional.ofNullable(team)
                .map(Team::getTeamId)
                .map(id -> this.update(team))
                .orElseGet(() -> this.persist(team));
    }

    private Team persist(Team team) {
        return teamRepository.save(team);
    }

    private Team update(Team team) {
        Optional<Team> existingTeam = this.teamRepository.findByTeamIdAndIsActv(team.getTeamId(), Constants.DEFAULT_VALID_VALUE);
        if (!existingTeam.isPresent()) {
            throw new ResourceNotFoundException("Team is not found with id " + team.getTeamId());
        }
        Team exist = existingTeam.get();
        exist.setTeamName(team.getTeamName());
        return this.persist(exist);
    }

    @Override
    @Transactional()
    public void delete(long teamId) {
        teamRepository.disableTeam(teamId, Constants.DEFAULT_INVALID_VALUE);
    }

}
