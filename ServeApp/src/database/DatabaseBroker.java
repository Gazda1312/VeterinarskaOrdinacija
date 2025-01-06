/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import domen.AbstractDomainObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseBroker {
    private Connection connection = null;
    private Statement st = null;

    public DatabaseBroker() {
    }
   

    
 
    public void connect() {
        try{
            String url = "jdbc:mysql://localhost:3306/veterinar_ordinacija";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Succesfully connected to database.");
            connection.setAutoCommit(false);
        }catch (SQLException e) {
            System.out.println("conn unsuccesful");
            throw new RuntimeException(e);
        }
    }
    
     public void commitTransaction() throws Exception {
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new Exception("Neuspesan commit");
        }
    }

    public void rollbackTransaction() throws Exception{
        try {
            if(connection != null)
                connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new Exception("Neuspesan rollback");
        }
    }
     
     
    public void closeConnection() throws Exception {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Konekcija neuspesno zatvorena");
        }
    }
    
    public ArrayList<AbstractDomainObject> returnAll (AbstractDomainObject ado) throws SQLException, Exception {
            String query = "SELECT * FROM " + ado.returnClassName() + " WHERE " + ado.returnSearchCondition();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            return ado.returnList(rs);
    }

}
