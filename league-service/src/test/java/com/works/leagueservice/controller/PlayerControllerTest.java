package com.works.leagueservice.controller;

import com.google.gson.Gson;
import com.works.sharedlibrary.config.domain.dto.PlayerDTO;
import com.works.sharedlibrary.config.domain.dto.TeamDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PlayerController.class)
@WebAppConfiguration
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {

    }

    @Test
    public void addNewPlayer_whenGiveRequiredFields_thenShouldCreateNewPlayer() throws Exception {
        PlayerDTO player = new PlayerDTO();
        player.playerName = "Ronaldo";
        TeamDTO team = new TeamDTO();
        team.teamName = "Manchester United";
        player.team = team;

        String content = new Gson().toJson(player);
        mockMvc.perform(
                post("/player")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content)
        ).andExpect(status().isCreated());
    }
}