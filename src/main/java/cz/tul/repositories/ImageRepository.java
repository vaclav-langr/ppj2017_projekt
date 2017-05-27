package cz.tul.repositories;

import cz.tul.data.Author;
import cz.tul.data.Image;
import cz.tul.data.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vaclavlangr on 24.04.17.
 */
@Repository
public interface ImageRepository extends CrudRepository<Image, Long>{

    @Query("select i from Image as i where i.name LIKE %:name%")
    public List<Image> findByNameContaining(@Param("name") String name);

    @Query("select i from Image as i where i.author=:author")
    public List<Image> findByAuthor(@Param("author") Author author);

    @Query("select i from Image as i where i.author.userName=:userName")
    public List<Image> findByUsername(@Param("userName") String userName);

    @Query("select distinct(t.imageId) from Tag as t where t.tag LIKE :tag")
    public List<Long> findByTags(@Param("tag") String tag);

    @Query("select i from Image as i where i.imageId = :imageId")
    public Image getImage(@Param("imageId") long imageId);
}
