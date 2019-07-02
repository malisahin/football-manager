package com.works.leagueservice.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Table(name = "transfer", schema = "football_manager")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer extends BaseEntity implements Comparable<Transfer> {

    @Id
    @Column(name = "transfer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_team_id", referencedColumnName = "team_id", insertable = false, updatable = false)
    private Team departure;

    @Column(name = "departure_team_id", nullable = false)
    private Long departureTeamId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "terminal_team_id", referencedColumnName = "team_id", insertable = false, updatable = false)
    private Team terminal;

    @Column(name = "terminal_team_id", nullable = false)
    private Long terminalTeamId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id", insertable = false, updatable = false)
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
