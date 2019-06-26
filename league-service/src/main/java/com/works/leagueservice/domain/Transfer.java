package com.works.leagueservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Table(name = "transfer")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Data
public class Transfer extends BaseEntity{

    @Id
    @Column(name = "transfer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transferId;
/*
    @Column(name = "departure_team_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "departure_team_id")
    private Team departure;

    @Column(name = "terminal_team_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "terminal_team_id")
    private Team terminal;

    @Column(name = "player_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    @Column(name = "transfer_date", nullable = false)
    private Date transferDate;*/
}
