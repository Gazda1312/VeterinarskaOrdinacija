/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import domen.Veterinar;
import java.util.ArrayList;
import so.login.SOLogin;

/**
 *
 * @author gazda
 */
public class ServerController {
    private static ServerController instance;
    private ArrayList<Veterinar> loggedInVeterinars = new ArrayList<>();

    public ServerController() {
    }

    public ArrayList<Veterinar> getLoggedInVeterinars() {
        return loggedInVeterinars;
    }
    
    
    
    public static ServerController getInstance() {
        if(instance == null) instance = new ServerController();
        return instance;
    }
    
    public Veterinar login(Veterinar veterinar) throws Exception {
        
        System.out.println("Veterinar objekat pre slanja u SOLogin: " + veterinar.getIme());
        SOLogin so = new SOLogin();
        so.executeSO(veterinar);
        return so.getLoggedIn();
    }
    
}
