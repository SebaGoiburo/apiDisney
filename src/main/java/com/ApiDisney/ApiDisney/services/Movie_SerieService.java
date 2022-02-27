package com.ApiDisney.ApiDisney.services;

import com.ApiDisney.ApiDisney.entities.Image;
import com.ApiDisney.ApiDisney.entities.Movie_Serie;
import com.ApiDisney.ApiDisney.entities.Character;
import com.ApiDisney.ApiDisney.error.ErrorService;
import com.ApiDisney.ApiDisney.repository.Movie_SerieRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Movie_SerieService {

    @Autowired
    private Movie_SerieRepository movie_serieRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public void createMovie_Serie(MultipartFile image, String title, Date creation_date, int qualification, List<Character> characters) throws ErrorService {
        try {
            Movie_Serie movie_serie = new Movie_Serie();
            Image img = imageService.saveImage((MultipartFile) image);
            movie_serie.setImage(img);
            movie_serie.setTitle(title);
            movie_serie.setCreation_date(creation_date);
            //Utilizo un método para validar que la calificación sea entre 1 y 5
            if(validateQualification(qualification)){
                movie_serie.setQualification(qualification);
            }
            movie_serie.setCharacters(characters);
            movie_serie.setIsRemoved(false);
            movie_serieRepository.save(movie_serie);
        } catch (Error e) {
            throw new ErrorService("Ha ocurrido un error durate la carga");
        }
    }

    @Transactional
    public void editMovie_Serie(String id, MultipartFile image, String title, Date creation_date, int qualification, List<Character> characters) throws ErrorService {
        Optional<Movie_Serie> answer = movie_serieRepository.findById(id);
        if (answer.isPresent()) {
            Movie_Serie movie_serie = answer.get();
            Image img = new Image();
            img = imageService.saveImage((MultipartFile) image);
            movie_serie.setImage(img);
            movie_serie.setTitle(title);
            movie_serie.setCreation_date(creation_date);
            //Utilizo un método para validar que la calificación sea entre 1 y 5
            if(validateQualification(qualification)){
                movie_serie.setQualification(qualification);
            }
            movie_serie.setCharacters(characters);
            movie_serieRepository.save(movie_serie);
        } else {
            throw new ErrorService("No se halló la película o serie buscada");
        }
    }

    @Transactional
    public void delete(String id) throws ErrorService {
        Optional<Movie_Serie> answer = movie_serieRepository.findById(id);
        if (answer.isPresent()) {
            Movie_Serie mov_ser = answer.get();
            mov_ser.setIsRemoved(true);
            movie_serieRepository.save(mov_ser);
        } else {
            throw new ErrorService("No se halló la película o serie buscada");
        }
    }

    public List<Movie_Serie> listMoviesSeries(){
        List<Movie_Serie> movies_series = movie_serieRepository.listMovies_Series();
        
        return movies_series;
    }
 
    public Movie_Serie movie_serieDetails(String id){
        Movie_Serie movie_serie = movie_serieRepository.getById(id);
        return movie_serie;
    }
    
    public List<Movie_Serie> listMoviesSeriesByTitle(String title, String order){
        List<Movie_Serie> movies_series = movie_serieRepository.listMovies_SeriesByTitle(title);
        
        return movies_series;
    }
    
//    public List<Movie_Serie> listMoviesSeriesByGenre(String idGenre){
//        List<Movie_Serie> movies_series = movie_serieRepository.listMovies_SeriesByGenre(idGenre);
//        
//        return movies_series;
//    }
    
    public boolean validateQualification(int qualification) throws ErrorService{
        
        if(qualification>0 && qualification<6){
          return true;
        } else{
            throw new ErrorService("Puntuación fuera de rango");
        } 
    }
}
