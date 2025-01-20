/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.main;

import client.logic.ClientController;
import forms.LogForm;
import javax.swing.JOptionPane;

/**
 *
 * @author gazda
 */
public class MainProgram {
    public static void main(String[] args) {
        try{
            ClientController.getInstance();
            LogForm rlf = new LogForm();
            rlf.setLocationRelativeTo(null);
            rlf.setVisible(true);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR! Unsuccesful connection to server!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
