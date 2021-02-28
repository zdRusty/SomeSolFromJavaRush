package level_33.task3310.strategy;

import java.math.BigDecimal;
import java.sql.*;

public class DBBucket {

    private String tableName;
    private int size=0;

    private static Connection connect = new ConnectionHelper().getNewConnection();

    public DBBucket(int i){
        tableName = "entries" + i;
        createDBBucket();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {

        try (Statement statement = connect.createStatement()){
            statement.execute("ALTER TABLE " + this.tableName + " RENAME TO " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.tableName = tableName;
    }

    public int getSize() {
        return size;
    }

    public void createDBBucket(){
        try {
            Statement statement = connect.createStatement();
            statement.execute("create  table if not exists " + tableName + "(" +
                    "  hash       int NOT NULL," +
                    "  key     bigint NOT NULL," +
                    "  value     text NOT NULL" +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putEntry(Entry entry){
        try (PreparedStatement prepStatement = connect.prepareCall("INSERT INTO " + tableName +
                " (hash, key, value) VALUES " + "(?,?,?)")){
            prepStatement.setInt(1,entry.hash);
            prepStatement.setBigDecimal(2,new BigDecimal(entry.key));
            prepStatement.setString(3,entry.value);
            prepStatement.execute();
            size++;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void clear(){
        try (Statement statement = connect.createStatement()){
            statement.execute("Truncate table " + tableName);
            size=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty(){
        try (Statement statement = connect.createStatement()){
            statement.execute("Select hash from " + tableName);
            ResultSet resultSet = statement.getResultSet();
            return !resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean containsKey(Long key) {
        try (Statement statement = connect.createStatement()){
            statement.executeQuery("Select key from " + tableName);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                if(resultSet.getBigDecimal("key").equals(new BigDecimal(key))) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean containsValue(String value) {
        try (Statement statement = connect.createStatement()){
            statement.executeQuery("Select value from " + tableName);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                if(resultSet.getString("value").equals(value)) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public long getKey(String value){
        try (Statement statement = connect.createStatement()){
            statement.executeQuery("Select key, value from " + tableName);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                if (value.equals(resultSet.getString("value"))) {
                    return resultSet.getBigDecimal("key").longValue();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public String getValue(long key){
        try (Statement statement = connect.createStatement()){
            statement.executeQuery("Select key, value from " + tableName);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                if (key==resultSet.getBigDecimal("key").longValue()) {
                    return resultSet.getString("value");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Entry getEntry(){
        long key;
        try (Statement statement = connect.createStatement()){
            statement.executeQuery("Select hash, key, value from " + tableName);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                key = resultSet.getBigDecimal("key").longValue();
                int hash = resultSet.getInt("hash");
                String value = resultSet.getString("value");
                statement.execute("DELETE FROM " + tableName + " WHERE key = " + key + "; ");
                size--;
                return new Entry(hash, key, value, null);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(){
        try (Statement statement = connect.createStatement()){
            statement.execute("drop table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
