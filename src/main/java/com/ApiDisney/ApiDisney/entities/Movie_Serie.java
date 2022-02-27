
package com.ApiDisney.ApiDisney.entities;

import com.ApiDisney.ApiDisney.entities.Character;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Movie_Serie {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name= "uuid", strategy= "uuid2")
    private String id;
    
    @OneToOne
    private Image image;
    
    @Column
    private String title;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date creation_date;
    
    @Column
    private int qualification;
    
    @OneToMany
    private List<Character> characters;
    
    @Column
    private boolean isRemoved;


    public Movie_Serie() {
    }

    public Movie_Serie(String id, Image image, String title, Date creation_date, int qualification, List<Character> characters, boolean isRemoved) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.creation_date = creation_date;
        this.qualification = qualification;
        this.characters = characters;
        this.isRemoved = isRemoved;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public int getQualification() {
        return qualification;
    }

    public void setQualification(int qualification) {
        this.qualification = qualification;
    }
   
    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
    
    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    @Override
    public String toString() {
        return "Movie / Serie: " + "Title: " + title + "Creation_date: " + creation_date + "Qualification: " + qualification + " Characters=" + characters + '.';
    }
    
    
}
