package com.works.leagueservice.repository;

import com.works.leagueservice.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
