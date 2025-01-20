/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import domen.Veterinar;
import java.util.ArrayList;
import model.VeterinarTableModel;
import thread.ServerThread;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gazda
 */
public class ActiveUsers extends javax.swing.JFrame {

    private ServerThread serverThread;
    private ArrayList<Veterinar> currentVeterinars;
    
    /**
     * Creates new form ActiveUsers
     */
    public ActiveUsers(ServerForm aThis, boolean par, ServerThread serverThread) {
        
        initComponents();
        
        currentVeterinars = new ArrayList<>();
        this.serverThread = serverThread;
        tblActiveUsers.setModel(new VeterinarTableModel(currentVeterinars));
        adminListener();
        this.setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblActiveUsers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Aktivni Veterinari", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 14))); // NOI18N

        tblActiveUsers.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblActiveUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblActiveUsers);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblActiveUsers;
    // End of variables declaration//GEN-END:variables

    private void adminListener() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        prepareView();
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ActiveUsers.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }).start();
    }
    
    private void prepareView() {
        // Dohvati listu aktivnih korisnika sa servera
        ArrayList<Veterinar> newVeterinars = serverThread.getActiveUsers();

        // Proveri da li ima promene u listi aktivnih korisnika
        if (!hasListChanged(newVeterinars)) {
            // Ako se lista nije promenila, ne osvežavaj tabelu
            return;
        }

        // Ažuriraj trenutnu listu aktivnih korisnika
        currentVeterinars = newVeterinars;

        // Postavi novi model na tabelu
        tblActiveUsers.setModel(new VeterinarTableModel(currentVeterinars));
    }

// Proverava da li se lista aktivnih korisnika promenila
    private boolean hasListChanged(ArrayList<Veterinar> newVeterinars) {
        if (currentVeterinars.size() != newVeterinars.size()) {
            return true;
        }

        // Uporedi svaki element
        for (int i = 0; i < currentVeterinars.size(); i++) {
            if (!currentVeterinars.get(i).equals(newVeterinars.get(i))) {
                return true;
            }
        }

        return false;
    }
}
