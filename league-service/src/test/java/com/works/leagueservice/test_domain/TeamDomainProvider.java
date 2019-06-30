package com.works.leagueservice.test_domain;

import com.works.sharedlibrary.domain.dto.TeamDTO;

/**
 * @author mali.sahin
 * @since 2019-06-30.
 */
public class TeamDomainProvider {

    public static TeamDTO withRequiredFields() {
        TeamDTO team = new TeamDTO();
        team.teamName = "Manchester United";
        team.teamId = 1L;
        return team;
    }

    public static TeamDTO departureTeam() {
        return TeamDomainProvider.withRequiredFields();
    }


    public static TeamDTO destinationTeam() {
        TeamDTO destination = new TeamDTO();
        destination.teamName = "Chelsea";
        destination.teamId = 2L;
        return destination;
    }
}
