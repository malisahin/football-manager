package com.works.leagueservice.repository;

import com.works.leagueservice.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>, JpaSpecificationExecutor<Transfer> {

    Optional<Transfer> findByTransferIdAndIsActv(Long transferId, Long isActv);

    List<Transfer> findByPlayerId(Long playerId);

    @Query("select t from Transfer t where t.terminalTeamId = :teamId " +
            "and t.transferYear <= :transferYear " +
            "and t.isActv = 1 " +
            "and t.playerId not in (select d from Transfer d where d.departureTeamId = :teamId and " +
            "                           d.transferYear <= :transferYear " +
            "                           and d.isActv =1 )")
    List<Transfer> findTransferByYearAndPlayerId(@Param("teamId") Long teamId, @Param("transferYear") int transferYear);

    @Query("update Transfer  set transferId = :transferId")
    void disableTransfer(@Param("transferId") Long transferId);
}
