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
public class CommentDaoTests {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private ImageDao imageDao;

    @Test
    public void testComments(){
        commentDao.deleteComments();
        imageDao.deleteImages();
        authorDao.deleteAuthors();

        Author a = new Author("pepa");
        authorDao.create(a);

        Image i = new Image(a, "url");
        imageDao.create(i);
        i = imageDao.getAllImages().get(0);

        Comment c = new Comment(i, "comment", a);
        commentDao.create(c);

        List<Comment> comments = commentDao.getAllComments();
        assertEquals("Number of comments should be 1", 1, comments.size());

        assertTrue("Comment should exist", commentDao.exists(comments.get(0).getComment_id()));

        assertEquals("Created comment should be identical to retrieved comment", c, comments.get(0));
    }
}
