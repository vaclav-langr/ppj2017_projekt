package cz.tul.data;

/**
 * Created by vaclavlangr on 03.04.17.
 */
public class CommentRating {
    private int comment_id;
    private String comment_rating_author;
    private int value;

    public CommentRating(){

    }

    public CommentRating(int comment_id, String comment_rating_author, int value) {
        this.comment_id = comment_id;
        this.comment_rating_author = comment_rating_author;
        this.value = value;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_rating_author() {
        return comment_rating_author;
    }

    public void setComment_rating_author(String comment_rating_author) {
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
                "comment_id=" + comment_id +
                ", comment_rating_author='" + comment_rating_author + '\'' +
                ", value=" + value +
                '}';
    }
}
