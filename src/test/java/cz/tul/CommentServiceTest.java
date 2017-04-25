package cz.tul;

import cz.tul.app.Main;
import cz.tul.data.Author;
import cz.tul.data.Comment;
import cz.tul.data.Image;
import cz.tul.services.AuthorService;
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
public class CommentServiceTest {
    @Autowired
    ImageService imageService;

    @Autowired
    AuthorService authorService;

    @Autowired
    CommentService commentService;

    @Before
    public void init() {
        commentService.deleteComments();;
        imageService.deleteImages();
        authorService.deleteAuthors();
    }

    private Author author1 = new Author("pepa");
    private Author author2 = new Author("test");
    private Image image1 = new Image(author1, "url", "name");
    private Image image2 = new Image(author2, "url2", "name2");
    private Comment comment1 = new Comment(image1.getImageId(), "Comment1", author1.getUserName());

    @Test
    public void testCreateRetrieve() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        comment1.setImageId(image1.getImageId());
        commentService.saveOrUpdate(comment1);
        List<Comment> comments1 = commentService.getAllComments();
        assertEquals("Should be 1 comment", 1, comments1.size());
        assertEquals("Retrieved comment should match", comment1, comments1.get(0));
    }

    @Test
    public void testUpdate() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        comment1.setImageId(image1.getImageId());

        commentService.saveOrUpdate(comment1);
        List<Comment> comments1 = commentService.getAllComments();
        assertEquals("Should be 1 comment", 1, comments1.size());

        comment1.setCommentText("newText");
        commentService.saveOrUpdate(comment1);
        List<Comment> comments2 = commentService.getAllComments();
        assertEquals("Should be 1 comment", 1, comments2.size());

        assertNotEquals("Should not match", comments1, comments2);
    }

    @Test
    public void testDelete() {
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        comment1.setImageId(image1.getImageId());

        commentService.saveOrUpdate(comment1);
        List<Comment> comments1 = commentService.getAllComments();
        assertEquals("Should be 1 comment", 1, comments1.size());

        commentService.deleteComment(comments1.get(0).getCommentId());

        List<Comment> comments2 = commentService.getAllComments();
        assertNull("Should be null", comments2);
    }

    @Test
    public void testCommentsForImage(){
        authorService.create(author1);
        authorService.create(author2);
        imageService.saveOrUpdate(image1);
        imageService.saveOrUpdate(image2);

        comment1.setImageId(image1.getImageId());

        commentService.saveOrUpdate(comment1);
        List<Comment> comments1 = commentService.getImageComments(image1.getImageId());
        assertEquals("Should be 1 comment", 1, comments1.size());

        List<Comment> comments2 = commentService.getImageComments(image2.getImageId());
        assertNull("Should be null", comments2);
    }
}
