package tech.rent.be.exception;

public class InValidToken extends RuntimeException {
    public InValidToken(String message) {
        super(message);
    }
}
