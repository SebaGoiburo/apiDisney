
package com.ApiDisney.ApiDisney.repository;

import com.ApiDisney.ApiDisney.entities.Movie_Serie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Movie_SerieRepository extends JpaRepository<Movie_Serie, String>{
    
    @Query("SELECT ms.title, ms.image, ms.creation_date FROM Movie_Serie ms WHERE ms.isRemoved = false")
    public List<Movie_Serie> listMovies_Series();

    @Query("SELECT ms FROM Movie_Serie ms WHERE ms.title = :title AND ms.isRemoved = false")
    public List<Movie_Serie> listMovies_SeriesByTitle(@Param("title")String title);
    
//    @Query("SELECT g.movies_series FROM Genre g WHERE g.id = :idGenre")
//    public List<Movie_Serie> listMovies_SeriesByGenre(@Param("genre")String genre);
    
    @Query("SELECT ms.characters FROM Movie_Serie ms WHERE ms.id = :id and ms.isRemoved = false")
    public List<com.ApiDisney.ApiDisney.entities.Character> findCharacterForMovie(@Param("id")String idMovie);
    
}
