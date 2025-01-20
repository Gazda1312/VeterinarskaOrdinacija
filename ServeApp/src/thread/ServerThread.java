/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import domen.Veterinar;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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
                System.out.println("Connection to server socket succesfull!");
                
                ClientThread ct = new ClientThread(socket);
                ct.start();
                clients.add(ct);             
                
            }
            
        } catch(SocketException e) {
            if(serverSocket.isClosed()) {
                //stopServerThread();
                System.out.println("Server socket closed. Server shutting down.");
            } else{
                e.printStackTrace();
            }
        }catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            System.out.println("Server stopped.");
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

    public ArrayList<Veterinar> getActiveUsers() {
        ArrayList<Veterinar> veterinars = new ArrayList<>();
        for (ClientThread ct : clients) {
            if (ct.getVeterinar() != null) {
                veterinars.add(ct.getVeterinar());
            } 
        }
        return veterinars;
    }

       
    

   
    
    
    
    
    
}
