package cz.tul.data;

import java.io.Serializable;

/**
 * Created by vaclavlangr on 24.04.17.
 */
public class CommentRatingId implements Serializable{
    long commentId;
    String commentRatingAuthor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentRatingId that = (CommentRatingId) o;

        if (commentId != that.commentId) return false;
        return commentRatingAuthor.equals(that.commentRatingAuthor);

    }

    @Override
    public int hashCode() {
        int result = (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + commentRatingAuthor.hashCode();
        return result;
    }
}
