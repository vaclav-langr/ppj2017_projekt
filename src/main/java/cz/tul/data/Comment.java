package cz.tul.data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="Comment")
public class Comment {
    @Id
    @ManyToOne
    @JoinColumn(name="image_id")
    private Image image;

    @Id
    @ManyToOne
    @JoinColumn(name="comment_author")
    private Author comment_author;

    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private int comment_id;

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;

    @Column(name="comment_text")
    private String comment_text;

    public Comment(){

    }

    public Comment(Image image, String comment_text, Author comment_author) {
        this.image = image;
        this.comment_text = comment_text;
        this.comment_author = comment_author;
    }

    public Comment(Image image, int comment_id, Date created, Date updated, String comment_text, Author comment_author) {
        this.image = image;
        this.comment_id = comment_id;
        this.created = created;
        this.updated = updated;
        this.comment_text = comment_text;
        this.comment_author = comment_author;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Author getComment_author() {
        return comment_author;
    }

    public void setComment_author(Author comment_author) {
        this.comment_author = comment_author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "image_id=" + image +
                ", comment_id=" + comment_id +
                ", created=" + created +
                ", updated=" + updated +
                ", comment_text='" + comment_text + '\'' +
                ", author='" + comment_author + '\'' +
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
        Comment temp = (Comment)obj;
        if (getImage() != temp.getImage()) {
            return false;
        }
        if (!getComment_author().equals(temp.getComment_author())) {
            return false;
        }
        if (!getComment_text().equals(temp.getComment_text())) {
            return false;
        }
        return true;
    }
}
