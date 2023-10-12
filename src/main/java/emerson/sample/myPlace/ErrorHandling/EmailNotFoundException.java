package emerson.sample.myPlace.ErrorHandling;

public class EmailNotFoundException extends Exception{
    public EmailNotFoundException(String msg){
        super(msg);
    }
}
