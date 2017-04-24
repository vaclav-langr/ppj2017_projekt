package cz.tul;

import cz.tul.app.Main;
import cz.tul.data.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    @Test
    public void testScenario1(){
        tagDao.deleteTags();
        imageRatingDao.deleteImageRatings();
        commentRatingDao.deleteCommentRatings();
        commentDao.deleteComments();
        imageDao.deleteImages();
        authorDao.deleteAuthors();

        Author a1 = new Author("pepa");
        authorDao.create(a1);
        assertEquals("Author 1 should be equal", a1, authorDao.getAllAuthors().get(0));

        Author a2 = new Author("test_user");
        authorDao.create(a2);
        assertEquals("Author 2 should be equal", a2, authorDao.getAllAuthors().get(1));

        Image image = new Image(a1, "url");
        imageDao.create(image);
        assertEquals("Image should be equal", image, imageDao.getAllImages().get(0));
        image = imageDao.getAllImages().get(0);

        image.setName("Name");
        imageDao.update(image);
        assertNotEquals("Image should have updated date", null, imageDao.getAllImages().get(0).getUpdated());

        Tag tag = new Tag(image, "TestingTag");
        tagDao.create(tag);
        assertEquals("Tag should be equal", tag, tagDao.getAllTags().get(0));

        ImageRating imageRating = new ImageRating(image, a2, 1);
        imageRatingDao.create(imageRating);
        assertEquals("ImageRating should be equal", imageRating, imageRatingDao.getAllImageRatings().get(0));

        Comment comment = new Comment(image, "Testing comment", a2);
        commentDao.create(comment);
        assertEquals("Comment should be equal", comment, commentDao.getAllComments().get(0));
        comment = commentDao.getAllComments().get(0);

        comment.setCommentText("Testing comment 2");
        commentDao.update(comment);
        assertNotEquals("Comment should have updated date", null, commentDao.getAllComments().get(0).getUpdated());

        CommentRating commentRating = new CommentRating(comment, a1, -1);
        commentRatingDao.create(commentRating);
        assertEquals("CommentRating should be equal", commentRating, commentRatingDao.getAllCommentRatings().get(0));
    }
}
