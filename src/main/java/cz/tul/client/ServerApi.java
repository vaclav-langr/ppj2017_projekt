package cz.tul.client;

import cz.tul.data.Author;
import cz.tul.data.Image;
import cz.tul.data.Tag;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit.http.*;
import retrofit.mime.TypedFile;

import java.util.List;

/**
 * Created by vaclavlangr on 17.05.2017.
 */
public interface ServerApi {
    public static final String AUTHORS_PATH = "/authors";
    public static final String AUTHOR_PATH = AUTHORS_PATH + "/{userName}" ;
    public static final String IMAGES_PATH = "/images";
    public static final String IMAGE_PATH = IMAGES_PATH + "/{id}";
    public static final String TAGS_PATH = "/tags";
    public static final String TAG_PATH = TAGS_PATH + "/{tag}";


    @GET(AUTHORS_PATH)
    public List<Author> showAuthors();

    @POST(AUTHORS_PATH)
    public void addAuthor(@Body Author author);

    @GET(AUTHOR_PATH)
    public Author getAuthor(@Path("userName") String userName);

    @DELETE(AUTHOR_PATH)
    public void deleteAuthor(@Path("userName") String userNname);



    @GET(IMAGES_PATH)
    public List<Image> showImages();

    @POST(IMAGES_PATH)
    public void addImage(@Body Image image);

    @GET(IMAGE_PATH)
    public Image getImage(@Path("id") Long id);

    @GET(IMAGE_PATH + "/data")
    public byte[] getImageData(@Path("id") Long id);

    @PUT(IMAGE_PATH)
    public void updateImage(@Body Image image);

    @DELETE(IMAGE_PATH)
    public void deleteImage(@Path("id") Long id);

    

    @GET(TAGS_PATH)
    public List<Tag> showTags();

    @GET(IMAGE_PATH + TAGS_PATH)
    public List<Tag> showImageTags(@Path("id") Long imageId);

    @POST(IMAGE_PATH + TAGS_PATH)
    public void addTag(@Path("id") Long imageId, @Body String tag);

    @POST(IMAGE_PATH + TAG_PATH)
    public void deleteTag(@Path("id") Long imageId, @Path("tag") String tag);
}
