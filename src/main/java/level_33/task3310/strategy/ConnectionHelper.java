package level_33.task3310.strategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    public Connection getNewConnection(){
        Connection connect = null;
        try {
            connect = DriverManager.getConnection("jdbc:postgresql:strategy", "postgres", "borderpass");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connect;
    }

}
