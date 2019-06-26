package com.works.leagueservice.repository;

import com.works.leagueservice.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
