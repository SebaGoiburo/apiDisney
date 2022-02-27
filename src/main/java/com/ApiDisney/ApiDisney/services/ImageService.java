
package com.ApiDisney.ApiDisney.services;

import com.ApiDisney.ApiDisney.entities.Image;
import com.ApiDisney.ApiDisney.error.ErrorService;
import com.ApiDisney.ApiDisney.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
    public Image saveImage(MultipartFile image) throws ErrorService{
        if(image!=null){
            try {
                Image img = new Image();
                img.setMime(image.getContentType());
                img.setName(image.getName());
                img.setContenido(image.getBytes());
                return imageRepository.save(img);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }  
        }
            return null;
    }
    
}
