/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.sql.SQLException;

/**
 *
 * @author gazda
 */
public class Therapy extends AbstractDomainObject{
    private int idTerapija;
    private String nazivTerapije;
    private String trajanje;
    private String opis;
    private String oprema;

    public Therapy() {
    }

    public Therapy(int idTerapija, String nazivTerapije, String trajanje, String opis, String oprema) {
        this.idTerapija = idTerapija;
        this.nazivTerapije = nazivTerapije;
        this.trajanje = trajanje;
        this.opis = opis;
        this.oprema = oprema;
    }

    public String getOprema() {
        return oprema;
    }

    public void setOprema(String oprema) {
        this.oprema = oprema;
    }

    public int getIdTerapija() {
        return idTerapija;
    }

    public void setIdTerapija(int idTerapija) {
        this.idTerapija = idTerapija;
    }

    public String getNazivTerapije() {
        return nazivTerapije;
    }

    public void setNazivTerapije(String nazivTerapije) {
        this.nazivTerapije = nazivTerapije;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String alias() {
        return "t";
    }

    @Override
    public String join() {
        return ""; // No join necessary here, as this is a standalone table.
    }

    @Override
    public String returnAttrValues() {
        return String.format("'%s', '%s', '%s', '%s'", nazivTerapije, trajanje, opis, oprema);
    }

    @Override
    public String returnClassName() {
        return "therapy";
    }

    @Override
    public String setAttrValues() {
        return String.format("nazivTerapije = '%s', trajanje = '%s', opis = '%s', oprema = '%s'",
                nazivTerapije, trajanje, opis, oprema);
    }

    @Override
    public String returnInsertColumns() {
        return "nazivTerapije, trajanje, opis, oprema";
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            this.idTerapija = rs.getInt("idTerapija");
            this.nazivTerapije = rs.getString("nazivTerapije");
            this.trajanje = rs.getString("trajanje");
            this.opis = rs.getString("opis");
            this.oprema = rs.getString("oprema");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public LinkedList<AbstractDomainObject> returnList(ResultSet rs) throws Exception {
        LinkedList<AbstractDomainObject> therapies = new LinkedList<>();
        try {
            while (rs.next()) {
                Therapy therapy = new Therapy(
                        rs.getInt("idTerapija"),
                        rs.getString("nazivTerapije"),
                        rs.getString("trajanje"),
                        rs.getString("opis"),
                        rs.getString("oprema")
                );
                therapies.add(therapy);
            }
        } catch (SQLException e) {
            throw new Exception("Error while creating Therapy list from ResultSet: " + e.getMessage());
        }
        return therapies;
    }
    
    
}
