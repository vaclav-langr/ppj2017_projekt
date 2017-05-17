package cz.tul.services;

import cz.tul.data.Comment;
import cz.tul.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by vaclavlangr on 25.04.2017.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void create(Comment comment) {
        commentRepository.save(comment);
    }

    public void saveOrUpdate(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteComments() {
        commentRepository.deleteAll();
    }

    public void deleteCommentsByImageId(Long imageId) {
        List<Comment> comments = commentRepository.getImageComments(imageId);
        commentRepository.delete(comments);
    }

    public void deleteComment(long commentId) {
        commentRepository.delete(commentId);
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = StreamSupport.stream(commentRepository.findAll().spliterator(), false).collect(Collectors.toList());
        if (comments.size() == 0) {
            return null;
        }
        return comments;
    }

    public List<Comment> getImageComments(long imageId) {
        if(imageId <= 0) {
            return null;
        }
        List<Comment> comments = commentRepository.getImageComments(imageId);

        if(comments.size() == 0) {
            return null;
        }
        return comments;
    }

    public boolean hasComment(String username) {
        if(username.isEmpty()) {
            return false;
        }
        List<Comment> images = commentRepository.findByUsername(username);
        if(images == null) {
            return false;
        }
        if(images.size() == 0) {
            return false;
        }
        return true;
    }
}
