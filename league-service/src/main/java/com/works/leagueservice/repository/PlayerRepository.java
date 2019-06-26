package com.works.leagueservice.repository;

import com.works.leagueservice.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
