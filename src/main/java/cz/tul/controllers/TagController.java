package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Tag;
import cz.tul.services.ImageService;
import cz.tul.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vaclavlangr on 17.05.2017.
 */
@RestController
public class TagController {
    private ImageService imageService;
    private TagService tagService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = ServerApi.TAGS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> getImageTags(@PathVariable("id") Long imageId) {
        List<Tag> tags = tagService.getImageTags(imageId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.TAGS_PATH, method = RequestMethod.POST)
    public ResponseEntity<Tag> addImageTag(@PathVariable("id") Long imageId, @RequestBody String tag) {
        Tag tag1 = new Tag(imageId, tag);
        if(tagService.exists(tag1)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            tagService.create(tag1);
            return new ResponseEntity<>(tag1, HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.TAG_PATH, method = RequestMethod.GET)
    public ResponseEntity<Tag> deleteImageTag(@PathVariable("id") Long imageId, @PathVariable("tag") String tag) {
        Tag tag1 = new Tag(imageId, tag);
        tagService.deleteTag(tag1);
        return new ResponseEntity<>(tag1, HttpStatus.NOT_FOUND);
    }
}
