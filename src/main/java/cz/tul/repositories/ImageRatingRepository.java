package cz.tul.repositories;

import cz.tul.data.ImageRating;
import cz.tul.data.ImageRatingId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vaclavlangr on 24.04.17.
 */
@Repository
public interface ImageRatingRepository extends CrudRepository<ImageRating, ImageRatingId>{

    @Query("select ir from ImageRating as ir where ir.imageId = :imageId")
    public List<ImageRating> getImageRatings(@Param("imageId") long imageId);
}
