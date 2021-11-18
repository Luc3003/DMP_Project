package exceptions;

public class EmailFormatIncorrectException extends Exception{

    public EmailFormatIncorrectException() {
        super("Le format de l'email rentré est incorrect");
    }
}
