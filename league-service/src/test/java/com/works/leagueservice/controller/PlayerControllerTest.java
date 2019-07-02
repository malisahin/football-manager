package com.works.leagueservice.controller;

import com.works.leagueservice.AbstractTestConfig;
import com.works.leagueservice.mappingservice.PlayerMappingService;
import com.works.leagueservice.service.TeamService;
import com.works.leagueservice.test_domain.PlayerDomainProvider;
import com.works.sharedlibrary.domain.dto.PlayerDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */

@WebMvcTest(PlayerController.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerControllerTest extends AbstractTestConfig {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TeamService teamService;

    @MockBean
    private PlayerMappingService playerMappingService;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addNewPlayer_whenGiveRequiredFields_thenShouldCreateNewPlayer() throws Exception {
        // given
        final PlayerDTO playerDTO = PlayerDomainProvider.withRequiredFields();
        playerDTO.playerId = null;  // to insert

        final PlayerDTO insertedPlayer = playerDTO.deepCopy();
        insertedPlayer.playerId = 1L;
        // mock
        when(playerMappingService.save(any(PlayerDTO.class))).thenReturn(insertedPlayer);

        //action
        final ResultActions resultActions = mockMvc.perform(
                post("/player")
                        .accept(DEFAULT_MEDIA_TYPE)
                        .contentType(DEFAULT_MEDIA_TYPE)
                        .content(gson.toJson(playerDTO))
        );

        //verify
        resultActions.andExpect(status().isCreated());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        PlayerDTO resultPlayer = gson.fromJson(responseContent, PlayerDTO.class);
        assertEquals(resultPlayer.playerId, insertedPlayer.playerId);
        verify(playerMappingService, times(1))
                .save(any(PlayerDTO.class));
    }

    @Test
    public void updateExistingPlayer_whenGiveNewFields_thenShouldUpdateOldValues() throws Exception {
        // given
        final PlayerDTO player = PlayerDomainProvider.withRequiredFields();
        player.playerId = 1L;  // to update

        final PlayerDTO updatedPlayer = player.deepCopy();
        updatedPlayer.playerId = 1L;
        updatedPlayer.playerName = player.playerName + "X";
        // mock
        when(playerMappingService.save(player)).thenReturn(updatedPlayer);

        final ResultActions resultActions = mockMvc.perform(
                put("/player")
                        .accept(DEFAULT_MEDIA_TYPE)
                        .contentType(DEFAULT_MEDIA_TYPE)
                        .content(gson.toJson(player))
        );
        resultActions.andExpect(status().isOk());
        assertEquals(updatedPlayer.playerName, player.playerName + "X");
        verify(playerMappingService, times(1))
                .save(any(PlayerDTO.class));
    }

    @Test
    public void deletePlayer_givePlayerId_thenShouldDeletePlayer() throws Exception {
        //given
        final Long playerId = 1L;

        //mock
        //when(playerMappingService.delete(playerId));

        //action
        final ResultActions resultActions = mockMvc.perform(delete("/player/1")
                .accept(DEFAULT_MEDIA_TYPE)
                .contentType(DEFAULT_MEDIA_TYPE));

        //verify
        resultActions.andExpect(status().isOk());
        verify(playerMappingService, times(1))
                .delete(playerId);
    }

    @Test
    public void findAllPlayers_givePageableParams_thenShouldGivePlayers() throws Exception {
        //give
        final PageRequest pageRequest = PageRequest.of(0, 5);

        //mock
        when(playerMappingService.findAll(pageRequest)).thenReturn(new ArrayList<>());

        //action
        final ResultActions resultActions = mockMvc.perform(
                get("/player?page=0&size=5")
                        .accept(DEFAULT_MEDIA_TYPE)
                        .contentType(DEFAULT_MEDIA_TYPE));

        //verify
        resultActions.andExpect(status().isOk());
        verify(playerMappingService, times(1))
                .findAll(pageRequest);
    }
}