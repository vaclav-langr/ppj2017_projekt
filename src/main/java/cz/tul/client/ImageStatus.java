package cz.tul.client;

/**
 * Created by vaclavlangr on 17.05.2017.
 */
public class ImageStatus {
    public enum ImageState {
        READY, PROCESSING;
    }

    private ImageState state;

    public ImageStatus(ImageState state) {
        super();
        this.state = state;
    }

    public ImageState getState() {
        return state;
    }
}
