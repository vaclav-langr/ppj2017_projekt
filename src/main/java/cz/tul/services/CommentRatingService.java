package cz.tul.services;

import cz.tul.data.Comment;
import cz.tul.data.CommentRating;
import cz.tul.data.CommentRatingId;
import cz.tul.repositories.CommentRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by vaclavlangr on 25.04.2017.
 */
@Service
public class CommentRatingService {

    @Autowired
    private CommentRatingRepository commentRatingRepository;

    public void create(CommentRating commentRating) {
        commentRatingRepository.save(commentRating);
    }

    public boolean exists(CommentRatingId id) {
        return commentRatingRepository.exists(id);
    }

    public List<CommentRating> getAllRatings() {
        List<CommentRating> ratings = StreamSupport.stream(commentRatingRepository.findAll().spliterator(), false).collect(Collectors.toList());
        if(ratings.size() == 0) {
            return null;
        }
        return ratings;
    }

    public List<CommentRating> getCommentRatings(long commentId) {
        List<CommentRating> ratings = commentRatingRepository.getCommentRatings(commentId);
        if(ratings.size() == 0) {
            return null;
        }
        return ratings;
    }

    public void updateCommentRating(CommentRating commentRating) {
        commentRatingRepository.save(commentRating);
    }

    public void deleteAllCommentRatings() {
        commentRatingRepository.deleteAll();
    }

    public void deleteCommentRating(CommentRating commentRating) {
        commentRatingRepository.delete(commentRating);
    }

    public void deleteCommentRatings(List<Comment> comments) {
        List<CommentRating> commentRatings;
        for (Comment comment: comments) {
            commentRatings = commentRatingRepository.findByCommentId(comment.getCommentId());
            commentRatingRepository.delete(commentRatings);
        }
    }

    public boolean hasRating(String username) {
        if(username.isEmpty()) {
            return false;
        }
        List<CommentRating> images = commentRatingRepository.findByUsername(username);
        if(images == null) {
            return false;
        }
        if(images.size() == 0) {
            return false;
        }
        return true;
    }
}
