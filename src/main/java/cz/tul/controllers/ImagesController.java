package cz.tul.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.tul.client.FileManager;
import cz.tul.client.ImageStatus;
import cz.tul.client.ServerApi;
import cz.tul.data.Author;
import cz.tul.data.Image;
import cz.tul.services.*;
import org.apache.log4j.Logger;
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
    private AuthorService authorService;

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
            Logger logger = Logger.getLogger(ImagesController.class);
            logger.error("Unable to set file manager:", e);
        }
    }

    @RequestMapping(value = ServerApi.IMAGES_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Image>> showImages() {
        List<Image> images = imageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_FIND_NAME, method = RequestMethod.GET)
    public ResponseEntity<List<Image>> showImagesByName(@PathVariable("imageName") String imageName) {
        List<Image> images = imageService.getImagesByName(imageName);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_FIND_AUTHOR, method = RequestMethod.GET)
    public ResponseEntity<List<Image>> showImagesByUserName(@PathVariable("userName") String userName) {
        List<Image> images = imageService.getImagesByAuthor(userName);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_FIND_TAG, method = RequestMethod.GET)
    public ResponseEntity<List<Image>> showImagesByTag(@PathVariable("tag") String tag) {
        List<Image> images = imageService.getImagesByTag(tag);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGES_PATH, method = RequestMethod.POST)
    public ResponseEntity<ImageStatus> addImage(@RequestParam(value = "strAuthor") String strAuthor,
                                                @RequestParam(value = "strImage") String strImage,
                                                @RequestParam(value = "data") MultipartFile imageData) {
        Author author = null;
        Image image = null;
        try {
            author = new ObjectMapper().readValue(strAuthor, Author.class);
            if(authorService.exists(author.getUserName())) {
                author = authorService.getAuthor(author.getUserName());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(ImagesController.class);
            logger.error("Unable to parse strAuthor=" + strAuthor + ":", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            image = new ObjectMapper().readValue(strImage, Image.class);
            image.setAuthor(author);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(ImagesController.class);
            logger.error("Unable to parse strImage=" + strImage + ":", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ImageStatus state = new ImageStatus(ImageStatus.ImageState.READY);
        imageService.create(image);
        if(image.getUrl() == null || image.getUrl().isEmpty()) {
            setFileManager();
            try {
                Long id = image.getImageId();
                fileManager.saveImage(id.toString(), imageData.getInputStream());
            } catch (IOException e) {
                Logger logger = Logger.getLogger(ImagesController.class);
                logger.error("Unable to save file:", e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(state, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(state, HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH, method = RequestMethod.PUT)
    public ResponseEntity<ImageStatus> updateImage(@PathVariable("imageId") Long imageId,
                                                   @RequestPart(value = "strAuthor") String strAuthor,
                                                   @RequestPart(value = "strImage") String strImage) {
        Author author = null;
        Image image = null;
        try {
            author = new ObjectMapper().readValue(strAuthor, Author.class);
            if(authorService.exists(author.getUserName())) {
                author = authorService.getAuthor(author.getUserName());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(ImagesController.class);
            logger.error("Unable to parse strAuthor=" + strAuthor + ":", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            image = new ObjectMapper().readValue(strImage, Image.class);
            image.setAuthor(author);
            image.setImageId(imageId);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(ImagesController.class);
            logger.error("Unable to parse strImage=" + strImage + ":", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ImageStatus state = new ImageStatus(ImageStatus.ImageState.READY);
        imageService.saveOrUpdate(image);
        return new ResponseEntity<>(state, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH, method = RequestMethod.GET)
    public ResponseEntity<Image> getImage(@PathVariable("imageId") Long id) {
        Image image = imageService.getImage(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH + "/data", method = RequestMethod.GET)
    public HttpEntity<byte[]> getImageData(@PathVariable("imageId") Long id) {
        byte[] image = new byte[0];
        HttpHeaders headers = new HttpHeaders();
        setFileManager();
        if(fileManager.imageExists(id.toString())) {
            Image image1 = imageService.getImage(id);
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                if(image1.getUrl().isEmpty()) {
                    fileManager.retrieveImage(id.toString(), baos);
                    image = baos.toByteArray();
                    headers.setContentLength(image.length);
                    String mime = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image));
                    headers.setContentType(MediaType.valueOf(mime));
                }
            } catch (IOException e) {
                Logger logger = Logger.getLogger(ImagesController.class);
                logger.error("Unable to get file:", e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new HttpEntity<>(image, headers);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Image> deleteImage(@PathVariable("imageId") Long id) {
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
