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

    @Override
    public String toString() {
        return "Veterinar{" + "ime=" + ime + ", prezime=" + prezime + '}';
    }
    
    

    @Override
    public String alias() {
        return "v";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String returnAttrValues() {
        return String.format("'%d', '%d', '%s', '%s', '%s', '%s', '%s', %b",
            idVeterinar, godineIskustva, ime, prezime, specijalizacija, username, password, loggedIn);    
    }

    @Override
    public String returnClassName() {
        return "veterinar";
    }

    @Override
    public String setAttrValues() {
        return String.format("godineIskustva = %d, ime = '%s', prezime = '%s', specijalizacija = '%s', username = '%s', password = '%s', loggedIn = %b",
            godineIskustva, ime, prezime, specijalizacija, username, password, loggedIn);    
    }

    @Override
    public String returnInsertColumns() {
        return "idVeterinar, godineIskustva, ime, prezime, specijalizacija, username, password, loggedIn";    
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            this.idVeterinar = rs.getInt("idVeterinar");
            this.godineIskustva = rs.getInt("godineIskustva");
            this.ime = rs.getString("ime");
            this.prezime = rs.getString("prezime");
            this.specijalizacija = rs.getString("specijalizacija");
            this.username = rs.getString("username");
            this.password = rs.getString("password");
            this.loggedIn = rs.getBoolean("loggedIn");
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }    
    }

    @Override
    public ArrayList<AbstractDomainObject> returnList(ResultSet rs) throws Exception {
        ArrayList<AbstractDomainObject> veterinarians = new ArrayList<>();
    
        while (rs.next()) {
            Veterinar vet = new Veterinar();
            // Postavite atribute iz ResultSet-a
            vet.setIdVeterinar(rs.getInt("id"));
            vet.setUsername(rs.getString("username"));
            vet.setPassword(rs.getString("password"));
            vet.setIme(rs.getString("ime"));
            vet.setPrezime(rs.getString("prezime"));
            // Dodajte veterinar u listu
            veterinarians.add(vet);
        }
        return veterinarians;
    }

    

    
    
}
