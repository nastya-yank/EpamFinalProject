package com.golubeva.project.dao.connection;

import com.golubeva.project.exception.ConnectionDatabaseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The {@code ConnectionPool} enum represents connection pool.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int POOL_SIZE = 8;
    private static ConnectionPool connectionPool = new ConnectionPool();

    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> proxyConnections;

    /**
     * Instance connection pool.
     */
    public static ConnectionPool getInstance() {
        return connectionPool;
    }

    private ConnectionPool() {
        try {
            DatabaseConfig databaseConfig = new DatabaseConfig();
            Class.forName(databaseConfig.getDriverName());
            freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
            proxyConnections = new LinkedBlockingDeque<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.offer(new ProxyConnection(DriverManager
                        .getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(),
                                databaseConfig.getPassword())));
            }
            LOGGER.log(Level.INFO, "Connection pool has been filled");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.FATAL, "Error during connection pool creating");
        }
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() throws ConnectionDatabaseException {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
            proxyConnections.offer(connection);
            LOGGER.log(Level.DEBUG, "Connection has been given");
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Pool can't provide connection", e);
            throw new ConnectionDatabaseException(e);
        }
        return connection;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            if (proxyConnections.remove(connection)) {
                freeConnections.offer((ProxyConnection) connection);
            }
            LOGGER.log(Level.DEBUG, "Connection has been released");
        } else {
            LOGGER.log(Level.WARN, "Invalid connection to release");
        }
    }

    /**
     * Destroy pool.
     */
    public void destroyPool() {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.take().reallyClose();
            }
            LOGGER.log(Level.INFO, "Connection pool has been destroyed");
            deregisterDrivers();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Connection was not deleted", e);
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Thread was interrupted", e);
        }
    }

    private void deregisterDrivers() throws SQLException {
        while (DriverManager.getDrivers().hasMoreElements()) {
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
        LOGGER.log(Level.INFO, "Drivers have been deregistered");
    }
}
