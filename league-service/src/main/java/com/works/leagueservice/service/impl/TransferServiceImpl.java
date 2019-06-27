package com.works.leagueservice.service.impl;

import com.works.leagueservice.domain.Transfer;
import com.works.leagueservice.repository.TransferRepository;
import com.works.leagueservice.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Service
public class TransferServiceImpl extends BaseService implements TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public Transfer save(Transfer transfer) {
        return this.persist(transfer);
    }

    private Transfer persist(Transfer transfer) {
        return this.save(transfer);
    }
}
