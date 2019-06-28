package com.works.leagueservice.controller;

import com.works.sharedlibrary.domain.dto.PlayerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@RestController
@RequestMapping("/player")
public class PlayerController {

    @PostMapping
    public ResponseEntity<PlayerDTO> createNewPlayer(@RequestBody PlayerDTO playerDTO) {
        PlayerDTO player = new PlayerDTO();
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO playerDTO) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(name = "/{playerId}")
    public ResponseEntity deletePlayer(@PathVariable Long playerId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(name = "/{playerId}")
    public PlayerDTO getPlayer(@PathVariable long playerId) {
        return null;
    }
}
