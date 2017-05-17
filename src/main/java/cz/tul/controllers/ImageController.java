package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Image;
import cz.tul.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by vaclavlangr on 17.05.2017.
 */

@RestController
public class ImageController {
    private ImageService imageService;
    private TagService tagService;
    private ImageRatingService imageRatingService;
    private CommentService commentService;
    private CommentRatingService commentRatingService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setImageRatingService(ImageRatingService imageRatingService) {
        this.imageRatingService = imageRatingService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setCommentRatingService(CommentRatingService commentRatingService) {
        this.commentRatingService = commentRatingService;
    }

    @RequestMapping(value = ServerApi.IMAGES_PATH, method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<List<Image>> showImages() {
        List<Image> images = imageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGES_PATH, method = RequestMethod.POST)
    public ResponseEntity<Image> addImage(@RequestBody Image image) {
        imageService.create(image);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH, method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Image> getImage(@PathVariable("id") Long id) {
        Image image = imageService.getImage(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGES_PATH, method = RequestMethod.PUT)
    public ResponseEntity<Image> updateImage(@RequestBody Image image, @PathVariable("id") Long id) {
        imageService.saveOrUpdate(image);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.AUTHOR_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Image> deleteImage(@PathVariable("id") Long id) {
        if(imageService.exists(id)) {
            tagService.deleteTagsByImageId(id);
            imageRatingService.deleteImageRatingsByImageId(id);
            commentRatingService.deleteCommentRatings(commentService.getImageComments(id));
            commentService.deleteCommentsByImageId(id);
            imageService.deleteImage(id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
