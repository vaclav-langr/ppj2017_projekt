package cz.tul.data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="Comment")
public class Comment {
    @Column(name="image_id")
    private long imageId;

    @Column(name="comment_author")
    private String commentAuthor;

    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private long commentId;

    @Column(name="created")
    private LocalDateTime created;

    @Column(name="updated")
    private LocalDateTime updated;

    @Column(name="comment_text")
    private String commentText;

    @OneToMany(mappedBy = "commentId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentRating> ratings;

    public Comment(){}

    public Comment(long imageId, String commentText, String commentAuthor) {
        this.imageId = imageId;
        this.commentText = commentText;
        this.commentAuthor = commentAuthor;
    }

    public Comment(long imageId, long commentId, LocalDateTime created, LocalDateTime updated, String commentText, String commentAuthor) {
        this.imageId = imageId;
        this.commentId = commentId;
        this.created = created;
        this.updated = updated;
        this.commentText = commentText;
        this.commentAuthor = commentAuthor;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public List<CommentRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<CommentRating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "image_id=" + imageId +
                ", commentId=" + commentId +
                ", created=" + created +
                ", updated=" + updated +
                ", commentText='" + commentText + '\'' +
                ", author='" + commentAuthor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (imageId != comment.imageId) return false;
        if (commentId != comment.commentId) return false;
        if (!commentAuthor.equals(comment.commentAuthor)) return false;
        return commentText.equals(comment.commentText);

    }

    @Override
    public int hashCode() {
        int result = (int) (imageId ^ (imageId >>> 32));
        result = 31 * result + commentAuthor.hashCode();
        result = 31 * result + (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + commentText.hashCode();
        return result;
    }

    @PrePersist
    public void prePersist(){
        setCreated(LocalDateTime.now());
        setUpdated(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(){
        setUpdated(LocalDateTime.now());
    }
}
