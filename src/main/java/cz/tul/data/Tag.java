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
    @JoinColumn(name="image_id")
    private int imageId;

    @Id
    @Column(name="tag")
    private String tag;

    public Tag(){}

    public Tag(int imageId, String tag) {
        this.imageId = imageId;
        this.tag = tag;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
                "imageId=" + imageId +
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
        if (getImageId() != temp.getImageId()) {
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
