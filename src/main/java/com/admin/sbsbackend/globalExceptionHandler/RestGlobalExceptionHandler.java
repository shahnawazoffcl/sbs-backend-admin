package com.admin.sbsbackend.globalExceptionHandler;

import com.admin.sbsbackend.dtos.ErrorResponseDTO;
import com.admin.sbsbackend.exceptions.InvalidTokenException;
import com.admin.sbsbackend.exceptions.UserAlreadyExists;
import com.admin.sbsbackend.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestGlobalExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class, UserAlreadyExists.class})
    protected ResponseEntity<ErrorResponseDTO> handleUserExceptions(UserNotFoundException ex){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setMessageCode(404);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = InvalidTokenException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidTokenException(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setMessageCode(403);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
//        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
//        errorResponse.setMessage(ex.getMessage());
//        errorResponse.setMessageCode(500);
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
