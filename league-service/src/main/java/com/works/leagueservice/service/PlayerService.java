package com.works.leagueservice.service;

import com.works.leagueservice.domain.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
public interface PlayerService {
    Player save(Player player);

    void delete(long playerId);

    Page<Player> findAll(Pageable pageable);
}
