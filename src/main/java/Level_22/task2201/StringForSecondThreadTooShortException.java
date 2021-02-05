package Level_22.task2201;

public class StringForSecondThreadTooShortException extends RuntimeException{

    public StringForSecondThreadTooShortException(String s,Throwable e) {
        super(s,e);
    }
}
