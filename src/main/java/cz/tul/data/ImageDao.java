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
public class ImageDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Image image) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("image_author", image.getImage_author());
        params.addValue("url", image.getUrl());
        params.addValue("name", image.getName());

        return jdbc.update("insert into Image (image_author, url, name) values (:image_author, :url, :name)", params) == 1;
    }

    public boolean exists(int image_id) {
        return jdbc.queryForObject("select count(*) from Image where image_id=:image_id",
                new MapSqlParameterSource("image_id", image_id), Integer.class) > 0;
    }

    public List<Image> getAllImages() {
        return jdbc.query("select * from Image", BeanPropertyRowMapper.newInstance(Image.class));
    }

    public boolean update(Image image) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("image_id", image.getImage_id());
        params.addValue("image_author", image.getImage_author());
        params.addValue("url", image.getUrl());
        params.addValue("name", image.getName());
        params.addValue("created", image.getCreated());
        params.addValue("updated", image.getUpdated());
        return jdbc.update("update Image set image_author=:image_author, url=:url, name=:name, created=:created, updated=:updated where image_id=:image_id", params) == 1;
    }

    public void deleteImages() {
        jdbc.getJdbcOperations().execute("DELETE FROM Tag");
        jdbc.getJdbcOperations().execute("DELETE FROM ImageRating");
        jdbc.getJdbcOperations().execute("DELETE FROM CommentRating");
        jdbc.getJdbcOperations().execute("DELETE FROM Comment");
        jdbc.getJdbcOperations().execute("DELETE FROM Image");
    }
}
