package com.works.leagueservice.service.impl;

import com.works.leagueservice.domain.Constants;
import com.works.leagueservice.domain.Player;
import com.works.leagueservice.repository.PlayerRepository;
import com.works.leagueservice.service.PlayerService;
import com.works.sharedlibrary.exceptions.BusinessValidationException;
import com.works.sharedlibrary.exceptions.InvalidFieldException;
import com.works.sharedlibrary.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Service
public class PlayerServiceImpl extends BaseService implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    static Predicate<Player> canPlay(Player player) {
        int age = DateUtil.getYear(new Date()) - DateUtil.getYear(player.getBirthDate());
        return p -> p.getTeamId() == 0 && age > 15 && age < 40;
    }

    @Override
    public Player save(final Player player) {
        validatePlayerToSave(player);
        return Optional.ofNullable(player)
                .map(Player::getPlayerId)
                .map(id -> this.update(player))
                .orElseGet(() -> playerRepository.save(player));

    }

    private void validatePlayerToSave(Player player) {
        Optional.ofNullable(player)
                .orElseThrow(() -> new InvalidFieldException("Player cannot be null"));

        Optional.of(player)
                .map(Player::getPlayerName)
                .map(name -> isNotBlank())
                .orElseThrow(invalidFieldSupplier("Player Name cannot be null!!"));

        Optional.of(player)
                .map(Player::getCareerStartDate)
                .orElseThrow(invalidFieldSupplier("Career Start Date cannot be null!!"));

        Optional.of(player)
                .map(PlayerServiceImpl::canPlay)
                .orElseThrow(() -> new BusinessValidationException(""));

        Optional.of(player)
                .map(Player::getBirthDate)
                .orElseThrow(invalidFieldSupplier("Birth Date cannot be null!!"));

        Optional.of(player)
                .map(Player::getTeamId)
                .orElseThrow(invalidFieldSupplier("Birth Date cannot be null!!"));

    }

    public Player update(Player player) {
        Player existingPlayer = playerRepository.findById()
    }

    @Override
    public void delete(long playerId) {
        playerRepository.disablePlayer(playerId, Constants.DEFAULT_FALSE_VALUE);
    }
}
