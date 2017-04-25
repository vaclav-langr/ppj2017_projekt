package cz.tul.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="CommentRating")
@IdClass(CommentRatingId.class)
public class CommentRating implements Serializable {
    @Id
    @Column(name="comment_id")
    private int commentId;

    @Id
    @Column(name="comment_rating_author")
    private int commentRatingAuthor;

    @Column(name="value")
    private int value;

    public CommentRating(){}

    public CommentRating(int commentId, int commentRatingAuthor, int value) {
        this.commentId = commentId;
        this.commentRatingAuthor = commentRatingAuthor;
        this.value = value;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCommentRatingAuthor() {
        return commentRatingAuthor;
    }

    public void setCommentRatingAuthor(int commentRatingAuthor) {
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
        if (commentRatingAuthor != that.commentRatingAuthor) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + commentRatingAuthor;
        result = 31 * result + value;
        return result;
    }
}
