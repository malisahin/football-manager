package com.works.leagueservice.service.impl;

import com.works.leagueservice.domain.Constants;
import com.works.leagueservice.domain.Player;
import com.works.leagueservice.domain.Team;
import com.works.leagueservice.domain.Transfer;
import com.works.leagueservice.repository.TransferRepository;
import com.works.leagueservice.service.TransferService;
import com.works.sharedlibrary.exceptions.InvalidFieldException;
import com.works.sharedlibrary.exceptions.ResourceNotFoundException;
import com.works.sharedlibrary.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Service
public class TransferServiceImpl extends BaseService implements TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Override
    @Transactional
    public Transfer save(Transfer transfer) {
        return Optional.of(transfer)
                .map(Transfer::getTransferId)
                .map(id -> this.update(transfer))
                .orElse(this.persist(transfer));
    }

    @Override
    @Transactional
    public Transfer update(Transfer transfer) {
        Optional<Transfer> existTransfer = this.transferRepository.findByTransferIdAndIsActv(transfer.getTransferId(), Constants.DEFAULT_VALID_VALUE);
        Optional.ofNullable(existTransfer)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer is not found"));
        existTransfer.ifPresent(old -> {
            old.setDeparture(transfer.getDeparture());
            old.setTerminal(transfer.getTerminal());
            old.setPlayer(transfer.getPlayer());
            old.setTransferYear(transfer.getTransferYear());
        });
        return this.persist(existTransfer.get());


    }

    private Transfer persist(Transfer transfer) {
        validateToPersist(transfer);
        return this.save(transfer);
    }

    private void validateToPersist(Transfer transfer) {
        Optional.ofNullable(transfer)
                .orElseThrow(() -> new InvalidFieldException("Transfer can not be null;"));

        Optional.of(transfer)
                .map(Transfer::getDeparture)
                .map(Team::getTeamId)
                .filter(id -> id > Constants.DEFAULT_INVALID_VALUE)
                .orElseThrow(() -> new InvalidFieldException("Departure team is not valid"));

        Optional.of(transfer)
                .map(Transfer::getTerminal)
                .map(Team::getTeamId)
                .filter(id -> id > Constants.DEFAULT_INVALID_VALUE)
                .orElseThrow(() -> new InvalidFieldException("Terminal team is not valid"));

        Optional.of(transfer)
                .map(Transfer::getPlayer)
                .map(Player::getPlayerId)
                .filter(id -> id > Constants.DEFAULT_INVALID_VALUE)
                .orElseThrow(() -> new InvalidFieldException("Player is not valid"));

        Optional.of(transfer)
                .map(Transfer::getPlayer)
                .map(Player::getPlayerId)
                .filter(id -> id > Constants.DEFAULT_INVALID_VALUE)
                .orElseThrow(() -> new InvalidFieldException("Player is not valid"));

        Optional.of(transfer)
                .map(Transfer::getTransferYear)
                .filter(year -> year <= DateUtil.getYear(new Date()))
                .orElseThrow(() -> new InvalidFieldException("Transfer date is not valid"));


    }

    @Override
    public List<Team> getTeamListByPlayer(Long playerId) {
        final List<Transfer> transferList = this.transferRepository.findByPlayerId(playerId);
        transferList.sort(Transfer::compareTo);
        final List<Team> teamList = transferList.stream()
                .map(Transfer::getDeparture)
                .collect(Collectors.toList());

        final Transfer lastTransfer = transferList.get(transferList.size() - 1);
        teamList.add(lastTransfer.getTerminal());
        return teamList;
    }

    @Override
    public void delete(Long transferId) {
        this.transferRepository.disableTransfer(transferId);
    }

    @Override
    public List<Transfer> findTransferByYearAndPlayerId(Long teamId, int transferYear) {
        return this.transferRepository.findTransferByYearAndPlayerId(teamId, transferYear);
    }

    public List<Player> findPlayerByYearAndTeamId(Long teamId, int year) {
        List<Player> transferredList = findTransferByYearAndPlayerId(teamId, year).stream()
                .map(Transfer::getPlayer)
                .collect(Collectors.toList());
        return null;
    }
}
