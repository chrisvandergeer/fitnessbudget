package nl.cge;

/**
 * Created by chris on 16-05-17.
 */
public class StopTestException extends RuntimeException {

    public static StopTestException wrap(Throwable e) {
        return new StopTestException(e);
    }

    public StopTestException(String msg) {
        super(msg);
    }

    public StopTestException(Throwable e) {
        super(e);
    }
}
