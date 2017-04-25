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

    public void deleteComment(int commentId) {
        commentRepository.delete(commentId);
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = StreamSupport.stream(commentRepository.findAll().spliterator(), false).collect(Collectors.toList());
        if (comments.size() == 0) {
            return null;
        }
        return comments;
    }

    public List<Comment> getImageComments(int imageId) {
        if(imageId <= 0) {
            return null;
        }
        List<Comment> comments = commentRepository.getImageComments(imageId);

        if(comments.size() == 0) {
            return null;
        }
        return comments;
    }
}
