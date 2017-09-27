package com.allstate.compozed.space.service;

import com.allstate.compozed.space.repository.Space;
import com.allstate.compozed.space.repository.SpaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpaceServiceTest {

    @Mock
    SpaceRepository repository;

    @InjectMocks
    SpaceService service;

    @Test
    public void createSpace_createsASpace_GivenSpace() throws Exception {
        Space space = Space.builder().build();

        when(repository.save(any(Space.class))).thenReturn(space);

        service.createSpace(space);

        verify(repository).save(space);
    }

    @Test
    public void listSpaces_returnsAllSpaces() throws Exception {
        Space spaceOne = Space.builder().build();
        Space spaceTwo = Space.builder().build();
        List<Space> spaceList = Arrays.asList(spaceOne, spaceTwo);

        when(repository.findAll()).thenReturn(spaceList);

        service.listSpaces();

        verify(repository).findAll();
    }

    @Test
    public void getSpace_returnsASpace_GivenSpaceId() throws Exception {
        Space space = Space.builder()
                .id(1)
                .disk_quotamb(1)
                .memory_quotamb(2)
                .name("J")
                .build();

        when(repository.findOne(anyInt())).thenReturn(space);

        service.getSpace(1);

        verify(repository).findOne(anyInt());
    }

    @Test
    public void updateSpace_returnsUpdatedSpace_GivenNewAttributers() {
        Space updatedSpace = Space.builder()
                .id(1)
                .disk_quotamb(3)
                .memory_quotamb(2)
                .name("N")
                .build();

        when(repository.exists(any(Integer.class))).thenReturn(true);
        when(repository.save(any(Space.class))).thenReturn(updatedSpace);

        service.updateSpace(updatedSpace);

        verify(repository).save(any(Space.class));
    }

    @Test (expected = EntityNotFoundException.class)
    public void updateSpace_throwsEntityNotFoundException_GivenInvalidId() {

        when(repository.exists(any(Integer.class))).thenReturn(false);

        service.updateSpace(Space.builder().build());

        verify(repository).exists(anyInt());
        verify(repository, never()).save(any(Space.class));
    }

    @Test
    public void deleteSpace_invokesRepositoryDelete() {
        service.deleteSpace(anyInt());
        verify(repository).delete(anyInt());
    }
}