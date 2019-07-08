package com.works.leagueservice.mappingservice;

import com.works.leagueservice.domain.Team;
import com.works.leagueservice.service.TeamService;
import com.works.sharedlibrary.domain.dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Service
public class TeamMappingService extends AbstractMappingService {

    @Autowired
    private TeamService teamService;

    public TeamDTO save(TeamDTO teamDTO) {
        final Team team = mapper.map(teamDTO, Team.class);
        final Team insertedTeam = teamService.save(team);
        return mapper.map(insertedTeam, TeamDTO.class);
    }

    public TeamDTO update(TeamDTO teamDTO) {
        final Team team = mapper.map(teamDTO, Team.class);
        final Team insertedTeam = teamService.update(team);
        return mapper.map(insertedTeam, TeamDTO.class);
    }

    public void delete(Long teamId) {
        this.teamService.delete(teamId);
    }

    public List<TeamDTO> getPlayerTeamHistory(Long playerId) {
        return new ArrayList<>();
    }

}
