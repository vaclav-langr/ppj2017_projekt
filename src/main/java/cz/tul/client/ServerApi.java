package cz.tul.client;

import cz.tul.data.Author;
import retrofit.http.*;

import java.util.List;

/**
 * Created by vaclavlangr on 17.05.2017.
 */
public interface ServerApi {
    public static final String AUTHORS_PATH = "/authors";
    public static final String AUTHOR_PATH = AUTHORS_PATH + "/{username}" ;

    @GET(AUTHORS_PATH)
    public List<Author> showAuthors();

    @POST(AUTHORS_PATH)
    public void addAuthor(@Body Author author);

    @GET(AUTHOR_PATH)
    public Author getAuthor(@Path("username") String username);

    @DELETE(AUTHOR_PATH)
    public void deleteAuthor(@Path("username") String username);
}
