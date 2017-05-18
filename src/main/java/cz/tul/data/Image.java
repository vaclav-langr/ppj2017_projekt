package cz.tul.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="Image")
public class Image {

    @Id
    @GeneratedValue
    @Column(name="image_id")
    private long imageId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="image_author")
    private Author author;

    @Column(name="url")
    private String url;

    @Column(name="name")
    private String name;

    @Column(name="created")
    private LocalDateTime created;

    @Column(name="updated")
    private LocalDateTime updated;

    @OneToMany(mappedBy = "imageId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tag> tags;

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

    public Image(long imageId, Author author, String url, String name, LocalDateTime created, LocalDateTime updated) {
        this.imageId = imageId;
        this.author = author;
        this.url = url;
        this.name = name;
        this.created = created;
        this.updated = updated;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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
        int result = (int) (imageId ^ (imageId >>> 32));
        result = 31 * result + author.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @PrePersist
    public void prePersist(){
        setCreated(LocalDateTime.now());
        setUpdated(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdated(LocalDateTime.now());
    }
}
