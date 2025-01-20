/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.logic;

import domen.Veterinar;
import communication.*;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author gazda
 */
public class ClientController {
    private static ClientController instance=null;
    private final Socket socket;
    private final Sender sender;
    private final Receiver receiver;
    private ClientController()throws IOException{
        socket = new Socket("127.0.0.1", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public static ClientController getInstance() throws IOException {
       if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }
    
    public Veterinar login(Veterinar vet) throws Exception {
       return (Veterinar) sendRequest(Operation.LOGIN, vet);
        
    }

    private Object sendRequest(int operation, Object arg) throws Exception {
        Request request = new Request(operation, arg);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()!=null)
            throw response.getException();
        return response.getResult();
    }
    
}
