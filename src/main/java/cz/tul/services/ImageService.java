package cz.tul.services;

import cz.tul.data.Author;
import cz.tul.data.Image;
import cz.tul.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public boolean exists(long imageId) {
        return imageRepository.exists(imageId);
    }

    public List<Image> getAllImages(){
        return StreamSupport.stream(imageRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Image getImage(long imageId){
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

    public void deleteImage(long imageId){
        imageRepository.delete(imageId);
    }

    public List<Image> getImagesByName(String name){
        if(name == null){
            return null;
        }

        List<Image> images = imageRepository.findByNameContaining(name);

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

    public List<Image> getImagesByAuthor(String username){
        if(username == null) {
            return null;
        }
        if(username.isEmpty()) {
            return null;
        }

        List<Image> images = imageRepository.findByUsername(username);

        if(images.size() == 0){
            return null;
        }

        return images;
    }

    public List<Image> getImagesByTag(String tag) {
        if(tag == null) {
            return null;
        }
        if(tag.isEmpty()){
            return null;
        }

        List<Long> imageIds = imageRepository.findByTags(tag);
        if(imageIds.size() == 0) {
            return null;
        }

        List<Image> images = new ArrayList<>();
        for (long id: imageIds) {
            images.add(imageRepository.getImage(id));
        }

        if(images.size() == 0) {
            return null;
        }
        return images;
    }

    public boolean hasImage(String username) {
        if(username.isEmpty()) {
            return false;
        }
        List<Image> images = imageRepository.findByUsername(username);
        if(images == null) {
            return false;
        }
        if(images.size() == 0) {
            return false;
        }
        return true;
    }
}
