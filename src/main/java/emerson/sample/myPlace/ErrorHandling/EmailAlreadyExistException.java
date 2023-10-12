package emerson.sample.myPlace.ErrorHandling;

public class EmailAlreadyExistException extends Exception{
    public EmailAlreadyExistException(String msg){
        super(msg);
    }
}
