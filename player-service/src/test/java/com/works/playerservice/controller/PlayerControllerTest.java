package com.works.playerservice.controller;

import com.google.gson.Gson;
import com.works.sharedlibrary.config.domain.dto.PlayerDTO;
import com.works.sharedlibrary.config.domain.dto.TeamDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author mali.sahin
 * @since 2019-06-25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PlayerController.class})
@WebAppConfiguration
public class PlayerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void addNewPlayer_GiveRequiredFields_ShouldCreateNewPlayer() throws Exception {
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