
package com.ApiDisney.ApiDisney.repository;

import com.ApiDisney.ApiDisney.entities.Character;
import com.ApiDisney.ApiDisney.entities.Movie_Serie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, String> {
    
    @Query("SELECT c.name, c.image FROM Character c")
    public List<Character> listCharactersByImageAndName();
    
    @Query("SELECT c FROM Character c WHERE c.name = :name and c.isRemoved = false")
    public List<Character> findCharacterForName(@Param("name")String name);
    
    @Query("SELECT c FROM Character c WHERE c.age = :age and c.isRemoved = false")
    public List<Character> findCharacterForAge(@Param("age")Integer age);
    
    @Query("SELECT c FROM Character c WHERE c.weight = :weight and c.isRemoved = false")
    public List<Character> findCharacterForWeight(@Param("weight")String weight);
   
    
    
}
