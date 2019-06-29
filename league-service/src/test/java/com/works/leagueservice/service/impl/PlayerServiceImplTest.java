package com.works.leagueservice.service.impl;

import com.works.leagueservice.domain.Constants;
import com.works.leagueservice.domain.Player;
import com.works.leagueservice.domain.Team;
import com.works.leagueservice.repository.PlayerRepository;
import com.works.leagueservice.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author mali.sahin
 * @since 2019-06-29.
 */
@WebMvcTest(PlayerServiceImpl.class)
@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    private Player playerWithRequiredFields;
    private Team teamWithRequiredFields;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        teamWithRequiredFields = new Team();
        teamWithRequiredFields.setTeamName("Manchester");
        teamWithRequiredFields.setTeamId(1L);

        playerWithRequiredFields.setPlayerName("Ronaldo");
        playerWithRequiredFields.setBirthYear(1983);
        playerWithRequiredFields.setCareerStartYear(2000);
        playerWithRequiredFields.setTeamId(teamWithRequiredFields.getTeamId());
    }


    @Test
    public void insertNewPlayer_whenGiveRequiredFields_thenShouldInsertPlayer() {
        // given
        Player player = playerWithRequiredFields.deepCopy();
        player.setPlayerId(null); // to insert
        Player insertedPlayer = player.deepCopy();
        insertedPlayer.setIsActv(Constants.DEFAULT_VALID_VALUE);
        insertedPlayer.setCreatedAt(new Date());
        insertedPlayer.setCreateUser("admin");

        //mock
        playerService.save(player);

        //action
        when(playerRepository.save(player)).thenReturn(insertedPlayer);

        //test
        Assert.notNull(player.getPlayerId(), "Player Id is null");
        Assert.notNull(player.getCreateUser(), "Player Create User is null");
        verify(playerRepository, times(1))
                .save(player);
    }

    @Test
    public void updateExistingPlayer_whenGiveRequiredFields_thenShouldUpdatePlayer() {
        // given
        Player player = playerWithRequiredFields.deepCopy();
        Player updatedPlayer = player.deepCopy();
        updatedPlayer.setPlayerName(player.getPlayerName() + "X");
        updatedPlayer.setUpdatedAt(new Date());
        updatedPlayer.setUpdateUser("admin");

        //mock
        playerService.save(player);

        //action
        when(playerRepository.save(player)).thenReturn(updatedPlayer);

        //test
        assertEquals(player.getPlayerId(), updatedPlayer.getPlayerId());
        Assert.notNull(player.getUpdateUser(), "Update User is null");
        assertEquals(player.getPlayerName() + "X", player.getPlayerName());
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    public void delete() {
    }

    @Test
    public void search() {
    }
}