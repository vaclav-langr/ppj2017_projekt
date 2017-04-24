package cz.tul.repositories;

import cz.tul.data.ImageRating;
import cz.tul.data.ImageRatingId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vaclavlangr on 24.04.17.
 */
@Repository
public interface ImageRatingRepository extends CrudRepository<ImageRating, ImageRatingId>{
}
