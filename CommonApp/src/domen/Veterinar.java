/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import enums.Action;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author gazda
 */
public class Veterinar extends AbstractDomainObject {

    private int idVeterinar;
    private int godineIskustva;
    private String ime;
    private String prezime;
    private String specijalizacija;
    private String username;
    private String password;
    private boolean loggedIn;

    public Veterinar() {
    }

    public Veterinar(int idVeterinar, String ime, String prezime, String specijalizacija, int godineIskustva, String username, String password) {
        this.idVeterinar = idVeterinar;
        this.ime = ime;
        this.prezime = prezime;
        this.specijalizacija = specijalizacija;
        this.godineIskustva = godineIskustva;
        this.username = username;
        this.password = password;
    }
    
    public Veterinar(String username, String password) {
        this.username = username;
        this.password = password;
        this.searchCondition="username = '" + username + "' AND password = '" + password + "'";
    }

    @Override
    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }
    
    
    
  

    public int getIdVeterinar() {
        return idVeterinar;
    }

    public void setIdVeterinar(int idVeterinar) {
        this.idVeterinar = idVeterinar;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getSpecijalizacija() {
        return specijalizacija;
    }

    public void setSpecijalizacija(String specijalizacija) {
        this.specijalizacija = specijalizacija;
    }

    public int getGodineIskustva() {
        return godineIskustva;
    }

    public void setGodineIskustva(int godineIskustva) {
        this.godineIskustva = godineIskustva;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veterinar other = (Veterinar) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    // Abstract Method Implementations
    @Override
    public String alias() {
        return "v"; // Alias for the table Veterinar
    }

    @Override
    public String join() {
        return ""; // No join for this example; add joins for related objects if needed
    }

    @Override
    public String returnAttrValues() {
        return idVeterinar + ", '" + ime + "', '" + prezime + "', " + godineIskustva +
               ", '" + specijalizacija + "', '" + username + "', '" + password + "'";
    }

    @Override
    public String returnClassName() {
        return "veterinar"; // Name of the corresponding table in the database
    }

    @Override
    public String setAttrValues() {
        return "ime='" + ime + "', prezime='" + prezime + "', godineIskustva=" + godineIskustva +
               ", specijalizacija='" + specijalizacija + "', username='" + username + 
               "', password='" + password + "'";
    }

    @Override
    public String returnInsertColumns() {
        return "(ime, prezime, godineIskustva, specijalizacija, username, password)";
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
          
            // Čitanje podataka iz ResultSet
            int id = rs.getInt("idVeterinar");
            int godineIskustva = rs.getInt("godineIskustva");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String specijalizacija = rs.getString("specijalizacija");
            String username = rs.getString("username");
            String password = rs.getString("password");

            // Log za proveru dobijenih vrednosti
            System.out.println("Dobijeni podaci iz ResultSet: " + id + ", " + godineIskustva + ", " + ime + ", " + prezime + ", " + specijalizacija + ", " + username + ", " + password);

            // Postavljanje atributa klase
            this.idVeterinar = id;
            this.godineIskustva = godineIskustva;
            this.ime = ime;
            this.prezime = prezime;
            this.specijalizacija = specijalizacija;
            this.username = username;
            this.password = password;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public LinkedList<AbstractDomainObject> returnList(ResultSet rs) throws Exception {
        LinkedList<AbstractDomainObject> list = new LinkedList<>();
        try {
            while (rs.next()) {
                Veterinar v = new Veterinar();
                v.setAttributes(rs);
                list.add(v);
            }
        } catch (SQLException e) {
            throw new Exception("Error creating the list of Veterinar objects", e);
        }
        return list;
    }

    @Override
    public String returnSearchCondition() {
        return searchCondition;
    }
    
    

    

    

    
    
}
