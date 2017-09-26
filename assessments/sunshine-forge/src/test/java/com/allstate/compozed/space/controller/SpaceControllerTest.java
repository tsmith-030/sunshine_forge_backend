package com.allstate.compozed.space.controller;

import com.allstate.compozed.space.repository.Space;
import com.allstate.compozed.space.service.SpaceService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class SpaceControllerTest {

    @Mock
    private SpaceService service;

    @InjectMocks
    private SpaceController controller;

    private MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void post_createSpace_createsNewSpace() throws Exception {

        Space space = Space.builder().build();

        when(service.createSpace(any(Space.class))).thenReturn(space);

        mockMvc.perform(post("/api/spaces/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(space)))
                .andExpect(jsonPath("id").value(space.getId()))
                .andExpect(status().isCreated());

        verify(service).createSpace(any(Space.class));
    }

    @Test
    public void get_listSpaces_returnsListOfAllSpaces() throws Exception {

        Space spaceOne = Space.builder()
                .id(0)
                .build();
        Space spaceTwo = Space.builder()
                .id(1)
                .build();

        List<Integer> spaceList = Arrays.asList(spaceOne.getId(), spaceTwo.getId());

        when(service.listSpaces()).thenReturn(spaceList);

        mockMvc.perform(get("/api/spaces/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(status().isOk());

        verify(service).listSpaces();
    }

    @Test
    public void get_getSpace_returnsSpaceWithFields() throws Exception {
        Space space = Space.builder()
                .id(1)
                .disk_quotamb(2)
                .memory_quotamb(3)
                .name("Test Space")
                .build();

        when(service.getSpace(anyInt())).thenReturn(space);

        mockMvc.perform(get("/api/spaces/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("disk_quotamb").value(2))
                .andExpect(jsonPath("memory_quotamb").value(3))
                .andExpect(jsonPath("name").value("Test Space"))
                .andExpect(status().isOk());

        verify(service).getSpace(anyInt());
    }
}