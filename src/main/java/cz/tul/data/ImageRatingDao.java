package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vaclavlangr on 03.04.17.
 */
public class ImageRatingDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(ImageRating rating) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("image_id", rating.getImage_id());
        params.addValue("image_rating_author", rating.getImage_rating_author());
        params.addValue("value", rating.getValue());

        return jdbc.update("insert into ImageRating (image_id, image_rating_author, value) values (:image_id, :image_rating_author, :value)", params) == 1;
    }

    public boolean exists(int image_id, String image_rating_author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("image_id", image_id);
        params.addValue("image_rating_author", image_rating_author);
        return jdbc.queryForObject("select count(*) from ImageRating where image_id=:image_id and image_rating_author=:image_rating_author",
                params, Integer.class) > 0;
    }

    public List<ImageRating> getAllImageRatings() {
        return jdbc.query("select * from ImageRating", BeanPropertyRowMapper.newInstance(ImageRating.class));
    }

    public void deleteImageRatings() {
        jdbc.getJdbcOperations().execute("DELETE FROM ImageRating");
    }
}
