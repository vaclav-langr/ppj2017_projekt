package cz.tul.data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;

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
    private int imageId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public Image(int imageId, Author author, String url, String name, Date created, Date updated) {
        this.imageId = imageId;
        this.author = author;
        this.url = url;
        this.name = name;
        this.created = created;
        this.updated = updated;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
                "imageId=" + imageId +
                ", author=" + author +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (imageId != image.imageId) return false;
        if (!author.equals(image.author)) return false;
        if (!url.equals(image.url)) return false;
        return name.equals(image.name);
    }

    @Override
    public int hashCode() {
        int result = imageId;
        result = 31 * result + author.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @PrePersist
    public void prePersist(){
        setCreated(new Date());
        setUpdated(new Date());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdated(new Date());
    }
}
