package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Tag;
import cz.tul.services.ImageService;
import cz.tul.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit.http.Path;

import java.util.List;

/**
 * Created by vaclavlangr on 17.05.2017.
 */
@RestController
public class TagsController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = ServerApi.TAGS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> showTags() {
        List<Tag> tags = tagService.getTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH + ServerApi.TAGS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> showImageTags(@PathVariable(value = "imageId") Long imageId) {
        List<Tag> tags = tagService.getImageTags(imageId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH + ServerApi.TAGS_PATH, method = RequestMethod.POST)
    public ResponseEntity<Tag> addTag(@PathVariable(value = "imageId") Long imageId,@RequestBody String tag) {
        Tag tag1 = new Tag(imageId, tag);
        if(tagService.exists(tag1)) {
            return new ResponseEntity<>(tag1, HttpStatus.BAD_REQUEST);
        } else {
            tagService.create(tag1);
            return new ResponseEntity<>(tag1, HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH + ServerApi.TAG_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Tag> deleteTag(@PathVariable(value = "imageId") Long imageId,@PathVariable(value = "tag") String tag) {
        Tag tag1 = new Tag(imageId, tag);
        if(tagService.exists(tag1)) {
            tagService.deleteTag(tag1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
