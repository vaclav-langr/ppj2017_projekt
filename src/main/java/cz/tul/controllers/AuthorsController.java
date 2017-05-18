package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Author;
import cz.tul.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vaclavlangr on 17.05.2017.
 */

@RestController
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageRatingService imageRatingService;

    @Autowired
    private CommentRatingService commentRatingService;

    @RequestMapping(value = ServerApi.AUTHORS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Author>> showAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.AUTHORS_PATH, method = RequestMethod.POST)
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        if(authorService.exists(author.getUserName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            authorService.create(author);
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.AUTHOR_PATH, method = RequestMethod.GET)
    public ResponseEntity<Author> getAuthor(@PathVariable("userName") String userName) {
        if(authorService.exists(userName)) {
            Author author = authorService.getAuthor(userName);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.AUTHOR_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Author> deleteAuthor(@PathVariable("userName") String userName) {
        if(authorService.exists(userName)) {
            if(imageService.hasImage(userName) || commentService.hasComment(userName) ||
                    imageRatingService.hasRating(userName) || commentRatingService.hasRating(userName)) {
                return null; // Throw exception
            } else {
                authorService.deleteAuthor(userName);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
