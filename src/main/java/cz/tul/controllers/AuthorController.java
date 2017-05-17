package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Author;
import cz.tul.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vaclavlangr on 17.05.2017.
 */

@RestController
public class AuthorController {
    private AuthorService authorService;
    private ImageService imageService;
    private CommentService commentService;
    private ImageRatingService imageRatingService;
    private CommentRatingService commentRatingService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setImageRatingService(ImageRatingService imageRatingService) {
        this.imageRatingService = imageRatingService;
    }

    @Autowired
    public void setCommentRatingService(CommentRatingService commentRatingService) {
        this.commentRatingService = commentRatingService;
    }

    @RequestMapping(value = ServerApi.AUTHORS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Author>> showAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.AUTHORS_PATH, method = RequestMethod.POST)
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        if(authorService.exists(author.getUserName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            authorService.create(author);
            return new ResponseEntity<Author>(author, HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.AUTHOR_PATH, method = RequestMethod.GET)
    public ResponseEntity<Author> getAuthor(@PathVariable("username") String username) {
        if(authorService.exists(username)) {
            Author author = authorService.getAuthor(username);
            return new ResponseEntity<Author>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.AUTHOR_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Author> deleteAuthor(@PathVariable("username") String username) {
        if(authorService.exists(username)) {
            if(imageService.hasImage(username) || commentService.hasComment(username) ||
                    imageRatingService.hasRating(username) || commentRatingService.hasRating(username)) {
                return null; // Throw exception
            } else {
                authorService.deleteAuthor(username);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
