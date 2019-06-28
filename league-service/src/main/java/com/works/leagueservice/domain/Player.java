package com.works.leagueservice.domain;

import com.works.sharedlibrary.util.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.function.Predicate;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Table(name = "player")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Data
public class Player extends BaseEntity {

    @Id
    @Column(name = "player_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playerId;

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(name = "career_start_year", nullable = false)
    private int careerStartYear;

    @Column(name = "team_id", nullable = false)
    private long teamId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team team;

    @Column(name = "birth_year", nullable = false)
    private int birthYear;

    public Predicate<Player> isEligible() {
        return s -> s.getAge() > 18 && s.getAge() < 40;
    }

    public int getAge() {
        return DateUtil.getYear(new Date()) - this.birthYear;
    }

}
