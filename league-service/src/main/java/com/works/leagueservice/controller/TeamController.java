package com.works.leagueservice.controller;

import com.works.leagueservice.mappingservice.TeamMappingService;
import com.works.sharedlibrary.domain.dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamMappingService teamMappingService;

    @PostMapping
    public ResponseEntity<TeamDTO> save(TeamDTO teamDTO) {
        return new ResponseEntity<>(teamMappingService.save(teamDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TeamDTO> update(TeamDTO teamDTO) {
        return new ResponseEntity<>(teamMappingService.save(teamDTO), HttpStatus.OK);
    }

    @DeleteMapping("/teamId")
    public ResponseEntity delete(@PathVariable Long teamId) {
        teamMappingService.delete(teamId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/playerId")
    public ResponseEntity<List<TeamDTO>> getPlayerTeamHistory(@PathVariable Long playerId) {
        return new ResponseEntity<>(teamMappingService.getPlayerTeamHistory(playerId), HttpStatus.OK);
    }

}
