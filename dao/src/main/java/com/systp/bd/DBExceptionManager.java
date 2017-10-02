package by.it.bd;

/**
 * Created by Evgeni on 09.08.17.
 */
public class DBExceptionManager extends RuntimeException {

    public DBExceptionManager(String message) {
        super(message);
    }
}
