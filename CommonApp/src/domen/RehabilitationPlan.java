/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import enums.RehabilitationPlanStatus;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author gazda
 */
public class RehabilitationPlan extends AbstractDomainObject {
    
    private int idPlan;
    private Date datumIzradePlana;
    private String vrstaPovrede;
    private Date datumOporavka;
    private RehabilitationPlanStatus statusPlana;
    private Veterinar idVeterinar;

    public RehabilitationPlan() {
    }

    public RehabilitationPlan(int idPlan, Date datumIzradePlana, String vrstaPovrede, Date datumOporavka, RehabilitationPlanStatus statusPlana, Veterinar idVeterinar) {
        this.idPlan = idPlan;
        this.datumIzradePlana = datumIzradePlana;
        this.vrstaPovrede = vrstaPovrede;
        this.datumOporavka = datumOporavka;
        this.statusPlana = statusPlana;
        this.idVeterinar = idVeterinar;
    }

    public Veterinar getIdVeterinar() {
        return idVeterinar;
    }

    public void setIdVeterinar(Veterinar idVeterinar) {
        this.idVeterinar = idVeterinar;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public Date getDatumIzradePlana() {
        return datumIzradePlana;
    }

    public void setDatumIzradePlana(Date datumIzradePlana) {
        this.datumIzradePlana = datumIzradePlana;
    }

    public String getVrstaPovrede() {
        return vrstaPovrede;
    }

    public void setVrstaPovrede(String vrstaPovrede) {
        this.vrstaPovrede = vrstaPovrede;
    }

    public Date getDatumOporavka() {
        return datumOporavka;
    }

    public void setDatumOporavka(Date datumOporavka) {
        this.datumOporavka = datumOporavka;
    }

    public RehabilitationPlanStatus getStatusPlana() {
        return statusPlana;
    }

    public void setStatusPlana(RehabilitationPlanStatus statusPlana) {
        this.statusPlana = statusPlana;
    }

    @Override
    public String alias() {
        return "r";
    }

    @Override
    public String join() {
        return "JOIN Veterinar v ON r.idVeterinar = v.idVeterinar";
    }

    @Override
    public String returnAttrValues() {
        return idPlan + ", '" + new java.sql.Date(datumIzradePlana.getTime()) + "', '" + vrstaPovrede + "', '" +
               new java.sql.Date(datumOporavka.getTime()) + "', '" + statusPlana.name() + "', " + idVeterinar.getIdVeterinar();
    }

    @Override
    public String returnClassName() {
        return "RehabilitationPlan";
    }

    @Override
    public String setAttrValues() {
        return "datumIzradePlana = '" + new java.sql.Date(datumIzradePlana.getTime()) + "', vrstaPovrede = '" + vrstaPovrede +
               "', datumOporavka = '" + new java.sql.Date(datumOporavka.getTime()) + "', statusPlana = '" + statusPlana.name() +
               "', idVeterinar = " + idVeterinar.getIdVeterinar();
    }

    @Override
    public String returnInsertColumns() {
        return "idPlan, datumIzradePlana, vrstaPovrede, datumOporavka, statusPlana, idVeterinar";
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            this.idPlan = rs.getInt("idPlan");
            this.datumIzradePlana = rs.getDate("datumIzradePlana");
            this.vrstaPovrede = rs.getString("vrstaPovrede");
            this.datumOporavka = rs.getDate("datumOporavka");
            this.statusPlana = RehabilitationPlanStatus.valueOf(rs.getString("statusPlana"));
            Veterinar vet = new Veterinar();
            vet.setIdVeterinar(rs.getInt("idVeterinar"));
            this.idVeterinar = vet;
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
            RehabilitationPlan plan = new RehabilitationPlan();
            plan.setIdPlan(rs.getInt("idPlan"));
            plan.setDatumIzradePlana(rs.getDate("datumIzradePlana"));
            plan.setVrstaPovrede(rs.getString("vrstaPovrede"));
            plan.setDatumOporavka(rs.getDate("datumOporavka"));
            plan.setStatusPlana(RehabilitationPlanStatus.valueOf(rs.getString("statusPlana")));
            Veterinar vet = new Veterinar();
            vet.setIdVeterinar(rs.getInt("idVeterinar"));
            plan.setIdVeterinar(vet);
            list.add(plan);
        }
        return list;
    }
    
    
     
}
