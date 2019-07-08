package com.works.leagueservice.service.impl;

import com.works.leagueservice.domain.Constants;
import com.works.leagueservice.domain.Player;
import com.works.leagueservice.domain.Team;
import com.works.leagueservice.repository.PlayerRepository;
import com.works.leagueservice.service.PlayerService;
import com.works.sharedlibrary.exceptions.InvalidFieldException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author mali.sahin
 * @since 2019-06-29.
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest extends AbstractTestService {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService = new PlayerServiceImpl();

    private Player playerWithRequiredFields;
    private Team teamWithRequiredFields;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        teamWithRequiredFields = new Team();
        teamWithRequiredFields.setTeamName("Manchester");
        teamWithRequiredFields.setTeamId(1L);

        playerWithRequiredFields = new Player();
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
        insertedPlayer.setPlayerId(1L);
        insertedPlayer.setIsActv(Constants.DEFAULT_VALID_VALUE);
        insertedPlayer.setCreatedAt(new Date());
        insertedPlayer.setCreateUser("admin");

        //mock
        when(playerRepository.save(player)).thenReturn(insertedPlayer);

        //action
        final Player resultPlayer = playerService.save(player);

        //test
        Assert.notNull(resultPlayer.getPlayerId(), "Player Id is null");
        Assert.notNull(resultPlayer.getCreateUser(), "Player Create User is null");
        verify(playerRepository, times(1))
                .save(player);
    }


    @Test()
    public void insertNewPlayer_whenGiveNullPlayer_thenShouldThrowException() {

        // verify
        thrown.expect(InvalidFieldException.class);
        thrown.expectMessage("Player cannot be null");

        //action
        playerService.save(null);
    }

    @Test
    public void insertNewPlayer_whenGiveNullName_thenShouldThrowException() {
        // given
        final Player player = playerWithRequiredFields.deepCopy();
        player.setPlayerName(null);

        // verify
        thrown.expect(InvalidFieldException.class);
        thrown.expectMessage("Player Name cannot be null!!");

        //action
        playerService.save(player);
    }

    @Test
    public void insertNewPlayer_whenGiveNullCareerStartYear_thenShouldThrowException() {
        // given
        final Player player = playerWithRequiredFields.deepCopy();
        player.setPlayerName("Ronaldo");
        player.setCareerStartYear(0);

        // verify
        thrown.expect(InvalidFieldException.class);
        thrown.expectMessage("Career Start Year cannot be null!!");

        //action
        playerService.save(player);
    }

    @Test
    public void insertNewPlayer_whenGiveNullBirthYear_thenShouldThrowException() {
        // given
        final Player player = playerWithRequiredFields.deepCopy();
        player.setPlayerName("Ronaldo");
        player.setCareerStartYear(1990);
        player.setBirthYear(0);

        // verify
        thrown.expect(InvalidFieldException.class);
        thrown.expectMessage("Birth Year cannot be null!!");

        //action
        playerService.save(player);
    }

    @Test
    public void insertNewPlayer_whenGiveNullTeamId_thenShouldThrowException() {
        // given
        final Player player = playerWithRequiredFields.deepCopy();
        player.setPlayerName("Ronaldo");
        player.setCareerStartYear(1990);
        player.setBirthYear(1970);
        player.setTeamId(null);

        // verify
        thrown.expect(InvalidFieldException.class);
        thrown.expectMessage("Team cannot be null!!");

        //action
        playerService.save(player);
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
        when(playerRepository.save(player)).thenReturn(updatedPlayer);

        //action
        final Player resultPlayer = playerService.save(player);

        //test
        assertEquals(resultPlayer.getPlayerId(), updatedPlayer.getPlayerId());
        Assert.notNull(resultPlayer.getUpdateUser(), "Update User is null");
        assertEquals(resultPlayer.getPlayerName(), player.getPlayerName() + "X");
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    public void deleteExistingPlayer_whenGivePlayerId_thenShouldDeletePlayer() {
        // given
        final Long playerId = 1L;

        // mock
        doNothing().when(playerRepository).disablePlayer(playerId, Constants.DEFAULT_INVALID_VALUE);

        // action
        playerService.delete(playerId);

        //verify
        verify(playerRepository, times(1)).disablePlayer(playerId, Constants.DEFAULT_INVALID_VALUE);

    }

    @Test
    public void searchWithPageable_whenGiveRequiredFields_thenShouldGiveRelatedResult() {
        // given
        final PageRequest pageRequest = PageRequest.of(0, 5);

        // mock
        final List<Player> playerList = new ArrayList<>();
        playerList.add(new Player());
        playerList.add(new Player());
        playerList.add(new Player());
        final Page<Player> playerPage = new PageImpl<>(playerList);
        doReturn(playerPage).when(playerRepository).findAll(pageRequest);

        // action
        final Page<Player> result = playerService.findAll(pageRequest);

        // verify
        assertEquals(result.getTotalElements(), playerPage.getTotalElements());
        verify(playerRepository, times(1)).findAll(pageRequest);

    }
}