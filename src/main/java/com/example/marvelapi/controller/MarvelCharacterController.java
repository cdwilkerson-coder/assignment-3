
package com.example.marvelapi.controller;

import com.example.marvelapi.entity.MarvelCharacter;
import com.example.marvelapi.repository.MarvelCharacterRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class MarvelCharacterController {

    private final MarvelCharacterRepository repository;

    public MarvelCharacterController(MarvelCharacterRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<MarvelCharacter> getAllCharacters() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public MarvelCharacter getCharacterById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public MarvelCharacter addCharacter(@RequestBody MarvelCharacter character) {
        return repository.save(character);
    }

    @PutMapping("/{id}")
    public MarvelCharacter updateCharacter(
            @PathVariable Long id,
            @RequestBody MarvelCharacter updatedCharacter) {

        return repository.findById(id).map(character -> {
            character.setName(updatedCharacter.getName());
            character.setAlias(updatedCharacter.getAlias());
            character.setDescription(updatedCharacter.getDescription());
            return repository.save(character);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
