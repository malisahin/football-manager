package com.works.leagueservice.repository;

import com.works.leagueservice.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>, JpaSpecificationExecutor<Transfer> {
    Optional<Transfer> findByTransferIdAndIsActv(Long transferId, int isActv);

    List<Transfer> findByPlayerId(Long playerId);
}
