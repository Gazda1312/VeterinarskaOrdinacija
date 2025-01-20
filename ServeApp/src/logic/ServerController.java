/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import domen.AbstractDomainObject;
import domen.Veterinar;
import java.util.ArrayList;
import so.login.SOLogin;
import database.DatabaseBroker;
/**
 *
 * @author gazda
 */
public class ServerController {
    private static ServerController instance;
    private final ArrayList<Veterinar> loggedInVeterinars = new ArrayList<>();

    private ServerController() {
        DatabaseBroker.openConnection();
    }
    
    public static ServerController getInstance() {
        if(instance == null) instance = new ServerController();
        return instance;
    }
    
    public ArrayList<Veterinar> getLoggedInVeterinars() {
        return loggedInVeterinars;
    }

    
    public Veterinar login(Veterinar veterinar) throws Exception {
        SOLogin so = new SOLogin();
        so.executeSO(veterinar);
        return so.getCurrentVeterinar();
    }
    
}
