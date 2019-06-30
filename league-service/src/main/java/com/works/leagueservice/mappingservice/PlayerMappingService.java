package com.works.leagueservice.mappingservice;

import com.works.leagueservice.domain.Player;
import com.works.leagueservice.service.PlayerService;
import com.works.sharedlibrary.domain.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PlayerDTO> findAll(Pageable pageable) {
        return playerService.findAll(pageable)
                .get()
                .map(player -> mapper.map(player, PlayerDTO.class))
                .collect(Collectors.toList());
    }

}
