package cz.tul.services;

import cz.tul.data.Author;
import cz.tul.data.Image;
import cz.tul.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by vaclavlangr on 24.04.17.
 */
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void create(Image image){
        imageRepository.save(image);
    }

    public boolean exists(int imageId) {
        return imageRepository.exists(imageId);
    }

    public List<Image> getAllImages(){
        return StreamSupport.stream(imageRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Image getImage(int imageId){
        Image image = imageRepository.getImage(imageId);
        if(image == null) {
            return null;
        }
        return image;
    }

    public void saveOrUpdate(Image image) {
        imageRepository.save(image);
    }

    public void deleteImages(){
        imageRepository.deleteAll();
    }

    public void deleteImage(int imageId){
        imageRepository.delete(imageId);
    }

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
