package com.works.leagueservice.domain;

import com.works.sharedlibrary.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.function.Predicate;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Table(name = "player", schema = "football_manager")
@Entity
@Getter
@Setter
public class Player extends BaseEntity {

    @Id
    @Column(name = "player_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(name = "career_start_year", nullable = false)
    private int careerStartYear;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", updatable = false, insertable = false)
    private Team team;

    @Column(name = "birth_year", nullable = false)
    private int birthYear;

    public Predicate<Player> isEligible() {
        return s -> s.getAge() > 18 && s.getAge() < 40;
    }

    public int getAge() {
        return DateUtil.getYear(new Date()) - this.birthYear;
    }

    public Player deepCopy() {
        Player clone = new Player();
        clone.setPlayerId(this.playerId);
        clone.setTeamId(this.getTeamId());
        clone.setTeam(this.team);
        clone.setCareerStartYear(this.getCareerStartYear());
        clone.setBirthYear(this.getBirthYear());
        clone.setCreatedAt(this.getCreatedAt());
        clone.setCreateUser(this.getCreateUser());
        clone.setUpdatedAt(this.getUpdatedAt());
        clone.setUpdateUser(this.getUpdateUser());
        clone.setIsActv(this.getIsActv());
        return clone;
    }

}
