package cz.tul;

import cz.tul.data.Author;
import cz.tul.data.AuthorDao;
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
import static org.junit.Assert.assertFalse;

/**
 * Created by vaclavlangr on 04.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorDaoTests {

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testAuthors(){
        authorDao.deleteAuthors();

        Author a = new Author();
        a.setUser_name("pepa");
        assertTrue("Author creation should return true",authorDao.create(a));

        List<Author> authors = authorDao.getAllAuthors();
        assertEquals("Number of authors should be 1", 1, authors.size());

        assertTrue("Author should exist", authorDao.exists(a.getUser_name()));

        assertEquals("Created author should be identical to retrieved user", a, authors.get(0));
    }
}
