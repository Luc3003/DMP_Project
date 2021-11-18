package exceptions;

public class UnexistedRoleException extends Exception{
    public UnexistedRoleException() {
        super("Unexistant role");
    }
}