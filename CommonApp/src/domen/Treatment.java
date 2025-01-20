/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author gazda
 */
public class Treatment extends AbstractDomainObject{
    private int idTretman;
    private String nazivTretman;
    private Date datumTretmana;
    private RehabilitationPlan idPlan;
    private Therapy idTherapy; 

    public Treatment() {
    }

    public Treatment(int idTretman, String nazivTretman, Date datumTretmana, RehabilitationPlan idPlan, Therapy idTherapy) {
        this.idTretman = idTretman;
        this.nazivTretman = nazivTretman;
        this.datumTretmana = datumTretmana;
        this.idPlan = idPlan;
        this.idTherapy = idTherapy;
    }

    public Therapy getIdTherapy() {
        return idTherapy;
    }

    public void setIdTherapy(Therapy idTherapy) {
        this.idTherapy = idTherapy;
    }

    public int getIdTretman() {
        return idTretman;
    }

    public void setIdTretman(int idTretman) {
        this.idTretman = idTretman;
    }

    public String getNazivTretman() {
        return nazivTretman;
    }

    public void setNazivTretman(String nazivTretman) {
        this.nazivTretman = nazivTretman;
    }

    public Date getDatumTretmana() {
        return datumTretmana;
    }

    public void setDatumTretmana(Date datumTretmana) {
        this.datumTretmana = datumTretmana;
    }

    public RehabilitationPlan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(RehabilitationPlan idPlan) {
        this.idPlan = idPlan;
    }

    @Override
    public String alias() {
        return "tr";
    }

    @Override
    public String join() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String returnAttrValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String returnClassName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String setAttrValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String returnInsertColumns() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LinkedList<AbstractDomainObject> returnList(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
