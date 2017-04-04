package cz.tul.data;

/**
 * Created by vaclavlangr on 03.04.17.
 */
public class ImageRating {
    private int image_id;
    private String image_rating_author;
    private int value;

    public ImageRating() {

    }

    public ImageRating(int image_id, String image_rating_author, int value) {
        this.image_id = image_id;
        this.image_rating_author = image_rating_author;
        this.value = value;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getImage_rating_author() {
        return image_rating_author;
    }

    public void setImage_rating_author(String image_rating_author) {
        this.image_rating_author = image_rating_author;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ImageRating{" +
                "image_id=" + image_id +
                ", image_rating_author='" + image_rating_author + '\'' +
                ", value=" + value +
                '}';
    }
}
