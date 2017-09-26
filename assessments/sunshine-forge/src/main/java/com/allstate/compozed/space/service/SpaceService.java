package com.allstate.compozed.space.service;

import com.allstate.compozed.space.repository.Space;
import com.allstate.compozed.space.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceService {

    private final SpaceRepository repository;

    public SpaceService(SpaceRepository repository) {
        this.repository = repository;
    }

    public Space createSpace(Space space) {
        return repository.save(space);
    }

    public List<Integer> listSpaces() {
        return repository.getAllById();
    }

    public Space getSpace(Integer id) {
        return repository.findOne(id);
    }

}
