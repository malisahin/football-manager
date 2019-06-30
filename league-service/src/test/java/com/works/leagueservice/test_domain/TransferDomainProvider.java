package com.works.leagueservice.test_domain;

import com.works.sharedlibrary.domain.dto.TransferDTO;

/**
 * @author mali.sahin
 * @since 2019-06-30.
 */
public class TransferDomainProvider {

    public static TransferDTO withRequiredFields() {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.player = PlayerDomainProvider.withRequiredFields();
        transferDTO.transferYear = 2002;
        transferDTO.destination = TeamDomainProvider.destinationTeam();
        transferDTO.departure = TeamDomainProvider.departureTeam();
        return transferDTO;
    }
}
