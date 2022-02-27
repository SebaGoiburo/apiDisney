/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ApiDisney.ApiDisney.repository;

import com.ApiDisney.ApiDisney.entities.Genre;
import com.ApiDisney.ApiDisney.entities.Movie_Serie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {
    
    @Query("SELECT g FROM Genre g WHERE g.id = :idGenre")
    public List<Movie_Serie> listMovie_SerieById(@Param("idGenre")String idGenre);
    
}
