
package com.example.marvelapi.service;

import com.example.marvelapi.entity.MarvelCharacter;
import com.example.marvelapi.repository.MarvelCharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarvelCharacterService {

    private final MarvelCharacterRepository repository;

    public MarvelCharacterService(MarvelCharacterRepository repository) {
        this.repository = repository;
    }

    public List<MarvelCharacter> getAllCharacters() {
        return repository.findAll();
    }

    public Optional<MarvelCharacter> getCharacterById(Long id) {
        return repository.findById(id);
    }

    public MarvelCharacter createCharacter(MarvelCharacter character) {
        return repository.save(character);
    }

    public Optional<MarvelCharacter> updateCharacter(
            Long id, MarvelCharacter updatedCharacter) {

        return repository.findById(id).map(character -> {
            character.setName(updatedCharacter.getName());
            character.setDescription(updatedCharacter.getDescription());
            character.setAlias(updatedCharacter.getAlias());
            return repository.save(character);
        });
    }

    public boolean deleteCharacter(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}

