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
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        CommentRating temp = (CommentRating)obj;
        if (getCommentId() != temp.getCommentId()) {
            return false;
        }
        if (getCommentRatingAuthor() != temp.getCommentRatingAuthor()) {
            return false;
        }
        if (getValue() != temp.getValue()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
