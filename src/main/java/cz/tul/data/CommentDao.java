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
public class CommentDao {
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Comment comment) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("image_id", comment.getImage_id());
        params.addValue("comment_author", comment.getComment_author());
        params.addValue("comment_text", comment.getComment_text());

        return jdbc.update("insert into Comment (image_id, comment_author, comment_text) values (:image_id, :comment_author, :comment_text)", params) == 1;
    }

    public boolean exists(int comment_id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("comment_id", comment_id);
        return jdbc.queryForObject("select count(*) from Comment where comment_id=:comment_id",
                params, Integer.class) > 0;
    }

    public List<Comment> getAllComments() {
        return jdbc.query("select * from Comment", BeanPropertyRowMapper.newInstance(Comment.class));
    }

    public void deleteComments() {
        jdbc.getJdbcOperations().execute("DELETE FROM CommentRating");
        jdbc.getJdbcOperations().execute("DELETE FROM Comment");
    }
}
