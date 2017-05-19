package cz.tul.controllers.exceptions;

/**
 * Created by vaclavlangr on 19.05.2017.
 */
public class APIErrorMessage {
    private String message;

    public APIErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
