
package com.ApiDisney.ApiDisney.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Genre {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name= "uuid", strategy= "uuid2")private String id;
    private String name;
    
    @OneToOne
    private Image image;
    
    @Column
    @OneToMany
    private List<Movie_Serie> movies_series;
    
    @Column
    private boolean isRemoved;

    public Genre() {
    }

    public Genre(String id, String name, Image image, List<Movie_Serie> movies_series) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.movies_series = movies_series;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Movie_Serie> getMovies_series() {
        return movies_series;
    }

    public void setMovies_series(List<Movie_Serie> movies_series) {
        this.movies_series = movies_series;
    }

    public boolean isIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean irRemoved) {
        this.isRemoved = irRemoved;
    }
    
   

    @Override
    public String toString() {
        return "Gender: " + "Name: " + name + "Image: " + image + "Movies & series: " + movies_series + '.';
    }
    
    
    
    
}
