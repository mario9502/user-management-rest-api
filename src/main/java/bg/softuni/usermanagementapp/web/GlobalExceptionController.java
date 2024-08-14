package bg.softuni.usermanagementapp.web;

import bg.softuni.usermanagementapp.service.exception.EmailAlreadyExistsException;
import bg.softuni.usermanagementapp.service.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String userNotFound(UserNotFoundException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String emailAlreadyExists(EmailAlreadyExistsException exception){
        return exception.getMessage();
    }
}
