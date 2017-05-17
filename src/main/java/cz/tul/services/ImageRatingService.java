package cz.tul.services;

import cz.tul.data.ImageRating;
import cz.tul.data.ImageRatingId;
import cz.tul.repositories.ImageRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by vaclavlangr on 25.04.2017.
 */
@Service
public class ImageRatingService {

    @Autowired
    private ImageRatingRepository imageRatingRepository;

    public void create(ImageRating imageRating) {
        imageRatingRepository.save(imageRating);
    }

    public boolean exists(ImageRatingId id) {
        return imageRatingRepository.exists(id);
    }

    public List<ImageRating> getAllRatings() {
        List<ImageRating> ratings = StreamSupport.stream(imageRatingRepository.findAll().spliterator(), false).collect(Collectors.toList());
        if(ratings.size() == 0) {
            return null;
        }
        return ratings;
    }

    public List<ImageRating> getImageRatings(long imageId) {
        List<ImageRating> ratings = imageRatingRepository.getImageRatings(imageId);
        if(ratings.size() == 0) {
            return null;
        }
        return ratings;
    }

    public void update(ImageRating imageRating) {
        imageRatingRepository.save(imageRating);
    }

    public void deleteImageRatings() {
        imageRatingRepository.deleteAll();
    }

    public void deleteImageRatingsByImageId(Long imageId) {
        List<ImageRating> imageRatings = imageRatingRepository.getImageRatings(imageId);
        imageRatingRepository.delete(imageRatings);
    }

    public void deleteImageRating(ImageRating imageRating) {
        imageRatingRepository.delete(imageRating);
    }

    public boolean hasRating(String username) {
        if(username.isEmpty()) {
            return false;
        }
        List<ImageRating> images = imageRatingRepository.findByUsername(username);
        if(images == null) {
            return false;
        }
        if(images.size() == 0) {
            return false;
        }
        return true;
    }
}
