

package com.ApiDisney.ApiDisney.repository;

import com.ApiDisney.ApiDisney.entities.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsernameRepository extends JpaRepository<Username, String> {
    
    @Query("SELECT u FROM Username u WHERE u.correo = :correo")
    public Username findUsername(@Param("correo")String correo);
    
}
