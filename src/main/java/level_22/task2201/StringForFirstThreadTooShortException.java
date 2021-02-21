package level_22.task2201;

public class StringForFirstThreadTooShortException extends RuntimeException {

    public StringForFirstThreadTooShortException(String s,Throwable e) {
        super(s,e);
    }
}
