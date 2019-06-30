package com.works.sharedlibrary.domain.dto;

/**
 * @author mali.sahin
 * @since 2019-06-25.
 */
public class TeamDTO {
    public Long teamId;
    public String teamName;

    public TeamDTO deepCopy() {
        TeamDTO clone = new TeamDTO();
        clone.teamId = this.teamId;
        clone.teamName = this.teamName;
        return clone;
    }
}
