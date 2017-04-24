package cz.tul.repositories;

import cz.tul.data.Tag;
import cz.tul.data.TagId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vaclavlangr on 24.04.17.
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, TagId>{
}
