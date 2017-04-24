package cz.tul.services;

import cz.tul.data.Author;
import cz.tul.data.Image;
import cz.tul.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vaclavlangr on 24.04.17.
 */
@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getImagesByName(String name){
        if(name == null){
            return null;
        }

        List<Image> images = imageRepository.findByName(name);

        if(images.size() == 0){
            return null;
        }

        return images;
    }

    public List<Image> getImagesByAuthor(Author author){
        if(author == null) {
            return null;
        }

        List<Image> images = imageRepository.findByAuthor(author);

        if(images.size() == 0){
            return null;
        }

        return images;
    }

    public List<Image> getImagesByTags(List<String> tags) {
        if(tags == null) {
            return null;
        }
        if(tags.size() == 0){
            return null;
        }

        List<Image> images = imageRepository.findByTags(tags);

        if(images.size() == 0) {
            return null;
        }
        return images;
    }
}
