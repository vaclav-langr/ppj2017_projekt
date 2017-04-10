package cz.tul.data;

import javax.persistence.*;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="ImageRating")
public class ImageRating {
    @Id
    @ManyToOne
    @JoinColumn(name="image_id")
    private Image image;

    @Id
    @ManyToOne
    @JoinColumn(name="image_rating_author")
    private Author image_rating_author;

    @Column(name="value")
    private int value;

    public ImageRating() {

    }

    public ImageRating(Image image, Author image_rating_author, int value) {
        this.image = image;
        this.image_rating_author = image_rating_author;
        this.value = value;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Author getImage_rating_author() {
        return image_rating_author;
    }

    public void setImage_rating_author(Author image_rating_author) {
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
                "image_id=" + image +
                ", image_rating_author='" + image_rating_author + '\'' +
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
        if (getImage() != temp.getImage()) {
            return false;
        }
        if (!getImage_rating_author().equals(temp.getImage_rating_author())) {
            return false;
        }
        if (getValue() != temp.getValue()) {
            return false;
        }
        return true;
    }
}
