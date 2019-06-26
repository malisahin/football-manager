package com.works.leagueservice.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Table(name = "team")
public class Team extends BaseEntity {

    @Id
    @Column(name = "team_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teamId;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "team")
    private List<Player> playerList;


    public long getTeamId() {
        return this.teamId;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public List<Player> getPlayerList() {
        return this.playerList;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Team)) return false;
        final Team other = (Team) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getTeamId() != other.getTeamId()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Team;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $teamId = this.getTeamId();
        result = result * PRIME + (int) ($teamId >>> 32 ^ $teamId);
        return result;
    }
}
