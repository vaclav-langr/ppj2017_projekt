package cz.tul;

import cz.tul.data.*;
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
 * Created by vaclavlangr on 04.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageRatingDaoTests {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private ImageRatingDao imageRatingDao;

    @Test
    public void testAuthors(){
        imageRatingDao.deleteImageRatings();
        imageDao.deleteImages();
        authorDao.deleteAuthors();

        Author a1 = new Author("pepa");
        authorDao.create(a1);

        Author a2 = new Author("test_user");
        authorDao.create(a2);

        Image image = new Image(a1.getUser_name(), "url");
        imageDao.create(image);
        image = imageDao.getAllImages().get(0);

        ImageRating imageRating = new ImageRating(image.getImage_id(),a2.getUser_name(),-1);
        assertTrue("Image rating should be created", imageRatingDao.create(imageRating));

        List<ImageRating> imageRatings = imageRatingDao.getAllImageRatings();
        assertEquals("Image rating should contain 1 rating", 1, imageRatings.size());

        assertTrue("Image rating should exist", imageRatingDao.exists(imageRating.getImage_id(), imageRating.getImage_rating_author()));

        assertEquals("Image rating should be equal", imageRating, imageRatings.get(0));
    }
}
