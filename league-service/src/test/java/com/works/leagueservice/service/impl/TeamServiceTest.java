package com.works.leagueservice.service.impl;

import com.works.leagueservice.domain.Constants;
import com.works.leagueservice.domain.Team;
import com.works.leagueservice.repository.TeamRepository;
import com.works.leagueservice.service.TeamService;
import com.works.sharedlibrary.exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author mali.sahin
 * @since 2019-07-08.
 */
@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest extends AbstractTestService {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService = new TeamServiceImpl();

    @Before
    public void setUp() {

    }

    @Test
    public void saveTeam_giveRequiredFields_thenShouldSaveTeam() {
        // given
        final Team team = new Team();
        team.setTeamId(null);
        team.setTeamName("Real Madrid");

        final Team savedTeam = new Team();
        savedTeam.setTeamId(1L);
        savedTeam.setTeamName(team.getTeamName());
        savedTeam.setCreatedAt(new Date());

        // mock
        doReturn(savedTeam).when(teamRepository).save(team);

        //action
        final Team resultTeam = teamService.save(team);

        // verify
        assertNotNull(resultTeam);
        assertEquals(resultTeam.getTeamId(), savedTeam.getTeamId());
        assertNotNull(resultTeam.getCreatedAt());
        verify(teamRepository, times(1)).save(team);
    }

    @Test
    public void updateTeam_giveRequiredFields_thenShouldSaveUpdate() {
        // given
        final Team team = new Team();
        team.setTeamId(1L);
        team.setTeamName("Real Madrid");

        final Team updatedTeam = new Team();
        updatedTeam.setTeamName(team.getTeamName() + "X");
        updatedTeam.setUpdatedAt(new Date());

        final Optional<Team> optionalTeam = Optional.of(updatedTeam);

        // mock
        doReturn(updatedTeam).when(teamRepository).save(team);
        doReturn(optionalTeam).when(teamRepository).findByTeamIdAndIsActv(team.getTeamId(), Constants.DEFAULT_VALID_VALUE);

        //action
        final Team resultTeam = teamService.save(team);

        // verify
        assertNotNull(resultTeam);
        assertEquals(resultTeam.getTeamId(), updatedTeam.getTeamId());
        assertNotNull(resultTeam.getUpdatedAt());
        verify(teamRepository, times(1)).save(team);
    }

    @Test
    public void updateNotExistTeam_giveNotExistId_thenShouldThrowException() {

        //given
        final Team team = new Team();
        team.setTeamId(2L);
        final Optional<Team> optionalTeam = Optional.empty();
        //mock
        doReturn(optionalTeam).when(teamRepository).findByTeamIdAndIsActv(team.getTeamId(), Constants.DEFAULT_VALID_VALUE);

        thrown.expect(ResourceNotFoundException.class);
        thrown.expectMessage("Team is not found with id " + team.getTeamId());

        // action
        teamService.update(team);
    }

    @Test
    public void deleteTeam_giveTeamId_thenShouldSetDisabled() {
       // given
        final long teamId = 1L;

        //mock
        doNothing().when(teamRepository).disableTeam(teamId, Constants.DEFAULT_INVALID_VALUE);

        // action
        this.teamService.delete(teamId);

        // verify
        verify(teamRepository, times(1)).disableTeam(teamId, Constants.DEFAULT_INVALID_VALUE);
    }
}
