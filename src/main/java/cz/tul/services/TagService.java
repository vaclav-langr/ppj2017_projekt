package cz.tul.services;

import cz.tul.data.Tag;
import cz.tul.data.TagId;
import cz.tul.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by vaclavlangr on 25.04.17.
 */

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public void create(Tag tag){
        tagRepository.save(tag);
    }

    public List<Tag> getImageTags(long imageId){
        if(imageId <= 0) {
            return null;
        }

        List<Tag> tags = StreamSupport.stream(tagRepository.getTagsForImage(imageId).spliterator(), false).collect(Collectors.toList());

        if(tags.size() == 0) {
            return null;
        }
        return tags;
    }

    public List<String> getUniqueTags(){
        List<String> tags = StreamSupport.stream(tagRepository.getDistinctTags().spliterator(), false).collect(Collectors.toList());
        if(tags.size() == 0) {
            return null;
        }
        return tags;
    }

    public boolean exists(Tag tag) {
        PersistenceUnitUtil util = entityManagerFactory.getPersistenceUnitUtil();
        TagId tagId = (TagId) util.getIdentifier(tag);
        return tagRepository.exists(tagId);
    }

    public void deleteTag(Tag tag) {
        PersistenceUnitUtil util = entityManagerFactory.getPersistenceUnitUtil();
        TagId tagId = (TagId) util.getIdentifier(tag);
        tagRepository.delete(tagId);
    }

    public void deleteTag(TagId tagId){
        tagRepository.delete(tagId);
    }

    public void deleteTagsByImageId(Long imageId) {
        List<Tag> tags = tagRepository.getTagsForImage(imageId);
        tagRepository.delete(tags);
    }

    public void deleteTags(){
        tagRepository.deleteAll();
    }
}
