package cz.tul.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="Tag")
@IdClass(TagId.class)
public class Tag implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="image_id")
    private Image image;

    @Id
    @Column(name="tag")
    private String tag;

    public Tag(){}

    public Tag(Image image, String tag) {
        this.image = image;
        this.tag = tag;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "image_id=" + image +
                ", tag='" + tag + '\'' +
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
        Tag temp = (Tag)obj;
        if (!getImage().equals(temp.getImage())) {
            return false;
        }
        if (!getTag().equals(temp.getTag())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
