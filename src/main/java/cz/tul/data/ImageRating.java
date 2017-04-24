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
    @ManyToOne
    @JoinColumn(name="image_id")
    private Image image;

    @Id
    @ManyToOne
    @JoinColumn(name="image_rating_author")
    private Author imageRatingAuthor;

    @Column(name="value")
    private int value;

    public ImageRating() {}

    public ImageRating(Image image, Author imageRatingAuthor, int value) {
        this.image = image;
        this.imageRatingAuthor = imageRatingAuthor;
        this.value = value;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Author getImageRatingAuthor() {
        return imageRatingAuthor;
    }

    public void setImageRatingAuthor(Author imageRatingAuthor) {
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
                "image_id=" + image +
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
        if (!getImage().equals(temp.getImage())) {
            return false;
        }
        if (!getImageRatingAuthor().equals(temp.getImageRatingAuthor())) {
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
