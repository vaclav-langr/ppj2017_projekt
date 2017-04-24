package cz.tul.repositories;

import cz.tul.data.CommentRating;
import cz.tul.data.CommentRatingId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vaclavlangr on 24.04.17.
 */
@Repository
public interface CommentRatingRepository extends CrudRepository<CommentRating, CommentRatingId> {
}
