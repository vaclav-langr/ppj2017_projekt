package cz.tul.repositories;

import cz.tul.data.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vaclavlangr on 24.04.17.
 */
@Repository
public interface CommentRepository  extends CrudRepository<Comment, Long> {

    @Query("select c from Comment as c where c.imageId = :imageId")
    public List<Comment> getImageComments(@Param("imageId") long imageId);
}
