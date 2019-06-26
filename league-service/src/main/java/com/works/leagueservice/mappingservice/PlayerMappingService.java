package com.works.leagueservice.mappingservice;

import com.works.leagueservice.domain.Player;
import com.works.leagueservice.service.PlayerService;
import com.works.sharedlibrary.domain.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */

@Service
public class PlayerMappingService extends AbstractMappingService {

    @Autowired
    private PlayerService playerService;

    public PlayerDTO save(PlayerDTO playerDTO) {
        Player player = mapper.map(playerDTO, Player.class);
        player = playerService.save(player);
        return mapper.map(player, PlayerDTO.class);
    }

    public void delete(long playerId) {
        playerService.delete(playerId);
    }
}
