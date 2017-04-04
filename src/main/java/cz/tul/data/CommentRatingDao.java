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
public class CommentRatingDao {
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(CommentRating rating) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("comment_id", rating.getComment_id());
        params.addValue("comment_rating_author", rating.getComment_rating_author());
        params.addValue("value", rating.getValue());

        return jdbc.update("insert into CommentRating (comment_id, comment_rating_author, value) values (:comment_id, :comment_rating_author, :value)", params) == 1;
    }

    public boolean exists(int comment_id, String comment_rating_author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("comment_id", comment_id);
        params.addValue("comment_rating_author", comment_rating_author);
        return jdbc.queryForObject("select count(*) from CommentRating where comment_id=:comment_id AND comment_rating_author=:comment_rating_author",
                params, Integer.class) > 0;
    }

    public List<CommentRating> getAllCommentRatings() {
        return jdbc.query("select * from CommentRating", BeanPropertyRowMapper.newInstance(CommentRating.class));
    }

    public void deleteCommentRatings() {
        jdbc.getJdbcOperations().execute("DELETE FROM CommentRating");
    }
}
