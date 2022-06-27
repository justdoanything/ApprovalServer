package prj.yong.payment.approval.van.fdk.exception;

import org.springframework.http.HttpStatus;

public class SystemException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String message;
    private HttpStatus httpStatus;
    private Throwable throwable;

    public SystemException(Exception e) {
        this(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public SystemException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public SystemException(Throwable throwable) {
        this.throwable = throwable;
    }

    public SystemException(String message, Throwable throwable){
        this.message = message;
        this.throwable = throwable;
    }

    public SystemException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the httpStatus
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
}
