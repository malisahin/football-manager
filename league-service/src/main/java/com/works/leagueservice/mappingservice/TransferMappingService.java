package com.works.leagueservice.mappingservice;

import com.works.leagueservice.service.TransferService;
import com.works.sharedlibrary.domain.dto.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Service
public class TransferMappingService extends AbstractMappingService {
    @Autowired
    private TransferService transferServicel;

    public TransferDTO save(TransferDTO transferDTO) {
        return transferDTO;
    }

    public void delete(Long transderId) {

    }
}
