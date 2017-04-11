package cz.tul.data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="Image")
public class Image {

    @Id
    @GeneratedValue
    @Column(name="image_id")
    private int image_id;

    @ManyToOne
    @JoinColumn(name="image_author")
    private Author author;

    @Column(name="url")
    private String url;

    @Column(name="name")
    private String name;

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;

    public Image(){}

    public Image(Author author, String url) {
        this.author = author;
        this.url = url;
    }

    public Image(Author author, String url, String name) {
        this.author = author;
        this.url = url;
        this.name = name;
    }

    public Image(int image_id, Author author, String url, String name, Date created, Date updated) {
        this.image_id = image_id;
        this.author = author;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
                ", image_author='" + author + '\'' +
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
        if (!getAuthor().equals(temp.getAuthor())) {
            return false;
        }
        return true;
    }
}
