package bjit.ursa.authserver.exception;

public class RoleDoesNotExistException extends RuntimeException {
    public RoleDoesNotExistException(String message) {
        super(message);
    }
}