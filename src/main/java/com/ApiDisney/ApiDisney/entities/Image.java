
package com.ApiDisney.ApiDisney.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Image {
 
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name= "uuid", strategy= "uuid2")
    private String id;
    
    
    private String name;
    
    private String mime;
    
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;

    public Image() {
    }

    public Image(String id, String name, String mime, byte[] contenido) {
        this.id = id;
        this.name = name;
        this.mime = mime;
        this.contenido = contenido;
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

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }
    
    
    
}
