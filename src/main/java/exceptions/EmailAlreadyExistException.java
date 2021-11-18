package exceptions;

public class EmailAlreadyExistException extends Exception{

    public EmailAlreadyExistException() {
        super("Impossible de créer un compte cet email existe deja");
    }
}
