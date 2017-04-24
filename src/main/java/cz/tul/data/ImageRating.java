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
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        ImageRating temp = (ImageRating)obj;
        if (getImageId() != temp.getImageId()) {
            return false;
        }
        if (getImageRatingAuthor() != temp.getImageRatingAuthor()) {
            return false;
        }
        if (getValue() != temp.getValue()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
