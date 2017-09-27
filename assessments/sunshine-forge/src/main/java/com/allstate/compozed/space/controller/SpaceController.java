package com.allstate.compozed.space.controller;

import com.allstate.compozed.space.repository.Space;
import com.allstate.compozed.space.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/spaces")
public class SpaceController {

    private final SpaceService service;

    public SpaceController(SpaceService service) {
        this.service = service;
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public Space createSpace(@RequestBody Space newSpace) {
        return this.service.createSpace(newSpace);
    }

    @GetMapping("")
    public List<Space> listSpaces() {
        return this.service.listSpaces();
    }

    @GetMapping("/{id}")
    public Space getSpace(@PathVariable(value="id") Integer id) {
        return service.getSpace(id);
    }

    @PostMapping("/{id}")
    public Space updateSpace(@RequestBody Space space) { return service.updateSpace(space);}

    @DeleteMapping("/{id}")
    public void deleteSpace(@PathVariable(value="id") Integer id) {
        service.deleteSpace(id);
    }
}