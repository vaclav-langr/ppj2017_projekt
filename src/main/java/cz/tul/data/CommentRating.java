package cz.tul.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vaclavlangr on 03.04.17.
 */
@Entity
@Table(name="Comment_Author")
public class CommentRating implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;

    @Id
    @ManyToOne
    @JoinColumn(name="comment_rating_author")
    private Author comment_rating_author;

    @Column(name="value")
    private int value;

    public CommentRating(){}

    public CommentRating(Comment comment, Author comment_rating_author, int value) {
        this.comment = comment;
        this.comment_rating_author = comment_rating_author;
        this.value = value;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Author getComment_rating_author() {
        return comment_rating_author;
    }

    public void setComment_rating_author(Author comment_rating_author) {
        this.comment_rating_author = comment_rating_author;
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
                ", comment_rating_author='" + comment_rating_author + '\'' +
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
        if (!getComment_rating_author().equals(temp.getComment_rating_author())) {
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
