package cz.tul;

import cz.tul.app.Main;
import cz.tul.data.Author;
import cz.tul.data.AuthorDao;
import cz.tul.data.Image;
import cz.tul.data.ImageDao;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by vaclavlangr on 04.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageDaoTests {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private ImageDao imageDao;

    @Test
    public void testImages(){
        imageDao.deleteImages();
        authorDao.deleteAuthors();

        Author a = new Author("pepa");
        authorDao.create(a);

        Image image = new Image(a, "url");
        imageDao.create(image);

        List<Image> images = imageDao.getAllImages();
        assertEquals("Image should contain 1 image", 1, images.size());

        assertTrue("Image should exist", imageDao.exists(images.get(0).getImageId()));

        assertEquals("Image should be same", image, images.get(0));

        image = images.get(0);
        image.setName("name");
        imageDao.update(image);
        images = imageDao.getAllImages();

        assertNotEquals("Image should have updated date", null, images.get(0).getUpdated());
    }
}
