package prj.yong.payment.approval.van.kftc.exception.advice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import prj.yong.payment.approval.van.kftc.exception.BusinessException;
import prj.yong.payment.approval.van.kftc.exception.SystemException;

@ControllerAdvice
public class DefaultExceptionAdvice {
    private final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionAdvice.class);

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleException(BusinessException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Business Exception : " + e.getMessage());
        result.put("httpStatus", e.getHttpStatus().value());

        return new ResponseEntity<>(result, e.getHttpStatus());
    }

    @ExceptionHandler(SystemException.class)
    protected ResponseEntity<Object> handleException(SystemException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "System Exception : " + e.getMessage());
        result.put("httpStatus", e.getHttpStatus().value());

        return new ResponseEntity<>(result, e.getHttpStatus());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> handleException(HttpClientErrorException e) {
        Map<String, Object> result = new HashMap<>();
        String message = getErrorMessageFromJsonString(e.getResponseBodyAsString());
        result.put("message", "Http Client Exception : " + message);
        result.put("httpStatus", HttpStatus.EXPECTATION_FAILED.value());

        return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    protected ResponseEntity<Object> handleException(HttpServerErrorException e) {
        Map<String, Object> result = new HashMap<>();
        String message = getErrorMessageFromJsonString(e.getResponseBodyAsString());
        result.put("message", "Http Server Exception : " + message);
        result.put("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceAccessException.class)
    protected ResponseEntity<Object> handleException(ResourceAccessException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Resource Access Exception : " + e.getMessage());
        result.put("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        Map<String, Object> result = new HashMap<>();
        
        LOGGER.error(e.getMessage(), e);

        if( e instanceof BusinessException){
            return handleException((BusinessException)e);
        } else if( e instanceof SystemException){
            return handleException((SystemException)e);
        } else {
            result.put("message", "Exception : " + e.getMessage());
            result.put("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getErrorMessageFromJsonString(String jsonString) {
        return (String) new Gson().fromJson(jsonString, JsonObject.class).get("message").getAsString();
    }
}
