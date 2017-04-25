package cz.tul.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="ImageRating")
@IdClass(ImageRatingId.class)
public class ImageRating implements Serializable {
    @Id
    @Column(name="image_id")
    private long imageId;

    @Id
    @Column(name="image_rating_author")
    private String imageRatingAuthor;

    @Column(name="value")
    private int value;

    public ImageRating() {}

    public ImageRating(long imageId, String imageRatingAuthor, int value) {
        this.imageId = imageId;
        this.imageRatingAuthor = imageRatingAuthor;
        this.value = value;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getImageRatingAuthor() {
        return imageRatingAuthor;
    }

    public void setImageRatingAuthor(String imageRatingAuthor) {
        this.imageRatingAuthor = imageRatingAuthor;
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
                "image_id=" + imageId +
                ", imageRatingAuthor='" + imageRatingAuthor + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageRating that = (ImageRating) o;

        if (imageId != that.imageId) return false;
        if (value != that.value) return false;
        return imageRatingAuthor.equals(that.imageRatingAuthor);

    }

    @Override
    public int hashCode() {
        int result = (int) (imageId ^ (imageId >>> 32));
        result = 31 * result + imageRatingAuthor.hashCode();
        result = 31 * result + value;
        return result;
    }
}
