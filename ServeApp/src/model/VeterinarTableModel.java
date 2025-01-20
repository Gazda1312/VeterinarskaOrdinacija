/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.Veterinar;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gazda
 */
public class VeterinarTableModel extends AbstractTableModel {

    String[] columnNames = new String[]{"Firstname", "Lastname", "Username"};
    ArrayList<Veterinar> veterinars;
    
    
    public VeterinarTableModel() {
        veterinars = new ArrayList<>();
    }
    
    public VeterinarTableModel(ArrayList<Veterinar> veterinars) {
        this.veterinars = veterinars;
    }
    
    @Override
    public String getColumnName(int column) {
        if(column > columnNames.length) {
            return "n/a";
        }
        return columnNames[column];
    }
    
    @Override
    public int getRowCount() {
        return veterinars.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veterinar v = veterinars.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return v.getIme();
            case 1:
                return v.getPrezime();
            case 2:
                return v.getUsername();
            default:
                return "n/a";
        }
    }
    
    public void addUser(Veterinar v) {
        if(veterinars.contains(v)) {
            return;
        }
        veterinars.add(v);
        fireTableDataChanged();
    }

    public ArrayList<Veterinar> getVeterinars() {
        return veterinars;
    }

    public void setVeterinars(ArrayList<Veterinar> veterinars) {
        this.veterinars = veterinars;
    }
    
    
    
}
