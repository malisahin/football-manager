package com.works.sharedlibrary.domain.dto;

/**
 * @author mali.sahin
 * @since 2019-06-25.
 */
public class TransferDTO {
    public long transferId;
    public TeamDTO departure;
    public TeamDTO destination;
    public PlayerDTO player;
    public int transferYear;

    public double getTransferCost() {
        final int experienceByMonth = (transferYear - this.player.careerStartYear) * 12;
        final int age = this.transferYear - this.player.birthYear;
        return (double) (experienceByMonth * 100000) / age * 1.0;
    }

    public double getTeamCommission() {
        return this.getTransferCost() * 0.1;
    }

    public double getContractValue() {
        return this.getTransferCost() * 1.1;
    }
}
