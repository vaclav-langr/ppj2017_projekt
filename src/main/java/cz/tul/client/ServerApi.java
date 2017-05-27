package cz.tul.client;

import cz.tul.data.*;
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
    public static final String IMAGE_FIND_NAME = IMAGES_PATH + "/name/{imageName}";
    public static final String IMAGE_FIND_AUTHOR = IMAGES_PATH + "/author/{userName}";
    public static final String IMAGE_FIND_TAG = IMAGES_PATH + "/tag/{tag}";
    public static final String IMAGE_PATH = IMAGES_PATH + "/{imageId}";
    public static final String TAGS_PATH = "/tags";
    public static final String TAG_PATH = TAGS_PATH + "/{tag}";
    public static final String IMAGE_RATINGS_PATH = "/imageRatings";
    public static final String COMMENTS_PATH = "/comments";
    public static final String COMMENT_PATH = COMMENTS_PATH + "/{commentId}";
    public static final String COMMENT_RATINGS_PATH = "/commentRatings";


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

    @Multipart
    @POST(IMAGES_PATH)
    public ImageStatus addImage(@Part("strAuthor") String strAuthor,
                                @Part("strImage") String strImage,
                                @Part("data") TypedFile imageData);

    @GET(IMAGE_PATH)
    public Image getImage(@Path("imageId") Long imageId);

    @Streaming
    @GET(IMAGE_PATH + "/data")
    public byte[] getImageData(@Path("imageId") Long imageId);

    @Multipart
    @PUT(IMAGE_PATH)
    public ImageStatus updateImage(@Path("imageId") Long imageId, @Part("strImage") String strImage);

    @DELETE(IMAGE_PATH)
    public void deleteImage(@Path("imageId") Long imageId);

    @GET(IMAGE_FIND_NAME)
    public List<Image> showByImageName(@Path("imageName") String imageName);

    @GET(IMAGE_FIND_AUTHOR)
    public List<Image> showByUserName(@Path("userName") String userName);

    @GET(IMAGE_FIND_TAG)
    public List<Image> showByTag(@Path("tag") String tag);

    

    @GET(TAGS_PATH)
    public List<Tag> showTags();

    @GET(IMAGE_PATH + TAGS_PATH)
    public List<Tag> showImageTags(@Path("imageId") Long imageId);

    @POST(IMAGE_PATH + TAGS_PATH)
    public void addTag(@Path("imageId") Long imageId, @Body String tag);

    @DELETE(IMAGE_PATH + TAG_PATH)
    public void deleteTag(@Path("imageId") Long imageId, @Path("tag") String tag);



    @GET(IMAGE_RATINGS_PATH)
    public List<ImageRating> showImageRatings();

    @GET(IMAGE_PATH + IMAGE_RATINGS_PATH)
    public List<ImageRating> showImageRatings(@Path("imageId") Long imageId);

    @POST(IMAGE_RATINGS_PATH)
    public void addImageRating(@Body ImageRating imageRating);

    @PUT(IMAGE_RATINGS_PATH)
    public void updateImageRating(@Body ImageRating imageRating);

    @DELETE(IMAGE_RATINGS_PATH)
    public void deleteImageRating(@Body ImageRating imageRating);



    @GET(COMMENTS_PATH)
    public List<Comment> showComments();

    @GET(IMAGE_PATH + COMMENTS_PATH)
    public List<Comment> showImageComments(@Path("imageId") Long imageId);

    @GET(COMMENT_PATH)
    public Comment showComment(@Path("commentId") Long commentId);

    @POST(IMAGE_PATH + COMMENTS_PATH)
    public void addComment(@Path("imageId") Long imageId, @Body Comment comment);

    @PUT(COMMENTS_PATH + COMMENT_PATH)
    public void updateComment(@Path("commentId") Long commentId, @Body Comment comment);

    @DELETE(COMMENTS_PATH + COMMENT_PATH)
    public void deleteComment(@Path("commentId") Long commentId);



    @GET(COMMENT_RATINGS_PATH)
    public List<CommentRating> showCommentRatings();

    @GET(COMMENT_PATH + COMMENT_RATINGS_PATH)
    public List<CommentRating> showCommentRatings(@Path("commentId") Long commentId);

    @POST(COMMENT_RATINGS_PATH)
    public void addCommentRating(@Body CommentRating commentRating);

    @PUT(COMMENT_RATINGS_PATH)
    public void updateCommentRating(@Body CommentRating commentRating);

    @DELETE(COMMENT_RATINGS_PATH)
    public void deleteCommentRating(@Body CommentRating commentRating);
}
