
package com.ApiDisney.ApiDisney.services;

import com.ApiDisney.ApiDisney.entities.Genre;
import com.ApiDisney.ApiDisney.entities.Image;
import com.ApiDisney.ApiDisney.entities.Movie_Serie;
import com.ApiDisney.ApiDisney.error.ErrorService;
import com.ApiDisney.ApiDisney.repository.GenreRepository;
import com.ApiDisney.ApiDisney.repository.Movie_SerieRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GenreService {
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private ImageService imageService;
    
    @Transactional
    public void createGenre(String name, MultipartFile image, List<Movie_Serie> movies_series) throws ErrorService{
        try {
//            validateCreation(String name, MultipartFile foto);
            Genre genre = new Genre();
            genre.setName(name);
            Image img = new Image();
            img = imageService.saveImage((MultipartFile)image);
            genre.setImage(img);
            genre.setMovies_series(movies_series);
            genre.setIsRemoved(false);
            genreRepository.save(genre);
        } catch (Error e) {
            throw new ErrorService("The genre could not be saved");
        }
    }
    
    @Transactional
    public void editGenre(String id, String name, MultipartFile image, List<Movie_Serie> movies_series) throws ErrorService{
        Optional<Genre> answer = genreRepository.findById(id);
        if (answer.isPresent()){
            Genre genre = answer.get();
            genre.setName(name);
            Image img = new Image();
            img = imageService.saveImage((MultipartFile)image);
            genre.setImage(img);
            genre.setMovies_series(movies_series);
            genreRepository.save(genre);
        } else{
            throw new ErrorService("The genre could not be edited");
        }
    }
    
    @Transactional
    public void delete(String id) throws ErrorService{
        Optional<Genre> answer = genreRepository.findById(id);
        if(answer.isPresent()){
            Genre genre = answer.get();
            genre.setIsRemoved(true);
            genreRepository.save(genre);
        } else{
            throw new ErrorService("No se halló el personaje buscado");
        }
    }
    
    public List<Movie_Serie> listMovie_SerieById(String idGenre){
        List<Movie_Serie> movies_series = genreRepository.listMovie_SerieById(idGenre);
        
        return movies_series;
    }
    
}

