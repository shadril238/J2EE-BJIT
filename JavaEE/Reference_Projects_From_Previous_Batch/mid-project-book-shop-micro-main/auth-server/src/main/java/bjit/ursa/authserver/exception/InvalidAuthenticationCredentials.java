package bjit.ursa.authserver.exception;

public class InvalidAuthenticationCredentials extends RuntimeException {
    public InvalidAuthenticationCredentials(String message) {
        super(message);
    }
}