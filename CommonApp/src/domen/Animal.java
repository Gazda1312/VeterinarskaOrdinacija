/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import enums.Gender;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author gazda
 */
public class Animal extends AbstractDomainObject{
   
    private int idZivotinja;
    private Date datumPrijema;
    private int starost;
    private Gender pol;
    private Species idVrsta;

    public Animal() {
    }

    public Animal(int idZivotinja, Date datumPrijema, int starost, Gender pol, Species idVrsta) {
        this.idZivotinja = idZivotinja;
        this.datumPrijema = datumPrijema;
        this.starost = starost;
        this.pol = pol;
        this.idVrsta = idVrsta;
    }

    public Species getIdVrsta() {
        return idVrsta;
    }

    public void setIdVrsta(Species idVrsta) {
        this.idVrsta = idVrsta;
    }

    public int getIdZivotinja() {
        return idZivotinja;
    }

    public void setIdZivotinja(int idZivotinja) {
        this.idZivotinja = idZivotinja;
    }

    public Date getDatumPrijema() {
        return datumPrijema;
    }

    public void setDatumPrijema(Date datumPrijema) {
        this.datumPrijema = datumPrijema;
    }

    public int getStarost() {
        return starost;
    }

    public void setStarost(int starost) {
        this.starost = starost;
    }

    public Gender getPol() {
        return pol;
    }

    public void setPol(Gender pol) {
        this.pol = pol;
    }

@Override
    public String alias() {
        return "a";
    }

    @Override
    public String join() {
        return "JOIN Species s ON a.idVrsta = s.idVrsta";
    }

    @Override
    public String returnAttrValues() {
        return idZivotinja + ", '" + new java.sql.Date(datumPrijema.getTime()) + "', " + starost + ", '" +
               pol.name() + "', " + idVrsta.getIdVrsta();
    }

    @Override
    public String returnClassName() {
        return "Animal";
    }

    @Override
    public String setAttrValues() {
        return "datumPrijema = '" + new java.sql.Date(datumPrijema.getTime()) + "', starost = " + starost +
               ", pol = '" + pol.name() + "', idVrsta = " + idVrsta.getIdVrsta();
    }

    @Override
    public String returnInsertColumns() {
        return "idZivotinja, datumPrijema, starost, pol, idVrsta";
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            this.idZivotinja = rs.getInt("idZivotinja");
            this.datumPrijema = rs.getDate("datumPrijema");
            this.starost = rs.getInt("starost");
            this.pol = Gender.valueOf(rs.getString("pol"));
            Species species = new Species();
            species.setIdVrsta(rs.getInt("idVrsta"));
            this.idVrsta = species;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public LinkedList<AbstractDomainObject> returnList(ResultSet rs) throws Exception {
        LinkedList<AbstractDomainObject> list = new LinkedList<>();
        while (rs.next()) {
            Animal animal = new Animal();
            animal.setIdZivotinja(rs.getInt("idZivotinja"));
            animal.setDatumPrijema(rs.getDate("datumPrijema"));
            animal.setStarost(rs.getInt("starost"));
            animal.setPol(Gender.valueOf(rs.getString("pol")));
            Species species = new Species();
            species.setIdVrsta(rs.getInt("idVrsta"));
            animal.setIdVrsta(species);
            list.add(animal);
        }
        return list;
    }    
    
    
    
}
