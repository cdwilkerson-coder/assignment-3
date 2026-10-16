
package com.example.marvelapi.controller;

import com.example.marvelapi.entity.MarvelCharacter;
import com.example.marvelapi.service.MarvelCharacterService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class MarvelCharacterController {

    private final MarvelCharacterService service;

    public MarvelCharacterController(MarvelCharacterService service) {
        this.service = service;
    }

    
@GetMapping
public List<MarvelCharacter> getAllCharacters(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String universe) {

    List<MarvelCharacter> characters = service.getAllCharacters();

    if (name != null && !name.isBlank()) {
        characters = characters.stream()
            .filter(character -> character.getName()
                .toLowerCase().contains(name.toLowerCase()))
            .toList();
    }

    if (universe != null && !universe.isBlank()) {
        characters = characters.stream()
            .filter(character -> character.getUniverse() != null
                && character.getUniverse().equalsIgnoreCase(universe))
            .toList();
    }

    return characters;
}


    @GetMapping("/{id}")
    public ResponseEntity<MarvelCharacter> getCharacterById(
            @PathVariable Long id) {

        return service.getCharacterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MarvelCharacter> addCharacter(
            @Valid @RequestBody MarvelCharacter character) {

        MarvelCharacter saved = service.createCharacter(character);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarvelCharacter> updateCharacter(
            @PathVariable Long id,
            @Valid @RequestBody MarvelCharacter updatedCharacter) {

        return service.updateCharacter(id, updatedCharacter)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(
            @PathVariable Long id) {

        if (service.deleteCharacter(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
