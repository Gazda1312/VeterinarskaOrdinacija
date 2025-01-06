/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import database.DatabaseBroker;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gazda
 */
public class ServerThread extends Thread{
    private ServerSocket serverSocket;
    private ArrayList<ClientThread> clients;
    private static int port = 9000;

    public ServerThread() {
        try{
            DatabaseBroker dbb = new DatabaseBroker();
            dbb.connect();
            serverSocket = new ServerSocket(port);
            System.out.println("Creating server socket on port: " + port);
            clients = new ArrayList<>();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public void run() {
        System.out.println("Waiting for connections.... ");
        try{
            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection succ!");
                ClientThread ct = new ClientThread(socket, this);
                ct.start();
                clients.add(ct);             
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void stopServerThread() {
        try{
            
            if(clients == null) {
                System.out.println("No one is active, server and program closing!");
                System.exit(0);
            }
            
            for (ClientThread client : clients) {
                client.closeSocket();
            }
            
            if(serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
       
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

       
    

   
    
    
    
    
    
}
