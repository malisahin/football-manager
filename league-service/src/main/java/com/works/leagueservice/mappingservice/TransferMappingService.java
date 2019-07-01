package com.works.leagueservice.mappingservice;

import com.works.leagueservice.domain.Transfer;
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
    private TransferService transferService;

    public TransferDTO save(TransferDTO transferDTO) {
        final Transfer transfer = mapper.map(transferDTO, Transfer.class);
        final Transfer savedTransfer = transferService.save(transfer);
        return mapper.map(savedTransfer, TransferDTO.class);
    }

    public void delete(Long transferId) {
        transferService.delete(transferId);
    }
}
