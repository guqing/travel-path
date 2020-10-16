package xyz.guqing.travelpath.exception;

import org.springframework.http.HttpStatus;

/**
 * Authentication exception.
 *
 * @author guqing
 * @date 2020-04-04 16:03
 */
public class AuthenticationException extends AbstractTravelPathException {
    private static final long serialVersionUID = -8823731606051111268L;

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
