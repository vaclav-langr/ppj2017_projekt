package cz.tul.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="Comment_Author")
@IdClass(CommentRatingId.class)
public class CommentRating implements Serializable {
    @Id
    @Column(name="comment_id")
    private long commentId;

    @Id
    @Column(name="comment_rating_author")
    private String commentRatingAuthor;

    @Column(name="value")
    private int value;

    public CommentRating(){}

    public CommentRating(long commentId, String commentRatingAuthor, int value) {
        this.commentId = commentId;
        this.commentRatingAuthor = commentRatingAuthor;
        this.value = value;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentRatingAuthor() {
        return commentRatingAuthor;
    }

    public void setCommentRatingAuthor(String commentRatingAuthor) {
        this.commentRatingAuthor = commentRatingAuthor;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CommentRating{" +
                "comment_id=" + commentId +
                ", commentRatingAuthor='" + commentRatingAuthor + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentRating that = (CommentRating) o;

        if (commentId != that.commentId) return false;
        if (value != that.value) return false;
        return commentRatingAuthor.equals(that.commentRatingAuthor);

    }

    @Override
    public int hashCode() {
        int result = (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + commentRatingAuthor.hashCode();
        result = 31 * result + value;
        return result;
    }
}
