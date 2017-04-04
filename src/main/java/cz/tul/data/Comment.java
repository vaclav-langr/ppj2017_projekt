package cz.tul.data;

import java.util.Date;

/**
 * Created by vaclavlangr on 03.04.17.
 */
public class Comment {
    private int image_id, comment_id;
    private Date created, updated;
    private String comment_text, comment_author;

    public Comment(){

    }

    public Comment(int image_id, int comment_id, Date created, Date updated, String comment_text, String comment_) {
        this.image_id = image_id;
        this.comment_id = comment_id;
        this.created = created;
        this.updated = updated;
        this.comment_text = comment_text;
        this.comment_author = comment_author;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
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

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "image_id=" + image_id +
                ", comment_id=" + comment_id +
                ", created=" + created +
                ", updated=" + updated +
                ", comment_text='" + comment_text + '\'' +
                ", author='" + comment_author + '\'' +
                '}';
    }
}
