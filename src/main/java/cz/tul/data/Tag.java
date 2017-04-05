package cz.tul.data;

/**
 * Created by vaclavlangr on 03.04.17.
 */
public class Tag {
    private int image_id;
    private String tag;

    public Tag(){

    }

    public Tag(int image_id, String tag) {
        this.image_id = image_id;
        this.tag = tag;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
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
                "image_id=" + image_id +
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
        if (getImage_id() != temp.getImage_id()) {
            return false;
        }
        if (!getTag().equals(temp.getTag())) {
            return false;
        }
        return true;
    }
}
