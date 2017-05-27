package cz.tul.controllers.exceptions;

/**
 * Created by vaclavlangr on 19.05.2017.
 */
public class APIException extends RuntimeException {
    public APIException(String message) {
        super(message);
    }
}

