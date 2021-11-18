package exceptions;

public class EmailNotFoundException extends Exception{
    public EmailNotFoundException() {
        super("Email could not be found");
    }
}
