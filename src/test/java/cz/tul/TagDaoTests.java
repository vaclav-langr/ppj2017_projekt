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
public class TagDaoTests {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private TagDao tagDao;

    @Test
    public void testAuthors(){
        tagDao.deleteTags();
        imageDao.deleteImages();
        authorDao.deleteAuthors();

        Author a = new Author("pepa");
        authorDao.create(a);

        Image image = new Image(a, "url");
        imageDao.create(image);
        image = imageDao.getAllImages().get(0);

        Tag tag = new Tag(image, "Test");
        tagDao.create(tag);

        List<Tag> tags = tagDao.getAllTags();
        assertEquals("Tag should contain 1 tag", 1, tags.size());

        assertTrue("Tag should exist", tagDao.exists(image.getImage_id(), tag.getTag()));

        assertEquals("Tag should be equal", tag, tags.get(0));
    }
}
