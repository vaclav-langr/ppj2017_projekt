package cz.tul;

import cz.tul.data.Author;
import cz.tul.data.Image;
import cz.tul.data.ImageRating;
import cz.tul.services.AuthorService;
import cz.tul.services.ImageRatingService;
import cz.tul.services.ImageService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vaclavlangr on 25.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageRatingServiceTest {

    @Autowired
    AuthorService authorService;

    @Autowired
    ImageService imageService;

    @Autowired
    ImageRatingService imageRatingService;

    @Before
    public void init() {
        imageRatingService.deleteImageRatings();
        imageService.deleteImages();
        authorService.deleteAuthors();
    }

    private Author author1 = new Author("pepa");
    private Author author2 = new Author("test");
    private Image image1 = new Image(author1, "url", "name");
    private Image image2 = new Image(author2, "url2", "name2");
    private ImageRating imageRating1 = new ImageRating(image1.getImageId(), author1.getUserName(), 1);

    @Test
    public void testCreateRetrieve() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        imageRating1.setImageId(image1.getImageId());
        imageRatingService.create(imageRating1);

        List<ImageRating> imageRatings = imageRatingService.getAllRatings();
        assertNotNull("Should not be null", imageRatings);
        assertEquals("Should be 1 image rating", 1, imageRatings.size());
        assertEquals("Image rating should match", imageRating1, imageRatings.get(0));
    }

    @Test
    public void testUpdate() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        imageRating1.setImageId(image1.getImageId());
        imageRatingService.create(imageRating1);

        List<ImageRating> imageRatings1 = imageRatingService.getAllRatings();
        assertNotNull("Should not be null", imageRatings1);
        assertEquals("Should be 1 image rating", 1, imageRatings1.size());

        imageRating1.setValue(-1);
        imageRatingService.update(imageRating1);

        List<ImageRating> imageRatings2 = imageRatingService.getAllRatings();
        assertNotNull("Should not be null", imageRatings2);
        assertEquals("Should be 1 image rating", 1, imageRatings2.size());

        assertNotEquals("Image ratings should not equal", imageRatings1, imageRatings2);
    }

    @Test
    public void testDelete() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        imageRating1.setImageId(image1.getImageId());
        imageRatingService.create(imageRating1);

        List<ImageRating> imageRatings1 = imageRatingService.getAllRatings();
        assertNotNull("Should not be null", imageRatings1);
        assertEquals("Should be 1 image rating", 1, imageRatings1.size());

        imageRatingService.deleteImageRating(imageRating1);
        List<ImageRating> imageRatings2 = imageRatingService.getAllRatings();
        assertNull("Should be null", imageRatings2);
    }

    @Test
    public void testImageRatings() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        imageRating1.setImageId(image1.getImageId());
        imageRatingService.create(imageRating1);

        List<ImageRating> imageRatings1 = imageRatingService.getImageRatings(image1.getImageId());
        assertNotNull("Should not be null", imageRatings1);
        assertEquals("Should be 1 image rating", 1, imageRatings1.size());

        List<ImageRating> imageRatings2 = imageRatingService.getImageRatings(image2.getImageId());
        assertNull("Should be null", imageRatings2);
    }
}
