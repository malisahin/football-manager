package com.works.sharedlibrary.domain.dto;

/**
 * @author mali.sahin
 * @since 2019-06-25.
 */

public class PlayerDTO {
    public Long playerId;
    public String playerName;
    public int careerStartYear;
    public TeamDTO team;
    public long teamId;
    public int birthYear;

    public PlayerDTO deepCopy() {
        PlayerDTO clone = new PlayerDTO();
        clone.playerId = this.playerId;
        clone.teamId = this.teamId;
        clone.team = this.team;
        clone.careerStartYear = this.careerStartYear;
        clone.birthYear = this.birthYear;
        clone.playerName = this.playerName;
        return clone;
    }
}
