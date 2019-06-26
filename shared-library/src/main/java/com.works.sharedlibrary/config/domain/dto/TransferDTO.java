package com.works.sharedlibrary.config.domain.dto;

import com.works.sharedlibrary.util.DateUtil;

import java.util.Date;

/**
 * @author mali.sahin
 * @since 2019-06-25.
 */
public class TransferDTO {
    public long transferId;
    public TeamDTO departure;
    public TeamDTO destination;
    public PlayerDTO player;
    public Date transferDate;

    public double getTransferCost() {
        final int transferYear = DateUtil.getYear(this.transferDate);
        final int careerBeginningYear = DateUtil.getYear(this.player.careerBeginningDate);
        final int birthYear = DateUtil.getYear(this.player.birthDate);
        final int experienceByMonth = (transferYear - careerBeginningYear) * 12;
        final int age = transferYear - birthYear;
        return (double) (experienceByMonth * 100000) / age;
    }

    public double getTeamCommission() {
        return this.getTransferCost() * 0.1;
    }

    public double getContractValue() {
        return this.getTransferCost() * 1.1;
    }
}
