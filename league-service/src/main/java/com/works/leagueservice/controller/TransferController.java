package com.works.leagueservice.controller;

import com.works.leagueservice.mappingservice.TransferMappingService;
import com.works.sharedlibrary.domain.dto.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mali.sahin
 * @since 2019-06-30.
 */
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferMappingService transferMappingService;


    @PostMapping
    public ResponseEntity<TransferDTO> save(TransferDTO transferDTO) {
        return new ResponseEntity<>(transferMappingService.save(transferDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TransferDTO> update(TransferDTO transferDTO) {
        return new ResponseEntity<>(transferMappingService.update(transferDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{transferId}")
    public ResponseEntity delete(@PathVariable Long transferId) {
        transferMappingService.delete(transferId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
