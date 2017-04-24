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
    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;

    @Id
    @ManyToOne
    @JoinColumn(name="comment_rating_author")
    private Author commentRatingAuthor;

    @Column(name="value")
    private int value;

    public CommentRating(){}

    public CommentRating(Comment comment, Author commentRatingAuthor, int value) {
        this.comment = comment;
        this.commentRatingAuthor = commentRatingAuthor;
        this.value = value;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Author getCommentRatingAuthor() {
        return commentRatingAuthor;
    }

    public void setCommentRatingAuthor(Author commentRatingAuthor) {
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
                "comment_id=" + comment +
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
        if (!getComment().equals(temp.getComment())) {
            return false;
        }
        if (!getCommentRatingAuthor().equals(temp.getCommentRatingAuthor())) {
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
