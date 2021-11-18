package exceptions;

public class PatientNotExistException extends Exception{
    public PatientNotExistException() {
        super("This Patient does not exist");
    }
}
