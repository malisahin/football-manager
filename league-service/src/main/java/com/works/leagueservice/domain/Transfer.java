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
public class Transfer extends BaseEntity implements Comparable<Transfer> {

    @Id
    @Column(name = "transfer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    @Column(name = "departure_team_id", nullable = false, updatable = false, insertable = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "departure_team_id")
    private Team departure;

    @Column(name = "departure_team_id", nullable = false)
    private Long departureTeamId;

    @Column(name = "terminal_team_id", nullable = false, updatable = false, insertable = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "terminal_team_id")
    private Team terminal;

    @Column(name = "terminal_team_id", nullable = false)
    private Long terminalTeamId;

    @Column(name = "player_id", nullable = false, updatable = false, insertable = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    @Basic
    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "transfer_year", nullable = false)
    private int transferYear;

    @Override
    public int compareTo(Transfer o) {
        return this.getTransferYear() - o.getTransferYear();
    }
}
