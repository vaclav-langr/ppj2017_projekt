package cz.tul.data;

import java.util.Date;

/**
 * Created by vaclavlangr on 03.04.17.
 */
public class Image {
    private int image_id;
    private String image_author, url, name;
    private Date created, updated;

    public Image(){

    }

    public Image(String image_author, String url) {
        this.image_author = image_author;
        this.url = url;
    }

    public Image(String image_author, String url, String name) {
        this.image_author = image_author;
        this.url = url;
        this.name = name;
    }

    public Image(int image_id, String image_author, String url, String name, Date created, Date updated) {
        this.image_id = image_id;
        this.image_author = image_author;
        this.url = url;
        this.name = name;
        this.created = created;
        this.updated = updated;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getImage_author() {
        return image_author;
    }

    public void setImage_author(String image_author) {
        this.image_author = image_author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Image{" +
                "image_id=" + image_id +
                ", image_author='" + image_author + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", updated=" + updated +
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
        Image temp = (Image)obj;
        if (!getUrl().equals(temp.getUrl())){
            return false;
        }
        if (getName() == null) {
            if (temp.getName() != null) {
                return false;
            }
        } else {
            if (temp.getName() == null) {
                return false;
            } else {
                if (!getName().equals(temp.getName())) {
                    return false;
                }
            }
        }
        if (!getImage_author().equals(temp.getImage_author())) {
            return false;
        }
        return true;
    }
}
