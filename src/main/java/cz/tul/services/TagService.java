package cz.tul.services;

import cz.tul.data.Tag;
import cz.tul.data.TagId;
import cz.tul.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void create(Tag tag){
        tagRepository.save(tag);
    }

    public List<Tag> getImageTags(int imageId){
        return StreamSupport.stream(tagRepository.getTagsForImage(imageId).spliterator(), false).collect(Collectors.toList());
    }

    public List<String> getUniqueTags(){
        return StreamSupport.stream(tagRepository.getDistinctTags().spliterator(), false).collect(Collectors.toList());
    }

    public void saveOrUpdate(Tag tag){
        tagRepository.save(tag);
    }

    public void deleteTag(TagId tagId){
        tagRepository.delete(tagId);
    }

    public void deleteTags(){
        tagRepository.deleteAll();
    }
}
