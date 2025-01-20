/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import domen.AbstractDomainObject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author gazda
 */
public class DatabaseBroker {
    private static Connection connection;


    public DatabaseBroker() {
    }
    
    public static void openConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/veterinar_ordinacija";
            String user = "root";
            String pass = "root";
            connection = DriverManager.getConnection(url,user, pass);
            connection.setAutoCommit(false);
            System.out.println("Database succesfully connected");
            System.out.println("------------------------------------------");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection to database in unsuccesful!");
        }
    }
    
    public static void commitTransaction() throws SQLException {
        connection.commit();
        System.out.println("Commit");
        System.out.println("------------------------------------------");
    }
    
    public static void rollbackTransaction() throws SQLException {
        if(connection != null) 
        connection.rollback();
        System.out.println("Rollback");
    }
    
    public static void closeConnection() throws SQLException {
        connection.close();
        System.out.println("Connection closed!");
    }
    
    public static boolean insertRow(AbstractDomainObject ado) {
        String query;
        try {
            Statement st = connection.createStatement();
            query = "INSERT INTO " + ado.returnClassName() + " " + ado.returnInsertColumns() + " VALUES(" + ado.returnAttrValues() + ")";
            st.executeUpdate(query);
            st.close();
        } catch (SQLException esql) {
            esql.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deleteRow(AbstractDomainObject ado) {
        String query;
        try {
           Statement st = connection.createStatement();
            query = "DELETE FROM " + ado.returnClassName() + " WHERE " + ado.returnSearchCondition();
            st.executeUpdate(query);
            st.close();
        } catch (SQLException esql) {
            return false;
        }
        return true;
    }

    public static boolean updateRow(AbstractDomainObject ado) {
        String query;
        try {
           Statement st = connection.createStatement();
            query = "UPDATE " + ado.returnClassName() +
                    " SET " + ado.setAttrValues() +
                    " WHERE " + ado.returnSearchCondition();
            st.executeUpdate(query);
            st.close();
        } catch (SQLException esql) {
            return false;
        }
        return true;
    }

    public static boolean doesExist(AbstractDomainObject ado) {
        String query;
        ResultSet rows;
        try {
           Statement st = connection.createStatement();
            query = "SELECT *" +
                    " FROM " + ado.returnClassName() +
                    " WHERE " + ado.returnSearchCondition();
            System.out.println(query);
            rows = st.executeQuery(query);
            boolean signal = rows.next();
            rows.close();
            st.close();
            return signal;
        } catch (SQLException esql) {
            throw new RuntimeException(esql);
        }
    }

    public static boolean findRowAndReturn(AbstractDomainObject ado) {
        ResultSet rs;
        String query;
        try {
            Statement st = connection.createStatement();
            query = "SELECT * FROM " + ado.returnClassName() + " WHERE " + ado.returnSearchCondition();
            System.out.println("SQL query: " + query);
            rs = st.executeQuery(query);

            if (!rs.next()) {
                System.out.println("No rows found in ResultSet!");
                return false;
            }

            System.out.println("Row found, calling setAttributes...");
            boolean success = ado.setAttributes(rs);
            rs.close();
            st.close();
            return success;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}
