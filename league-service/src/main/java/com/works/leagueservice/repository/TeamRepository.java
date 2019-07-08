package com.works.leagueservice.repository;

import com.works.leagueservice.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {

    @Query("update Team set isActv = :isActv where teamId = :teamId")
    void disableTeam(@Param("teamId") long teamId, @Param("isActv") Long isActv);

    Optional<Team> findByTeamIdAndIsActv(long teamId, Long isActv);
}
