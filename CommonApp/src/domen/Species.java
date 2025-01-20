/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author gazda
 */
public class Species {
    private int idVrsta;
    private String naziv;
    private String opis;

    public Species() {
    }

    public Species(int idVrsta, String naziv, String opis) {
        this.idVrsta = idVrsta;
        this.naziv = naziv;
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getIdVrsta() {
        return idVrsta;
    }

    public void setIdVrsta(int idVrsta) {
        this.idVrsta = idVrsta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
   
    
    
}
