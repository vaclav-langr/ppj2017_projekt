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
public class AuthorDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Author author) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("user_name", author.getUser_name());

        return jdbc.update("insert into Author (user_name) values (:user_name)", params) == 1;
    }

    public boolean exists(String user_name) {
        return jdbc.queryForObject("select count(*) from Author where user_name=:user_name",
                new MapSqlParameterSource("user_name", user_name), Integer.class) > 0;
    }

    public List<Author> getAllAuthors() {
        return jdbc.query("select * from Author", BeanPropertyRowMapper.newInstance(Author.class));
    }

    public void deleteAuthors() {
        jdbc.getJdbcOperations().execute("DELETE FROM ImageRating");
        jdbc.getJdbcOperations().execute("DELETE FROM Image");
        jdbc.getJdbcOperations().execute("DELETE FROM CommentRating");
        jdbc.getJdbcOperations().execute("DELETE FROM Comment");
        jdbc.getJdbcOperations().execute("DELETE FROM Author");
    }
}
