package cz.tul.data;

import java.io.Serializable;

/**
 * Created by vaclavlangr on 24.04.17.
 */
public class ImageRatingId implements Serializable {
    long imageId;
    String imageRatingAuthor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageRatingId that = (ImageRatingId) o;

        if (imageId != that.imageId) return false;
        return imageRatingAuthor.equals(that.imageRatingAuthor);

    }

    @Override
    public int hashCode() {
        int result = (int) (imageId ^ (imageId >>> 32));
        result = 31 * result + imageRatingAuthor.hashCode();
        return result;
    }
}
