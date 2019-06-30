package com.works.leagueservice.service.impl;

import com.works.leagueservice.domain.Constants;
import com.works.leagueservice.domain.Player;
import com.works.leagueservice.repository.PlayerRepository;
import com.works.leagueservice.service.PlayerService;
import com.works.sharedlibrary.exceptions.InvalidFieldException;
import com.works.sharedlibrary.exceptions.ResourceNotFoundException;
import com.works.sharedlibrary.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    private static Predicate<Player> canPlay(Player player) {
        int age = DateUtil.getYear(new Date()) - player.getBirthYear();
        return p -> p.getTeamId() == 0 && age > 15 && age < 40;
    }

    @Override
    @Transactional
    public Player save(final Player player) {
        validatePlayerToSave(player);
        return Optional.ofNullable(player)
                .map(Player::getPlayerId)
                .filter(id -> id != 0)
                .map(id -> this.update(player))
                .orElseGet(() -> this.persist(player));

    }

    private void validatePlayerToSave(Player player) {
        Optional.ofNullable(player)
                .orElseThrow(() -> new InvalidFieldException("Player cannot be null"));

        Optional.of(player)
                .map(Player::getPlayerName)
                .map(name -> isNotBlank())
                .orElseThrow(invalidFieldSupplier("Player Name cannot be null!!"));


        Optional.of(player)
                .map(Player::getCareerStartYear)
                .orElseThrow(invalidFieldSupplier("Career Start Date cannot be null!!"));

  /*      Optional.of(player)
                .map(Player::isEligible)
                .orElseThrow(() -> new BusinessValidationException("Player has to play in a team!!!"));
*/
        Optional.of(player)
                .map(Player::getBirthYear)
                .orElseThrow(invalidFieldSupplier("Birth Date cannot be null!!"));

        Optional.of(player)
                .map(Player::getTeamId)
                .orElseThrow(invalidFieldSupplier("Birth Date cannot be null!!"));

    }

    private Player persist(Player player) {
        return this.playerRepository.save(player);
    }

    private Player update(Player player) {
        final Optional<Player> existingPlayer = playerRepository.findByPlayerIdAndIsActv(player.getPlayerId(), Constants.DEFAULT_VALID_VALUE);
        if (!existingPlayer.isPresent()) {
            throw new ResourceNotFoundException("Player is not found with id" + player.getPlayerId());
        }
        final Player exist = existingPlayer.get();
        exist.setTeamId(player.getTeamId());
        exist.setPlayerName(player.getPlayerName());
        exist.setBirthYear(player.getBirthYear());
        exist.setCareerStartYear(player.getCareerStartYear());
        return persist(exist);
    }

    @Override
    @Transactional
    public void delete(long playerId) {
        playerRepository.disablePlayer(playerId, Constants.DEFAULT_INVALID_VALUE);
    }

    @Override
    public Page<Player> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }
}
