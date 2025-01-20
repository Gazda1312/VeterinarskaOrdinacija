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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private Veterinar veterinar;
    

    public ClientThread(Socket socket) {
        this.socket = socket;
    }
    
    private Response handleReq(Request r) {
        Response response = new Response();
        Boolean success;
        try{
            switch(r.getOperation()){
                case Operation.LOGIN:
                    Veterinar vet = (Veterinar) r.getArgument();
                    vet = ServerController.getInstance().login(vet);
                    this.veterinar = vet;
                    response.setResult(vet);
                    break;
                default:
                    return null;
        }
    }catch(Exception e) {
        e.printStackTrace();
        response.setException(e);
    }
    
        
   
    
   return response; 
  }
    @Override
    public void run() {
        try {
            while(!socket.isClosed()){
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleReq(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public Veterinar getVeterinar() {
        return veterinar;
    }

    public void setVeterinar(Veterinar veterinar) {
        this.veterinar = veterinar;
    }
    
    
    
}
