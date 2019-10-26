package xyz.guqing.travelpath.exception;

/**
 * 邮件自定义异常<br>
 *
 * @author guqin
 * @date 2019-10-26 21:28
 */
public class MailException extends RuntimeException{
    public MailException() {
    }

    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailException(Throwable cause) {
        super(cause);
    }

    public MailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
