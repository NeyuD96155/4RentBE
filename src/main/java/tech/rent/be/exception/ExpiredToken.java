package tech.rent.be.exception;

public class ExpiredToken extends RuntimeException {
    public ExpiredToken(String message) {
        super(message);
    }
}
