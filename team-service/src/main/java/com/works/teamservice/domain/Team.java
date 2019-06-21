package com.works.teamservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author mali.sahin
 * @since 2019-06-21.
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"teamId"})
@Table(name = "team")
public class Team {

    @Id
    @Column(name = "team_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teamId;

    @Column(name = "team_name", nullable = false)
    private String teamName;
}
