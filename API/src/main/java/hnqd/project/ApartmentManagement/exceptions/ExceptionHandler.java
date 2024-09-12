package hnqd.project.ApartmentManagement.exceptions;

import hnqd.project.ApartmentManagement.dto.ErrorResponse;
import hnqd.project.ApartmentManagement.dto.ResponseObject;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleIoException(IOException ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CommonException.NotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleNotFoundException(CommonException.NotFoundException ex) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CommonException.WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleWrongPasswordException(CommonException.NotFoundException ex) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CommonException.DuplicatePasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDuplicatePasswordException(CommonException.NotFoundException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
