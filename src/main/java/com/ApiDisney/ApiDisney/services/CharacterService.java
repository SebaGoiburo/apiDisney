
package com.ApiDisney.ApiDisney.services;

import com.ApiDisney.ApiDisney.entities.Movie_Serie;
import com.ApiDisney.ApiDisney.entities.Character;
import com.ApiDisney.ApiDisney.entities.Image;
import com.ApiDisney.ApiDisney.error.ErrorService;
import com.ApiDisney.ApiDisney.repository.CharacterRepository;
import com.ApiDisney.ApiDisney.repository.Movie_SerieRepository;
import com.google.gson.Gson;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CharacterService {
    
    @Autowired
    private CharacterRepository characterRepository;
    
    @Autowired Movie_SerieRepository movie_serieRepository;
    
    @Autowired
    private ImageService imageService;
    
    @Transactional
    public void createCharacter(String name, MultipartFile image, int age, int weight, String history, List<Movie_Serie> movies_series) throws ErrorService{
        try {
//            validateCreation(String name, MultipartFile foto);
            Character character = new Character();
            character.setName(name);
            Image img = new Image();
            img = imageService.saveImage((MultipartFile)image);
            character.setImage(img);
            character.setAge(age);
            character.setWeight(weight);
            character.setHistory(history);
            character.setMovies_series(movies_series);
            character.setIsRemoved(false);
            characterRepository.save(character);
        } catch (Error e) {
            throw new ErrorService("Ha ocurrido un error durate la carga");
        }
    }
    
    @Transactional
    public void editCharacter(String id, String name, MultipartFile image, int age, int weight, String history, List<Movie_Serie> movies_series) throws ErrorService{
        Optional<Character> answer = characterRepository.findById(id);
        if (answer.isPresent()){
            Character character = answer.get();
            character.setName(name);
            Image img = new Image();
            img = imageService.saveImage((MultipartFile)image);
            character.setImage(img);
            character.setAge(age);
            character.setWeight(age);
            character.setHistory(history);
            character.setMovies_series(movies_series);
            characterRepository.save(character);
        } else{
            throw new ErrorService("No se halló el personaje buscado");
        }
    }
    
    @Transactional
    public void delete(String id) throws ErrorService{
        Optional<Character> answer = characterRepository.findById(id);
        if(answer.isPresent()){
            Character charac = answer.get();
            charac.setIsRemoved(true);
            characterRepository.save(charac);
        } else{
            throw new ErrorService("No se halló el personaje buscado");
        }
    }
    
    public List<Character> listCharacters(){
        List<Character> characters = characterRepository.listCharactersByImageAndName();
        
        return characters;
    }
    
    public Optional<Character> findById(String id){
        Optional<Character> character = characterRepository.findById(id);
        return character;
    }
    
    public List<Character> findByName(String name){
        List<Character> characters = characterRepository.findCharacterForName(name);
        return characters;
    }
    
    public List<Character> findByAge(Integer age){
        List<Character> characters = characterRepository.findCharacterForAge(age);
        return characters;
    }
    
    public List<Character> findCharacterForMovie(String idMovie){
        List<Character> characters = movie_serieRepository.findCharacterForMovie(idMovie);
        return characters;
    }
    
    public void validateCreation(String name, MultipartFile foto) throws ErrorService{
        if (name == null || name.isEmpty()){
            throw new ErrorService("El personaje debe tener un nombre");
        }
        if (foto == null|| foto.isEmpty()){
            throw new ErrorService("El personaje debe tener una imagen");
        }
    }
}
