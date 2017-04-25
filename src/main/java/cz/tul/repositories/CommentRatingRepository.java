package cz.tul.repositories;

import cz.tul.data.CommentRating;
import cz.tul.data.CommentRatingId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vaclavlangr on 24.04.17.
 */
@Repository
public interface CommentRatingRepository extends CrudRepository<CommentRating, CommentRatingId> {

    @Query("select cr from CommentRating as cr where cr.commentId = :commentId")
    public List<CommentRating> getCommentRatings(@Param("commentId") long commentId);
}
