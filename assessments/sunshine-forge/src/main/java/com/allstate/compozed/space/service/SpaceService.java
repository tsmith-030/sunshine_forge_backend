package com.allstate.compozed.space.service;

import com.allstate.compozed.space.repository.Space;
import com.allstate.compozed.space.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SpaceService {

    private final SpaceRepository repository;

    public SpaceService(SpaceRepository repository) {
        this.repository = repository;
    }

    public Space createSpace(Space space) {
        return this.repository.save(space);
    }


    public Space getSpace(Integer id) {
        return repository.findOne(id);
    }

    public List<Space> listSpaces() {
        return (List)repository.findAll();
    }

    public Space updateSpace(Space space) {
        if (repository.exists(space.getId())) {
            return repository.save(space);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteSpace(Integer id) {
        repository.delete(id);
    }
}
