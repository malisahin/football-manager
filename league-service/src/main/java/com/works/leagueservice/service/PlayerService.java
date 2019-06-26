package com.works.leagueservice.service;

import com.works.leagueservice.domain.Player;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
public interface PlayerService {
    Player save(Player player);

    void delete(long playerId);
}
