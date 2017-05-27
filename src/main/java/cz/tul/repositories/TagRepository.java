package cz.tul.repositories;

import cz.tul.data.Tag;
import cz.tul.data.TagId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vaclavlangr on 24.04.17.
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, TagId>{

    @Query("select t from Tag as t where t.imageId = :imageId")
    public List<Tag> getTagsForImage(@Param("imageId") long imageId);

    @Query("select distinct(t.tag) from Tag as t")
    public List<String> getDistinctTags();
}
