package com.works.leagueservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "career_start_date", nullable = false)
    private Date careerStartDate;

    @Column(name = "team_id", nullable = false)
    private long teamId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team team;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
}
