package br.com.infra;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConexaoJDBC {
    public Connection getConnection();
    public void close();
    public void commit() throws SQLException;
    public void roolBack();
    
}
