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
    @Column(name="image_id")
    private long imageId;

    @Id
    @Column(name="tag")
    private String tag;

    public Tag(){}

    public Tag(long imageId, String tag) {
        this.imageId = imageId;
        this.tag = tag;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag1 = (Tag) o;

        if (imageId != tag1.imageId) return false;
        return tag.equals(tag1.tag);

    }

    @Override
    public int hashCode() {
        int result = (int) (imageId ^ (imageId >>> 32));
        result = 31 * result + tag.hashCode();
        return result;
    }
}
