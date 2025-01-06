/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import communication.Communication;
import communication.Operation;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import communication.Sender;
import domen.Veterinar;
import java.io.IOException;
import java.net.Socket;
import javax.sound.midi.Receiver;

/**
 *
 * @author gazda
 */
public class ClientController {
    private static ClientController instance = null;
    private Veterinar currentVeterinar;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if(instance == null) instance = new ClientController();
        return instance;
    }
    
    public Veterinar login(Veterinar veterinar) throws Exception {
      
        Request req = new Request(Operation.LOGIN, veterinar);
        Response response = Communication.getInstance().login(req);
        
       
        Veterinar vet = (Veterinar) response.getResult();
//        System.out.println(vet.toString());
        return vet;
        
    }
    
    
    
    
}
