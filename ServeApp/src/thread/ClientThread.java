/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domen.Veterinar;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.ServerController;

/**
 *
 * @author gazda
 */
public class ClientThread extends Thread {
    private Socket socket;
    Sender sender;
    Receiver receiver;
    private Veterinar veterinar;
    ServerThread server;

    public ClientThread(Socket socket, ServerThread server) {
        this.socket = socket;
        this.server = server;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
    

   
    
    /**
     *
     */
    @Override
    public void run () {
        
        while(true) {
            try{
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try{
                    
                    switch(request.getOperation()) {
                        case Operation.LOGIN:
                            Veterinar v = (Veterinar) request.getArgument();
                            v = ServerController.getInstance().login(veterinar);
                            break;
                    
                    
                    
                    
                    
                    }
                    
                }catch (Exception ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);      
                
            }sender.send(response);
            
        }catch (Exception ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
        
        
        
        
        
        
        
        
        

        
        
        
        
    }

    public void closeSocket() {
        try{
            if(socket != null && !socket.isClosed()) {
                socket.close();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
