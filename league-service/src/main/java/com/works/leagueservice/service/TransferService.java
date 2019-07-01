package com.works.leagueservice.service;

import com.works.leagueservice.domain.Team;
import com.works.leagueservice.domain.Transfer;

import java.util.List;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
public interface TransferService {

    Transfer save(Transfer transfer);

    List<Team> getTeamListByPlayer(Long playerId);

    void delete(Long transferId);

    List<Transfer> findTransferByYearAndPlayerId(Long teamId, int transferYear);
}
