package in.rs.mdprogramming.todos.response;

/**
 * This is error response object
 *
 * @author duskol Dec 24, 2019
 *
 */
public class ErrorResponse {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
