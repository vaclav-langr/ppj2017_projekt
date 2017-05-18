package cz.tul.controllers;

import cz.tul.client.FileManager;
import cz.tul.client.ImageStatus;
import cz.tul.client.ServerApi;
import cz.tul.data.Image;
import cz.tul.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by vaclavlangr on 17.05.2017.
 */

@RestController
public class ImagesController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ImageRatingService imageRatingService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRatingService commentRatingService;

    private FileManager fileManager;

    public void setFileManager() {

        try {

            fileManager = FileManager.get();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    @RequestMapping(value = ServerApi.IMAGES_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Image>> showImages() {
        List<Image> images = imageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGES_PATH, method = RequestMethod.POST)
    public ResponseEntity<Image> addImage(@RequestParam("image") Image image) {
        imageService.create(image);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH, method = RequestMethod.GET)
    public ResponseEntity<Image> getImage(@PathVariable("id") Long id) {
        Image image = imageService.getImage(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH + "/data", method = RequestMethod.GET)
    public HttpEntity<byte[]> getImageData(@PathVariable("id") Long id) {
        byte[] image = new byte[0];
        HttpHeaders headers = new HttpHeaders();
        setFileManager();
        if(fileManager.imageExists("test")) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                fileManager.retrieveImage("test", baos);
                image = baos.toByteArray();
                headers.setContentLength(image.length);
                String mime = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image));
                headers.setContentType(MediaType.valueOf(mime));
            } catch (IOException e) {

            }
        }
        return new HttpEntity<>(image, headers);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH, method = RequestMethod.PUT)
    public ResponseEntity<Image> updateImage(@RequestBody Image image) {
        imageService.saveOrUpdate(image);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH, method = RequestMethod.DELETE)
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
