package by.it.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Evgeni on 09.08.17.
 */
public class DBConnectManager {

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();


    public static Connection getConnection() throws DBExceptionManager {
        try {
            if (tl.get() == null) {
                tl.set(DataSource.getInstance().getConnection());
            }
            return tl.get();
        } catch (Exception e) {
            throw new DBExceptionManager("Ошибка получения соединения " + e.getMessage());
        }
    }


}


