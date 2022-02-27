
package com.ApiDisney.ApiDisney.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Character {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name= "uuid", strategy= "uuid2")
    private String id;
    
    @Column(unique = true, nullable = false)
    private String name;
    
    @OneToOne
    private Image image;

    @Column
    private int age;
    
    @Column
    private int weight;
    
    @Column
    private String history;
    
    @OneToMany
    private List<Movie_Serie> movies_series;
    
    @Column
    private boolean isRemoved;

    

    public Character() {
    }

    public Character(String id, String name, Image image, int age, int weight, String history, List<Movie_Serie> movies_series, boolean isRemoved) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.movies_series = movies_series;
        this.isRemoved = isRemoved;
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

    public void setName(String nombre) {
        this.name = nombre;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int edad) {
        this.age = edad;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int peso) {
        this.weight = peso;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String historia) {
        this.history = historia;
    }

    public List<Movie_Serie> getMovies_series() {
        return movies_series;
    }

    public void setMovies_series(List<Movie_Serie> movies_series) {
        this.movies_series = movies_series;
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    @Override
    public String toString() {
        return "Character:" + "/n" + "Name: " + name + "Image:=" + image + "Age: " + age + "Weight: " + weight + "History: " + history + "Movies / series:" + movies_series + '.';
    }
    
    
    
    
    
    

    
}
