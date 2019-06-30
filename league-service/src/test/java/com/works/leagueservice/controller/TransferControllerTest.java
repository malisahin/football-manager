package com.works.leagueservice.controller;

import com.works.leagueservice.AbstractTestConfig;
import com.works.leagueservice.mappingservice.TransferMappingService;
import com.works.leagueservice.test_domain.TransferDomainProvider;
import com.works.sharedlibrary.domain.dto.TransferDTO;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author mali.sahin
 * @since 2019-06-30.
 */
@WebMvcTest(TransferController.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TransferControllerTest extends AbstractTestConfig {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferMappingService transferMappingService;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addNewTransfer_whenGiveRequiredFields_thenShouldSaveTransfer() throws Exception {
        // given
        TransferDTO transferDTO = TransferDomainProvider.withRequiredFields();

        //mock
        when(transferMappingService.save(transferDTO)).thenReturn(transferDTO);

        //action
        final ResultActions resultActions = mockMvc.perform(
                post("/transfer")
                        .accept(DEFAULT_MEDIA_TYPE)
                        .contentType(DEFAULT_MEDIA_TYPE)
                        .content(gson.toJson(transferDTO))
        );

        //verify
        resultActions.andExpect(status().isCreated());
        verify(transferMappingService, times(1)).save(transferDTO);
    }

    @Test
    public void updateTransfer_whenGiveRequiredFields_thenShouldUpdateTransfer() throws Exception {
        // given
        TransferDTO transferDTO = TransferDomainProvider.withRequiredFields();

        //mock
        when(transferMappingService.save(transferDTO)).thenReturn(transferDTO);

        //action
        final ResultActions resultActions = mockMvc.perform(
                put("/transfer")
                        .accept(DEFAULT_MEDIA_TYPE)
                        .contentType(DEFAULT_MEDIA_TYPE)
                        .content(gson.toJson(transferDTO))
        );

        //verify
        resultActions.andExpect(status().isOk());
        verify(transferMappingService, times(1)).save(transferDTO);
    }

    @Test
    public void deleteTransfer_whenGiveTransferId_thenShouldCancelTransfer() throws Exception {
        // give
        final Long transferId = 1L;

        //mock

        //action
        final ResultActions resultActions = mockMvc.perform(
                delete("/transfer/" + transferId)
                        .accept(DEFAULT_MEDIA_TYPE)
                        .contentType(DEFAULT_MEDIA_TYPE)
        );

        //verify
        resultActions.andExpect(status().isOk());
        verify(transferMappingService, times(1)).delete(transferId);
    }
}