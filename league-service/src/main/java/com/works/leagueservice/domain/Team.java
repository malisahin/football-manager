package com.works.leagueservice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Table(name = "team", schema = "football_manager")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Team extends BaseEntity {

    @Id
    @Column(name = "team_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "team")
    private List<Player> playerList;

}
