package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Comment;
import cz.tul.services.CommentService;
import cz.tul.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vaclavlangr on 18.05.2017.
 */
@RestController
public class CommentsController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = ServerApi.COMMENTS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> showComments() {
        List<Comment> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH + ServerApi.COMMENTS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> showImageComments(@PathVariable("imageId") Long imageId) {
        List<Comment> comments = commentService.getImageComments(imageId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.COMMENT_PATH, method = RequestMethod.GET)
    public ResponseEntity<Comment> showComment(@PathVariable("commentId") Long commentId) {
        Comment comment = commentService.getComment(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH + ServerApi.COMMENTS_PATH, method = RequestMethod.POST)
    public ResponseEntity<Comment> addComment(@PathVariable("imageId") Long imageId, @RequestBody Comment comment) {
        if(commentService.exists(comment.getCommentId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            commentService.create(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.COMMENT_PATH, method = RequestMethod.PUT)
    public ResponseEntity<Comment> updateComment(@PathVariable("commentId") Long commentId, @RequestBody Comment comment) {
        Comment temp = commentService.getComment(commentId);
        comment.setCreated(temp.getCreated());
        comment.setCommentId(commentId);
        if(commentService.exists(commentId)) {
            commentService.saveOrUpdate(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.COMMENT_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Comment> deleteComment(@PathVariable("commentId") Long commentId) {
        if(commentService.exists(commentId)) {
            commentService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
