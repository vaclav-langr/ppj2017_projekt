package cz.tul;

import cz.tul.data.*;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by vaclavlangr on 04.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScenarioTests {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CommentRatingDao commentRatingDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private ImageRatingDao imageRatingDao;

    @Autowired
    private TagDao tagDao;

    @Autowired

    @Test
    public void testScenario1(){
        tagDao.deleteTags();
        imageRatingDao.deleteImageRatings();
        commentRatingDao.deleteCommentRatings();
        commentDao.deleteComments();
        imageDao.deleteImages();
        authorDao.deleteAuthors();

        Author a1 = new Author("pepa");
        assertTrue("Author 1 should be created", authorDao.create(a1));
        assertEquals("Author 1 should be equal", a1, authorDao.getAllAuthors().get(0));

        Author a2 = new Author("test_user");
        assertTrue("Author 2 should be created", authorDao.create(a2));
        assertEquals("Author 2 should be equal", a2, authorDao.getAllAuthors().get(1));

        Image image = new Image(a1.getUser_name(), "url");
        assertTrue("Image should be created", imageDao.create(image));
        assertEquals("Image should be equal", image, imageDao.getAllImages().get(0));
        image = imageDao.getAllImages().get(0);

        image.setName("Name");
        assertTrue("Image update should be created", imageDao.update(image));
        assertNotEquals("Image should have updated date", null, imageDao.getAllImages().get(0).getUpdated());

        Tag tag = new Tag(image.getImage_id(), "TestingTag");
        assertTrue("Tag should be created", tagDao.create(tag));
        assertEquals("Tag should be equal", tag, tagDao.getAllTags().get(0));

        ImageRating imageRating = new ImageRating(image.getImage_id(), a2.getUser_name(), 1);
        assertTrue("ImageRating should be created", imageRatingDao.create(imageRating));
        assertEquals("ImageRating should be equal", imageRating, imageRatingDao.getAllImageRatings().get(0));

        Comment comment = new Comment(image.getImage_id(), "Testing comment", a2.getUser_name());
        assertTrue("Comment should be created", commentDao.create(comment));
        assertEquals("Comment should be equal", comment, commentDao.getAllComments().get(0));
        comment = commentDao.getAllComments().get(0);

        comment.setComment_text("Testing comment 2");
        assertTrue("Comment update should be created", commentDao.update(comment));
        assertNotEquals("Comment should have updated date", null, commentDao.getAllComments().get(0).getUpdated());

        CommentRating commentRating = new CommentRating(comment.getComment_id(), a1.getUser_name(), -1);
        assertTrue("CommentRating should be created", commentRatingDao.create(commentRating));
        assertEquals("CommentRating should be equal", commentRating, commentRatingDao.getAllCommentRatings().get(0));
    }
}
