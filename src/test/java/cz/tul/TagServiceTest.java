package cz.tul;

import cz.tul.app.Main;
import cz.tul.data.Author;
import cz.tul.data.Image;
import cz.tul.data.Tag;
import cz.tul.data.TagId;
import cz.tul.services.AuthorService;
import cz.tul.services.ImageService;
import cz.tul.services.TagService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vaclavlangr on 25.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TagServiceTest {
    @Autowired
    AuthorService authorService;

    @Autowired
    ImageService imageService;

    @Autowired
    TagService tagService;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Before
    public void init(){
        tagService.deleteTags();
        imageService.deleteImages();
        authorService.deleteAuthors();
    }

    private Author author1 = new Author("pepa");
    private Author author2 = new Author("test");
    private Image image1 = new Image(author1, "url", "name");
    private Image image2 = new Image(author2, "url2", "name2");
    private Tag tag1 = new Tag(image1.getImageId(), "testTag");

    @Test
    public void testCreateRetrieve(){
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        tag1.setImageId(image1.getImageId());
        tagService.create(tag1);
        List<Tag> tags1 = tagService.getImageTags(image1.getImageId());
        assertEquals("Should be 1 tag", 1, tags1.size());
        assertEquals("Retrieved tag should match", tag1, tags1.get(0));
    }

    @Test
    public void testDelete() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        tag1.setImageId(image1.getImageId());
        tagService.create(tag1);
        List<Tag> retrieved1 = tagService.getImageTags(image1.getImageId());
        assertEquals("Should be 1 tag", 1, retrieved1.size());

        PersistenceUnitUtil util = entityManagerFactory.getPersistenceUnitUtil();
        TagId tagId = (TagId) util.getIdentifier(tag1);
        tagService.deleteTag(tagId);

        List<Tag> retrieved2 = tagService.getImageTags(image1.getImageId());
        assertNull("Should be null", retrieved2);
    }

    @Test
    public void testUniqueTags() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        tag1.setImageId(image1.getImageId());
        tagService.create(tag1);

        tag1.setImageId(image2.getImageId());
        tagService.create(tag1);

        List<String> tags = tagService.getUniqueTags();
        assertEquals("Should be 1 tag", 1, tags.size());
    }
}
