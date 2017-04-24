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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by vaclavlangr on 04.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentRatingDaoTests {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private CommentRatingDao commentRatingDao;

    @Test
    public void testCommentRatings(){
        commentRatingDao.deleteCommentRatings();
        commentDao.deleteComments();
        imageDao.deleteImages();
        authorDao.deleteAuthors();

        Author a1 = new Author("pepa");
        authorDao.create(a1);
        Author a2 = new Author("test_user");
        authorDao.create(a2);

        Image i = new Image(a1, "url");
        imageDao.create(i);
        i = imageDao.getAllImages().get(0);

        Comment c = new Comment(i, "comment", a1);
        commentDao.create(c);
        c = commentDao.getAllComments().get(0);

        CommentRating commentRating = new CommentRating(c, a2, 1);
        commentRatingDao.create(commentRating);

        List<CommentRating> commentRatings = commentRatingDao.getAllCommentRatings();
        assertEquals("Comment rating should contain 1 rating", 1, commentRatings.size());

        assertTrue("Comment rating should exist", commentRatingDao.exists(commentRatings.get(0).getComment(), commentRatings.get(0).getCommentRatingAuthor()));

        assertEquals("Comment rating should be equal", commentRating, commentRatings.get(0));
    }
}
