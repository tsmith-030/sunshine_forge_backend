package com.allstate.compozed.space.controller;

import com.allstate.compozed.space.repository.Space;
import com.allstate.compozed.space.repository.SpaceRepository;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class SpaceControllerIT {

    @Autowired
    MockMvc mvc;

    @Autowired
    SpaceRepository repository;


    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception {

        Space space = Space.builder().build();

        MockHttpServletRequestBuilder request = post("/api/spaces")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(space));

        this.mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)));
    }

    @Test
    @Transactional
    @Rollback
    public void testList() throws Exception {
        Space spaceOne = Space.builder()
                .build();
        Space spaceTwo = Space.builder()
                .build();

        repository.save(spaceOne);
        repository.save(spaceTwo);

        MockHttpServletRequestBuilder request = get("/api/spaces")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(spaceOne.getId()))
                .andExpect(jsonPath("$[1].id").value(spaceTwo.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void testGet() throws Exception {
        Space space = new Space();
        space.setName("Pivotal");
        repository.save(space);

        MockHttpServletRequestBuilder request = get("/api/spaces/1")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(space.getId())));
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdate() throws Exception {
        Space space = new Space();
        space.setName("Test");
        space.setDisk_quotamb(2);
        space.setMemory_quotamb(4);
        repository.save(space);

        Space updatedSpace = Space.builder()
                .id(space.getId())
                .disk_quotamb(2)
                .memory_quotamb(4)
                .name("Updated")
                .build();

        MockHttpServletRequestBuilder request = post("/api/spaces/{id}", updatedSpace.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(updatedSpace));

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(space.getId())))
                .andExpect(jsonPath("$.disk_quotamb", equalTo(space.getDisk_quotamb())))
                .andExpect(jsonPath("$.memory_quotamb", equalTo(space.getMemory_quotamb())))
                .andExpect(jsonPath("$.name", equalTo(updatedSpace.getName())));
    }

    @Test
    @Transactional
    @Rollback
    public void testDelete() throws Exception {
        Space space = new Space();
        space.setName("Test");
        space.setDisk_quotamb(2);
        space.setMemory_quotamb(4);
        repository.save(space);

        MockHttpServletRequestBuilder request = delete("/api/spaces/{id}", space.getId())
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk());

        assertFalse(repository.exists(space.getId()));
    }
}
