package exceptions;

public class IncorrectArgsException extends Exception{
    public IncorrectArgsException() {
    }
    public IncorrectArgsException(String message) {
        super(message);
    }
}
