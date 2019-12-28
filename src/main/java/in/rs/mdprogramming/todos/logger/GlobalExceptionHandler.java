package in.rs.mdprogramming.todos.logger;

import in.rs.mdprogramming.todos.exception.InvalidRoleException;
import in.rs.mdprogramming.todos.exception.ResourceNotFoundException;
import in.rs.mdprogramming.todos.exception.UsernameExistsException;
import in.rs.mdprogramming.todos.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String message = "An error has occurred while processing your request. Please contact support!";

    @Autowired
    CommonLogger commonLogger;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse validationError(MethodArgumentNotValidException ex) {

        commonLogger.log(ex);

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        return new ErrorResponse(errors.get(0));

    }

    @ExceptionHandler(UsernameExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse usernameExistsError(UsernameExistsException ex) {

        return new ErrorResponse(ex.getMessage());

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse resourceNotFoundError(ResourceNotFoundException ex) {

        return new ErrorResponse(ex.getMessage());

    }

    @ExceptionHandler(InvalidRoleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse invalidRoleError(InvalidRoleException ex) {

        return new ErrorResponse(ex.getMessage());

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse dataIntegrityViolationError(DataIntegrityViolationException ex) {

        return new ErrorResponse(message);

    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse sqlError(SQLException ex) {

        return new ErrorResponse(message);

    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse badCredentialsError(BadCredentialsException ex) {

        return new ErrorResponse(ex.getMessage());

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse internalError(Exception ex) {

        return new ErrorResponse(message);

    }

}
