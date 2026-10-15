package com.example.marvelapi.repository;

import com.example.marvelapi.entity.MarvelCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarvelCharacterRepository
        extends JpaRepository<MarvelCharacter, Long> {

}
