package level_33.task3310.tests;

import level_33.task3310.strategy.ConnectionHelper;
import level_33.task3310.strategy.DBBucket;
import level_33.task3310.strategy.DataBaseStrategy;
import level_33.task3310.strategy.Entry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

    private static Connection connection;

    @Before
    public void init() throws SQLException {
        connection = new ConnectionHelper().getNewConnection();
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }

    @Test
    public void testConnectionInstance(){
        try (Connection connection = new ConnectionHelper().getNewConnection()){
            Assert.assertTrue(connection.isValid(1));
            Assert.assertFalse(connection.isClosed());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void createDBBuketTest(){
        for(int i=0;i<3;i++){
            new DBBucket(i);
        }
    }

    public static void main(String[] args) {
        DataBaseStrategy dbStrategy = new DataBaseStrategy();
        dbStrategy.table[0].putEntry(new Entry(50,456L,"someString",null));
        dbStrategy.table[0].putEntry(new Entry(51,456L,"someString",null));
        System.out.println(dbStrategy.table[0].getSize());
        System.out.println(dbStrategy.table[0].getEntry());
        System.out.println(dbStrategy.table[0].getSize());

        for(DBBucket x: dbStrategy.table){
            x.remove();
        }
    }
}
