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
    private int imageId;

    @Id
    @Column(name="image_rating_author")
    private int imageRatingAuthor;

    @Column(name="value")
    private int value;

    public ImageRating() {}

    public ImageRating(int imageId, int imageRatingAuthor, int value) {
        this.imageId = imageId;
        this.imageRatingAuthor = imageRatingAuthor;
        this.value = value;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImage(int imageId) {
        this.imageId = imageId;
    }

    public int getImageRatingAuthor() {
        return imageRatingAuthor;
    }

    public void setImageRatingAuthor(int imageRatingAuthor) {
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
        if (imageRatingAuthor != that.imageRatingAuthor) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        int result = imageId;
        result = 31 * result + imageRatingAuthor;
        result = 31 * result + value;
        return result;
    }
}
