package com.works.leagueservice.controller;

import com.works.leagueservice.mappingservice.PlayerMappingService;
import com.works.sharedlibrary.domain.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerMappingService playerMappingService;

    @PostMapping
    public ResponseEntity<PlayerDTO> createNewPlayer(@RequestBody PlayerDTO playerDTO) {
        return new ResponseEntity<>(playerMappingService.save(playerDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO playerDTO) {
        return new ResponseEntity<>(playerMappingService.save(playerDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{playerId}")
    public ResponseEntity deletePlayer(@PathVariable("playerId") Long playerId) {
        playerMappingService.delete(playerId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAll(@RequestParam int page, @RequestParam int size) {
        final Pageable pageable = PageRequest.of(page, size);
        List<PlayerDTO> playerList = playerMappingService.findAll(pageable);
        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }

}
