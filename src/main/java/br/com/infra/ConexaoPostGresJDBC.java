package br.com.infra;

import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoPostGresJDBC implements ConexaoJDBC{

    private Connection connection = null;
    
    public ConexaoPostGresJDBC() throws SQLException,ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        Properties properties = new Properties();
        properties.put("user", "postgres");
        properties.put("password", "postgres");
        this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/test_db");
        this.connection.setAutoCommit(false);
    }

    
    
    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void close() {
        if(this.connection != null){
            try {
                this.connection.close();
            } catch (SQLException ex) {
                //Logger.getLogger(ConexaoPostGresJDBC.class.getName());
                System.out.println(""+ex);
            }
            
        }
    }

    @Override
    public void commit() throws SQLException {
        this.connection.commit();
        this.close();
    }

    @Override
    public void roolBack() {
        if(this.connection != null){
            try {
                this.connection.rollback();
            } catch (SQLException e) {
                System.out.println(e);
            }finally{
                this.close();
            }
        }
    }
    
}
