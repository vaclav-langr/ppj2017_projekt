package cz.tul;

import cz.tul.data.Author;
import cz.tul.data.Image;
import cz.tul.data.Tag;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vaclavlangr on 25.04.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageServiceTest {
    @Autowired
    ImageService imageService;

    @Autowired
    AuthorService authorService;

    @Autowired
    TagService tagService;

    @Before
    public void init(){
        imageService.deleteImages();
        authorService.deleteAuthors();
    }

    private Author author1 = new Author("pepa");
    private Author author2 = new Author("test");
    private Author author3 = new Author("test2");
    private Image image1 = new Image(author1, "url", "name");
    private Image image2 = new Image(author2, "url2", "name2");

    @Test
    public void testCreateRetrieve() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);

        List<Image> images1 = imageService.getAllImages();
        assertNotNull("Should not be null", images1);
        assertEquals("Should be 1 image.", 1, images1.size());
        assertEquals("Retrieved should be equal inserted", image1, images1.get(0));

        imageService.saveOrUpdate(image2);
        List<Image> images2 = imageService.getAllImages();
        assertNotNull("Should not be null", images2);
        assertEquals("Should be 2 images.", 2, images2.size());
    }

    @Test
    public void testUpdate(){
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        Image retrieved1 = imageService.getImage(image1.getImageId());

        image1.setName("updateName");
        imageService.saveOrUpdate(image1);
        Image retrieved2 = imageService.getImage(image1.getImageId());

        assertNotEquals("Image should have different update dates", retrieved1.getUpdated(), retrieved2.getUpdated());
    }

    @Test
    public void testDelete(){
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        Image retrieved1 = imageService.getImage(image1.getImageId());
        assertNotNull("Image with ID " + image1.getImageId() + " should not be null", retrieved1);

        imageService.deleteImage(image1.getImageId());

        Image retrieved2 = imageService.getImage(image1.getImageId());
        assertNull("Image with ID " + image1.getImageId() + " should be null", retrieved2);
    }

    @Test
    public void testGetById() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        Image retrieved1 = imageService.getImage(image1.getImageId());
        assertNotNull("Should not be null", retrieved1);
        assertEquals("Images should match", image1, retrieved1);
    }

    @Test
    public void testGetByAuthor() {
        authorService.create(author1);
        authorService.create(author2);

        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        List<Image> images1 = imageService.getImagesByAuthor(author1);
        assertNotNull("Should not be null", images1);
        assertEquals("Author1 should have 1 image", 1, images1.size());

        List<Image> images2 = imageService.getImagesByAuthor(author2);
        assertNotNull("Should not be null", images2);
        assertEquals("Author2 should have 1 image", 1, images2.size());

        List<Image> images3 = imageService.getImagesByAuthor(author3);
        assertEquals("Author3 should not have images", null, images3);
    }

    @Test
    public void testGetByName() {
        authorService.create(author1);
        authorService.create(author2);

        imageService.create(image1);
        imageService.create(image2);

        List<Image> images1 = imageService.getImagesByName(image1.getName());
        assertNotNull("Should not be null", images1);
        assertEquals("Should be 1 for name: " + image1.getName(), 1, images1.size());

        List<Image> images2 = imageService.getImagesByName(image2.getName());
        assertNotNull("Should not be null", images2);
        assertEquals("Should be 1 for name: " + image2.getName(), 1, images2.size());

        List<Image> images3 = imageService.getImagesByName("random");
        assertEquals("Should be null for name: random", null, images3);
    }

    @Test
    public void testGetByTag(){
        authorService.create(author1);
        imageService.saveOrUpdate(image1);
        Tag tag1 = new Tag(image1.getImageId(), "testTag");
        Tag tag2 = new Tag(image1.getImageId(), "testTag2");
        tagService.create(tag1);

        List<String> tags1 = new ArrayList<>();
        tags1.add(tag1.getTag());
        tags1.add(tag2.getTag());
        List<Image> images1 = imageService.getImagesByTags(tags1);
        assertNotNull("Should not be null", images1);
        assertEquals("For tag " + tag1.getTag() + " should be 1 image", 1, images1.size());

        List<String> tags2 = new ArrayList<>();
        tags2.add("randomTag");
        List<Image> images2 = imageService.getImagesByTags(tags2);
        assertNull("Null should be retrieved", images2);
    }
}
