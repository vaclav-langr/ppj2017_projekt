package cz.tul.client;

import cz.tul.data.Author;
import cz.tul.data.Image;
import retrofit.http.*;

import java.util.List;

/**
 * Created by vaclavlangr on 17.05.2017.
 */
public interface ServerApi {
    public static final String AUTHORS_PATH = "/authors";
    public static final String AUTHOR_PATH = AUTHORS_PATH + "/{username}" ;
    public static final String IMAGES_PATH = "/images";
    public static final String IMAGE_PATH = IMAGES_PATH + "/{id}";

    @GET(AUTHORS_PATH)
    public List<Author> showAuthors();

    @POST(AUTHORS_PATH)
    public void addAuthor(@Body Author author);

    @GET(AUTHOR_PATH)
    public Author getAuthor(@Path("username") String username);

    @DELETE(AUTHOR_PATH)
    public void deleteAuthor(@Path("username") String username);



    @GET(IMAGES_PATH)
    public List<Image> showImages();

    @POST(IMAGES_PATH)
    public void addImages(@Body Image image);

    @GET(IMAGE_PATH)
    public Image getImage(@Path("id") Long id);

    @PUT(IMAGE_PATH)
    public void updateImage(@Body Image image, @Path("id") Long id);

    @DELETE(IMAGE_PATH)
    public void deleteImage(@Path("id") Long id);
}
