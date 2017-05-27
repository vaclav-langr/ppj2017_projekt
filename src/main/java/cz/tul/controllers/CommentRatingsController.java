package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Comment;
import cz.tul.data.CommentRating;
import cz.tul.services.CommentRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vaclavlangr on 18.05.2017.
 */

@RestController
public class CommentRatingsController {

    @Autowired
    private CommentRatingService commentRatingService;

    @RequestMapping(value = ServerApi.COMMENT_RATINGS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<CommentRating>> showCommentRatings() {
        List<CommentRating> commentRatings = commentRatingService.getAllRatings();
        return new ResponseEntity<>(commentRatings, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.COMMENT_PATH + ServerApi.COMMENT_RATINGS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<CommentRating>> showCommentRatings(@PathVariable("commentId") Long commentId) {
        List<CommentRating> commentRatings = commentRatingService.getCommentRatings(commentId);
        return new ResponseEntity<>(commentRatings, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.COMMENT_RATINGS_PATH, method = RequestMethod.POST)
    public ResponseEntity<CommentRating> addCommentRating(@RequestBody CommentRating commentRating) {
        if(commentRatingService.exists(commentRating)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            commentRatingService.create(commentRating);
            return new ResponseEntity<>(commentRating, HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.COMMENT_RATINGS_PATH, method = RequestMethod.PUT)
    public ResponseEntity<CommentRating> updateCommentRating(@RequestBody CommentRating commentRating) {
        if(commentRatingService.exists(commentRating)) {
            commentRatingService.updateCommentRating(commentRating);
            return new ResponseEntity<>(commentRating, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.COMMENT_RATINGS_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<CommentRating> deleteCommentRating(@RequestBody CommentRating commentRating) {
        if(commentRatingService.exists(commentRating)) {
            commentRatingService.deleteCommentRating(commentRating);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
