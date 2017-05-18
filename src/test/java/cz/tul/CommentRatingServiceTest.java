package cz.tul;

import cz.tul.data.Author;
import cz.tul.data.Comment;
import cz.tul.data.CommentRating;
import cz.tul.data.Image;
import cz.tul.services.AuthorService;
import cz.tul.services.CommentRatingService;
import cz.tul.services.CommentService;
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
public class CommentRatingServiceTest {

    @Autowired
    AuthorService authorService;

    @Autowired
    ImageService imageService;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRatingService commentRatingService;

    @Before
    public void init() {
        commentRatingService.deleteAllCommentRatings();
        commentService.deleteComments();
        imageService.deleteImages();
        authorService.deleteAuthors();
    }

    private Author author1 = new Author("pepa");
    private Author author2 = new Author("test");
    private Image image1 = new Image(author1, "url", "name");
    private Image image2 = new Image(author2, "url2", "name2");
    private Comment comment1 = new Comment(image1.getImageId(), "Comment1", author1.getUserName());
    private Comment comment2 = new Comment(image1.getImageId(), "Comment1", author1.getUserName());
    private CommentRating commentRating1 = new CommentRating(comment1.getCommentId(), author2.getUserName(), 1);

    @Test
    public void testCreateRetrieve() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);
        comment1.setImageId(image1.getImageId());
        commentService.saveOrUpdate(comment1);

        commentRating1.setCommentId(comment1.getCommentId());
        commentRatingService.create(commentRating1);
        List<CommentRating> commentRatings = commentRatingService.getAllRatings();
        assertNotNull("Should not be null", commentRatings);
        assertEquals("Should be 1 comment rating", 1, commentRatings.size());
        assertEquals("Comment rating should match", commentRating1, commentRatings.get(0));
    }

    @Test
    public void testUpdate() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);
        comment1.setImageId(image1.getImageId());
        commentService.saveOrUpdate(comment1);

        commentRating1.setCommentId(comment1.getCommentId());
        commentRatingService.create(commentRating1);
        List<CommentRating> commentRatings1 = commentRatingService.getAllRatings();
        assertNotNull("Should not be null", commentRatings1);
        assertEquals("Should be 1 comment rating", 1, commentRatings1.size());

        commentRating1.setValue(-1);
        commentRatingService.updateCommentRating(commentRating1);
        List<CommentRating> commentRatings2 = commentRatingService.getAllRatings();
        assertNotNull("Should not be null", commentRatings2);
        assertEquals("Should be 1 comment rating", 1, commentRatings2.size());

        assertNotEquals("Should not equal", commentRatings1, commentRatings2);
    }

    @Test
    public void testDelete() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);
        comment1.setImageId(image1.getImageId());
        commentService.saveOrUpdate(comment1);

        commentRating1.setCommentId(comment1.getCommentId());
        commentRatingService.create(commentRating1);
        List<CommentRating> commentRatings1 = commentRatingService.getAllRatings();
        assertNotNull("Should not be null", commentRatings1);
        assertEquals("Should be 1 comment rating", 1, commentRatings1.size());

        commentRatingService.deleteCommentRating(commentRating1);
        List<CommentRating> commentRatings2 = commentRatingService.getAllRatings();
        assertNull("Should be null", commentRatings2);
    }

    @Test
    public void testCommentRatings() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);
        comment1.setImageId(image1.getImageId());
        commentService.saveOrUpdate(comment1);
        comment2.setImageId(image1.getImageId());
        commentService.saveOrUpdate(comment2);

        commentRating1.setCommentId(comment1.getCommentId());
        commentRatingService.create(commentRating1);
        List<CommentRating> commentRatings1 = commentRatingService.getCommentRatings(comment1.getCommentId());
        assertNotNull("Should not be null", commentRatings1);
        assertEquals("Should be 1 comment rating", 1, commentRatings1.size());

        List<CommentRating> commentRatings2 = commentRatingService.getCommentRatings(comment2.getCommentId());
        assertNull("Should be null", commentRatings2);
    }
}
