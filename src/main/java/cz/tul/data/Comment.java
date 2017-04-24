package cz.tul.data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="Comment")
public class Comment {
    @ManyToOne
    @JoinColumn(name="image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name="comment_author")
    private Author commentAuthor;

    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private int commentId;

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;

    @Column(name="comment_text")
    private String commentText;

    public Comment(){}

    public Comment(Image image, String commentText, Author commentAuthor) {
        this.image = image;
        this.commentText = commentText;
        this.commentAuthor = commentAuthor;
    }

    public Comment(Image image, int commentId, Date created, Date updated, String commentText, Author commentAuthor) {
        this.image = image;
        this.commentId = commentId;
        this.created = created;
        this.updated = updated;
        this.commentText = commentText;
        this.commentAuthor = commentAuthor;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Author getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(Author commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "image_id=" + image +
                ", commentId=" + commentId +
                ", created=" + created +
                ", updated=" + updated +
                ", commentText='" + commentText + '\'' +
                ", author='" + commentAuthor + '\'' +
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
        if (!getImage().equals(temp.getImage())) {
            return false;
        }
        if (!getCommentAuthor().equals(temp.getCommentAuthor())) {
            return false;
        }
        if (!getCommentText().equals(temp.getCommentText())) {
            return false;
        }
        return true;
    }

    @PrePersist
    public void prePersist(){
        setCreated(new Date());
        setUpdated(new Date());
    }

    @PreUpdate
    public void preUpdate(){
        setUpdated(new Date());
    }
}
