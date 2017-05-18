package cz.tul;

import cz.tul.data.Author;
import cz.tul.services.AuthorService;
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
 * Created by vaclavlangr on 25.04.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    private Author author1 = new Author("pepa");
    private Author author2 = new Author("test");

    @Before
    public void init(){
        authorService.deleteAuthors();
    }

    @Test
    public void testCreateRetrieve(){
        authorService.create(author1);

        List<Author> authors1 = authorService.getAllAuthors();
        assertNotNull("Should not be null", authors1);
        assertEquals("One author should have been created and retrieved", 1, authors1.size());
        assertEquals("Inserted author should match retrieved", author1, authors1.get(0));

        authorService.create(author2);

        List<Author> authors2 = authorService.getAllAuthors();
        assertNotNull("Should not be null", authors2.size());
        assertEquals("Should be two retrieved authors", 2, authors2.size());
    }

    @Test
    public void testExists() {
        authorService.create(author1);
        authorService.create(author2);

        assertTrue("Author should exist", authorService.exists(author1.getUserName()));
        assertFalse("Author should not exist", authorService.exists("abcdefg"));
    }
}
