package com.allstate.compozed.space.controller;

import com.allstate.compozed.space.repository.Space;
import com.allstate.compozed.space.service.SpaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController("space")
@RequestMapping("/api/spaces")
public class SpaceController {

    private final SpaceService service;

    public SpaceController(SpaceService service) {
        this.service = service;
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public Space createSpace(@RequestBody Space newSpace) {
        return service.createSpace(newSpace);
    }

    @GetMapping("")
    public List<Integer> listSpaces() {
        return service.listSpaces();
    }

    @GetMapping("/{id}")
    public Space getSpace(@PathVariable(value="id") Integer id) {
        return service.getSpace(id);
    }
}