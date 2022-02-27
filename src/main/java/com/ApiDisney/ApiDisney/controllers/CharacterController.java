
package com.ApiDisney.ApiDisney.controllers;

import com.ApiDisney.ApiDisney.services.CharacterService;
import com.ApiDisney.ApiDisney.entities.Character;
import com.ApiDisney.ApiDisney.entities.Movie_Serie;
import com.ApiDisney.ApiDisney.error.ErrorService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/characters")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class CharacterController {
    
    @Autowired
    CharacterService characterService;
    
    @GetMapping()
    public List<Character> getAll(){
        List<Character> characters= characterService.listCharacters();
        return characters;
        
    }

    @GetMapping("/{id}")
    public Optional<Character> findById(@PathVariable("id") String characterId){
        return characterService.findById(characterId);
    }
    
    @GetMapping(params="name")
    public List<Character> findByName(@RequestParam("name") String name){
        return characterService.findByName(name);
    }
    
    @GetMapping(params="age")
    public List<Character> findByAge(@RequestParam("age") Integer age){
        return characterService.findByAge(age);
    }
    
    
    @GetMapping(params="movies")
    public List<Character> getByMovieId(@RequestParam("idMovie") String idMovie){
        return characterService.findCharacterForMovie(idMovie);
    }
    
    @DeleteMapping(path = "delete/{id}")
    public String delete(@PathVariable("id") String id){
        try {
            characterService.delete(id);
            return "Character (id: " + id + ") was deleted";
        } catch (Exception e) {
            return "Character cannot deleted";
        }
    }
    
    @PostMapping("save")
    public String save(ModelMap model, @RequestParam("name") String name, @RequestParam("file") MultipartFile image, @RequestParam("age") Integer age, @RequestParam("weight") Integer weight, @RequestParam("history") String history, @RequestParam("movies_series") List<Movie_Serie> movies_series) throws ErrorService{
        try {
            characterService.createCharacter(name, image, 0, 0, history, movies_series);
            return "The character was saved successfully";
        } catch (ErrorService e) {
            model.put("error", e.getMessage());
            model.put("name", name);
            model.put("file", image);
            model.put("age", age);
            model.put("weight", weight);
            model.put("history", history);
            model.put("movies_series", movies_series);
            return "Character cannot saved";
        }
    }
    
    @PutMapping("edit")
    public String editCharacter(ModelMap model, @RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("file") MultipartFile image, @RequestParam("age") Integer age, @RequestParam("weight") Integer weight, @RequestParam("history") String history, @RequestParam("movies_series") List<Movie_Serie> movies_series) throws ErrorService{
        try {
            characterService.editCharacter(id, name, image, 0, 0, history, movies_series);
            return "The character was edited successfully";
        } catch (ErrorService e) {
            model.put("error", e.getMessage());
            model.put("name", name);
            model.put("file", image);
            model.put("age", age);
            model.put("weight", weight);
            model.put("history", history);
            model.put("movies_series", movies_series);
            return "Character cannot edited";
        }
    }
    
}
