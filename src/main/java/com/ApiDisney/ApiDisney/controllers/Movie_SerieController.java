
package com.ApiDisney.ApiDisney.controllers;

import com.ApiDisney.ApiDisney.entities.Movie_Serie;
import com.ApiDisney.ApiDisney.error.ErrorService;
import com.ApiDisney.ApiDisney.services.Movie_SerieService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/movies")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class Movie_SerieController {
    
    @Autowired
    Movie_SerieService movie_serieService;
    
    @GetMapping("/movies")
    public List<Movie_Serie> getAll(){
        List<Movie_Serie> movies_series= movie_serieService.listMoviesSeries();
        return movies_series;
        
    }
    
    @PostMapping("save")
    public String save(ModelMap model, @RequestParam("file") MultipartFile image, @RequestParam("name") String name, @RequestParam("creation_date") Date creation_date, @RequestParam("calification") int calification, @RequestParam("character") List<com.ApiDisney.ApiDisney.entities.Character> characters) throws ErrorService{
        try {
            movie_serieService.createMovie_Serie(image, name, creation_date, 0, characters);
            return "The movie or serie was saved successfully";
        } catch (ErrorService e) {
            model.put("error", e.getMessage());
            model.put("name", name);
            model.put("file", image);
            model.put("creation_date", creation_date);
            model.put("calification", calification);
            model.put("characters", characters);
            return "The film could not be saved";
        }
    }
    
    @PutMapping("edit")
    public String editMovie(ModelMap model, @RequestParam("id") String id,@RequestParam("file") MultipartFile image, @RequestParam("name") String name, @RequestParam("creation_date") Date creation_date, @RequestParam("calification") int calification, @RequestParam("character") List<com.ApiDisney.ApiDisney.entities.Character> characters) throws ErrorService{
        try {
            movie_serieService.editMovie_Serie(id, image, name, creation_date, 0, characters);
            return "The movie or serie was edited successfully";
        } catch (ErrorService e) {
            model.put("error", e.getMessage());
            model.put("name", name);
            model.put("file", image);
            model.put("creation_date", creation_date);
            model.put("calification", calification);
            model.put("characters", characters);
            return "The movie or series could not be edited";
        }
    }
    
    @DeleteMapping(path = "delete/{id}")
    public String delete(@PathVariable("id") String id){
        try {
            movie_serieService.delete(id);
            return "Movie or Serie (id: " + id + ") was deleted";
        } catch (Exception e) {
            return "Movie or Serie cannot deleted";
        }
    }
    
    @GetMapping("/movies?title=title")
    public List<Movie_Serie>listMovies_SeriesByTitle(@RequestParam("title")String title, @RequestParam("order")String order){
        return movie_serieService.listMoviesSeriesByTitle(title, order);
    }
    
//    @GetMapping("/movies?genre=idGenre")
//    public List<Movie_Serie>listMovies_SeriesByGenre(@RequestParam("idGenre")String idGenre){
//        return movie_serieService.listMoviesSeriesByGenre(idGenre);
//    }
    
    
    
}
