
package com.example.marvelapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.marvelapi.entity.MarvelCharacter;
import com.example.marvelapi.service.MarvelCharacterService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class MarvelPageController {

    private final MarvelCharacterService service;

    public MarvelPageController(MarvelCharacterService service) {
        this.service = service;
    }

    
@GetMapping("/")
public String home(
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "") String universe,
        Model model) {

    String searchName = name.trim().toLowerCase();
    String searchUniverse = universe.trim();

    List<MarvelCharacter> allCharacters =
            service.getAllCharacters();

    List<String> universes = allCharacters.stream()
            .map(MarvelCharacter::getUniverse)
            .filter(Objects::nonNull)
            .filter(value -> !value.isBlank())
            .distinct()
            .sorted()
            .toList();

    List<MarvelCharacter> filteredCharacters =
            allCharacters.stream()
            .filter(character ->
                    searchName.isEmpty()
                    || (character.getName() != null
                    && character.getName().toLowerCase()
                        .contains(searchName)))
            .filter(character ->
                    searchUniverse.isEmpty()
                    || (character.getUniverse() != null
                    && character.getUniverse()
                        .equalsIgnoreCase(searchUniverse)))
            .toList();

    model.addAttribute("characters", filteredCharacters);
    model.addAttribute("universes", universes);
    model.addAttribute("searchName", name.trim());
    model.addAttribute("searchUniverse", searchUniverse);

    return "index";
}


    @GetMapping("/characters/{id}")
    public String characterDetails(
            @PathVariable Long id,
            Model model) {

        Optional<MarvelCharacter> character = service.getCharacterById(id);

        if (character.isEmpty()) {
            return "not-found";
        }

        model.addAttribute("character", character.get());

        return "details";
    }


@GetMapping("/characters/new")
public String showAddForm(Model model) {
    model.addAttribute("character", new MarvelCharacter());
    return "add";
}

@PostMapping("/characters")
public String addCharacter(
        @ModelAttribute MarvelCharacter character,
        RedirectAttributes redirectAttributes) {

    MarvelCharacter saved = service.createCharacter(character);

    redirectAttributes.addFlashAttribute(
        "message",
        saved.getName() + " was added"
    );

    return "redirect:/";
}


@GetMapping("/characters/{id}/edit")
public String showEditForm(
        @PathVariable Long id,
        Model model) {

    return service.getCharacterById(id)
            .map(character -> {
                model.addAttribute("character", character);
                return "edit";
            })
            .orElse("not-found");
}

@PostMapping("/characters/{id}/edit")
public String updateCharacter(
        @PathVariable Long id,
        @ModelAttribute MarvelCharacter updatedCharacter,
        RedirectAttributes redirectAttributes) {

    var result = service.updateCharacter(id, updatedCharacter);

    if (result.isEmpty()) {
        return "not-found";
    }

    redirectAttributes.addFlashAttribute(
            "message",
            result.get().getName() + " was updated"
    );

    return "redirect:/characters/" + id;
}

@PostMapping("/characters/{id}/delete")
public String deleteCharacter(
        @PathVariable Long id,
        RedirectAttributes redirectAttributes) {

    var existing = service.getCharacterById(id);

    if (existing.isEmpty()) {
        return "not-found";
    }

    String name = existing.get().getName();
    service.deleteCharacter(id);

    redirectAttributes.addFlashAttribute(
            "message",
            name + " was removed"
    );

    return "redirect:/";
}

@GetMapping("/about")
public String about() {
    return "about";
}


}
