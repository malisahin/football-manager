package com.works.leagueservice.test_domain;

import com.works.sharedlibrary.domain.dto.PlayerDTO;

/**
 * @author mali.sahin
 * @since 2019-06-30.
 */
public class PlayerDomainProvider {

    public static PlayerDTO withRequiredFields() {
        final PlayerDTO player = new PlayerDTO();
        player.playerName = "Ronaldo";
        player.birthYear = 1983;
        player.careerStartYear = 2000;
        player.teamId = TeamDomainProvider.withRequiredFields().teamId;
        player.team = TeamDomainProvider.withRequiredFields();
        return player;
    }
}
