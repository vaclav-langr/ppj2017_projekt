package cz.tul.data;

import java.io.Serializable;

/**
 * Created by vaclavlangr on 24.04.17.
 */
public class TagId implements Serializable {
    String tag;
    long imageId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagId tagId = (TagId) o;

        if (imageId != tagId.imageId) return false;
        return tag.equals(tagId.tag);

    }

    @Override
    public int hashCode() {
        int result = tag.hashCode();
        result = 31 * result + (int) (imageId ^ (imageId >>> 32));
        return result;
    }
}
