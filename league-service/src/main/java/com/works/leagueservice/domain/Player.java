package com.works.leagueservice.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Table(name = "player")
public class Player extends BaseEntity {

    @Id
    @Column(name = "player_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playerId;

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(name = "career_start_date", nullable = false)
    private Date careerStartDate;

    @Column(name = "team_id", nullable = false)
    private long teamId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team team;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;


    public long getPlayerId() {
        return this.playerId;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public Date getCareerStartDate() {
        return this.careerStartDate;
    }

    public long getTeamId() {
        return this.teamId;
    }

    public Team getTeam() {
        return this.team;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCareerStartDate(Date careerStartDate) {
        this.careerStartDate = careerStartDate;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Player)) return false;
        final Player other = (Player) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getPlayerId() != other.getPlayerId()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Player;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $playerId = this.getPlayerId();
        result = result * PRIME + (int) ($playerId >>> 32 ^ $playerId);
        return result;
    }
}
