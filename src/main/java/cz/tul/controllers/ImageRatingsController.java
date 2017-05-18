package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.ImageRating;
import cz.tul.services.ImageRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vaclavlangr on 18.05.2017.
 */
@RestController
public class ImageRatingsController {

    @Autowired
    private ImageRatingService imageRatingService;

    @RequestMapping(value = ServerApi.IMAGE_RATINGS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<ImageRating>> showImageRatings() {
        List<ImageRating> ratings = imageRatingService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH + ServerApi.IMAGE_RATINGS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<ImageRating>> showImageRatings(@PathVariable(value = "imageId") Long imageId) {
        List<ImageRating> ratings = imageRatingService.getImageRatings(imageId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_RATINGS_PATH, method = RequestMethod.POST)
    public ResponseEntity<ImageRating> addImageRating(@RequestBody ImageRating imageRating) {
        if(imageRatingService.exists(imageRating)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            imageRatingService.create(imageRating);
            return new ResponseEntity<>(imageRating, HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.IMAGE_RATINGS_PATH, method = RequestMethod.PUT)
    public ResponseEntity<ImageRating> updateImageRating(@RequestBody ImageRating imageRating) {
        if(imageRatingService.exists(imageRating)) {
            imageRatingService.update(imageRating);
            return new ResponseEntity<>(imageRating, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.IMAGE_RATINGS_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<ImageRating> deleteImageRating(@RequestBody ImageRating imageRating) {
        if(imageRatingService.exists(imageRating)) {
            imageRatingService.deleteImageRating(imageRating);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
