package cz.tul.data;

import javax.persistence.*;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="Tag")
public class Tag {
    @ManyToOne
    @JoinColumn(name="image_id")
    private Image image;

    @Id
    @Column(name="tag")
    private String tag;

    public Tag(){

    }

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
        if (getImage() != temp.getImage()) {
            return false;
        }
        if (!getTag().equals(temp.getTag())) {
            return false;
        }
        return true;
    }
}
