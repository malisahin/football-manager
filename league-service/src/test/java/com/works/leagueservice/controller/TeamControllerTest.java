package com.works.leagueservice.controller;

import com.works.leagueservice.AbstractTestConfig;
import com.works.leagueservice.mappingservice.TeamMappingService;
import com.works.leagueservice.test_domain.TeamDomainProvider;
import com.works.sharedlibrary.domain.dto.TeamDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author mali.sahin
 * @since 2019-06-30.
 */
@WebMvcTest(TeamController.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TeamControllerTest extends AbstractTestConfig {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamMappingService teamMappingService;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void saveNewTeam_giveRequiredField_thenShouldSaveGivenTeam() throws Exception {
        // give
        TeamDTO teamDTO = TeamDomainProvider.withRequiredFields();
        teamDTO.teamId = null;  // to save

        TeamDTO insertedTeam = teamDTO.deepCopy();
        insertedTeam.teamId = 1L;

        //mock
        when(teamMappingService.save(teamDTO)).thenReturn(insertedTeam);

        //action
        final ResultActions resultActions = mockMvc.perform(
                post("/team")
                        .accept(DEFAULT_MEDIA_TYPE)
                        .contentType(DEFAULT_MEDIA_TYPE)
                        .content(gson.toJson(teamDTO))
        );
        resultActions.andExpect(status().isCreated());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        TeamDTO resultTeam = gson.fromJson(responseContent, TeamDTO.class);
        assertEquals(resultTeam.teamId, insertedTeam.teamId);
        verify(teamMappingService, times(1))
                .save(teamDTO);
    }

    @Test
    public void updateTeam_giveRequiredField_thenShouldUpdateTeam() throws Exception {
        // give
        TeamDTO teamDTO = TeamDomainProvider.withRequiredFields();
        teamDTO.teamId = 1L;  // to save
        teamDTO.teamName = "Manchester";

        TeamDTO insertedTeam = teamDTO.deepCopy();
        insertedTeam.teamId = 1L;
        insertedTeam.teamName = "Chelsea";
        //mock
        when(teamMappingService.update(any(TeamDTO.class))).thenReturn(insertedTeam);

        //action
        final ResultActions resultActions = mockMvc.perform(
                put("/team")
                        .accept(DEFAULT_MEDIA_TYPE)
                        .contentType(DEFAULT_MEDIA_TYPE)
                        .content(gson.toJson(teamDTO))
        );
        resultActions.andExpect(status().isOk());
        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        TeamDTO resultTeam = gson.fromJson(responseContent, TeamDTO.class);
        assertEquals(resultTeam.teamId, insertedTeam.teamId);
        verify(teamMappingService, times(1)).update(any(TeamDTO.class));
    }


    @Test
    public void delete() throws Exception {
        // given
        final Long teamId = 1L;

        // mock
        doNothing().when(teamMappingService).delete(teamId);

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/team/" + teamId)
                        .accept(DEFAULT_MEDIA_TYPE)
                        .contentType(DEFAULT_MEDIA_TYPE)
        );

        resultActions.andExpect(status().isOk());
        verify(teamMappingService, times(1)).delete(teamId);
    }

    @Test
    public void getPlayerTeamHistory_givePlayerId_thenShouldReturnTransferList() throws Exception {
        // give
        final Long playerId = 1L;

        final List<TeamDTO> teamDTOList = new ArrayList<>();
        teamDTOList.add(new TeamDTO());
        teamDTOList.add(new TeamDTO());
        teamDTOList.add(new TeamDTO());

        //mock
        when(teamMappingService.getPlayerTeamHistory(playerId)).thenReturn(teamDTOList);

        // action
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/team/" + playerId)
                        .accept(DEFAULT_MEDIA_TYPE)
                        .contentType(DEFAULT_MEDIA_TYPE)
        );

        // verify
        resultActions.andExpect(status().isOk());
        verify(teamMappingService, timeout(1)).getPlayerTeamHistory(playerId);
    }
}