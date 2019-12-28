package in.rs.mdprogramming.todos.logger;

import in.rs.mdprogramming.todos.exception.InvalidPrincipalException;
import in.rs.mdprogramming.todos.exception.ResourceNotFoundException;
import in.rs.mdprogramming.todos.exception.UsernameExistsException;
import in.rs.mdprogramming.todos.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

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

    @ExceptionHandler(UsernameExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse emailExistsError(UsernameExistsException ex) {

        return new ErrorResponse(ex.getMessage());

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse invalidRoleError(ResourceNotFoundException ex) {

        return new ErrorResponse(ex.getMessage());

    }

    /**
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse invalidRoleError(RoleNotFoundException ex) {

        return new ErrorResponse(ex.getMessage());

    }

    @ExceptionHandler(RolesAreEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse emptyRolesError(RolesAreEmptyException ex) {

        return new ErrorResponse(ex.getMessage());

    }*/

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse sqlError(SQLException ex) {

        return new ErrorResponse(ex.getMessage());

    }

    @ExceptionHandler(InvalidPrincipalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse badRequestError(InvalidPrincipalException ex) {

        return new ErrorResponse(ex.getMessage());

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse internalError(Exception ex) {

        return new ErrorResponse(ex.getMessage());

    }

}
