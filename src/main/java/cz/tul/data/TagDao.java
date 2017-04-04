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
public class TagDao {
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Tag tag) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("image_id", tag.getImage_id());
        params.addValue("tag", tag.getTag());

        return jdbc.update("insert into Tag (image_id, tag) values (:image_id, :tag)", params) == 1;
    }

    public boolean exists(int image_id, String tag) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("image_id", image_id);
        params.addValue("tag", tag);
        return jdbc.queryForObject("select count(*) from Tag where image_id=:image_id AND tag=:tag",
                params, Integer.class) > 0;
    }

    public List<Tag> getAllTags() {
        return jdbc.query("select * from Tag", BeanPropertyRowMapper.newInstance(Tag.class));
    }

    public void deleteTags() {
        jdbc.getJdbcOperations().execute("DELETE FROM Tag");
    }
}
