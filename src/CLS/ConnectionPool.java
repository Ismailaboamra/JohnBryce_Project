package CLS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class ConnectionPool {
    public static ConnectionPool instance = null;
    public static final int NUMBER_OF_CONNECTION = 10;
    private final Set<Connection> connections = new HashSet<>();

    private ConnectionPool() throws SQLException {
        System.out.println("We create a new ConnectionPool instance.");
        OpenAllConnections();
    }

    public Connection getConnection()throws InterruptedException{
        synchronized (connections){
            if (connections.isEmpty()){
                connections.wait();
            }
            return connections.iterator().next();

        }
    }
    public void restoreConnection(Connection connection){
        synchronized (connections){
            connections.add(connection);
            connections.notify();
        }
    }

    private void OpenAllConnections() throws SQLException {
        //creating connection according to number of connections
        for (int i = 0; i < NUMBER_OF_CONNECTION; i++) {
            //Crete connection
            Connection connection = DriverManager.getConnection(DBManger.URL, DBManger.SQL_USER, DBManger.SQL_PASS);
            //add the connection into the Stack.
            connections.add(connection);
        }
    }

    private void closeAllConnections() throws InterruptedException{
        synchronized (connections){
            while (connections.size() < NUMBER_OF_CONNECTION)
                connections.wait();
        }
        connections.clear();
    }

    public static ConnectionPool getInstance(){
        if (instance == null){
            synchronized (ConnectionPool.class){
                if (instance == null){
                    try {
                        instance = new ConnectionPool();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return instance;
    }




}
