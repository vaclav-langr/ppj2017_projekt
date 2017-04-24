package cz.tul.repositories;

import cz.tul.data.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vaclavlangr on 24.04.17.
 */
@Repository
public interface CommentRepository  extends CrudRepository<Comment, Integer> {
}
