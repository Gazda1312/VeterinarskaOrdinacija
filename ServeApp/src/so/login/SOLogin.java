/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import abstractSO.AbstractSO;
import domen.AbstractDomainObject;
import domen.Veterinar;
import java.util.ArrayList;
import logic.ServerController;
import database.DatabaseBroker;
/**
 *
 * @author gazda
 */
public class SOLogin extends AbstractSO{

    private Veterinar loggedIn;


    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (ado == null) {
        System.out.println("Prosleđeni objekat je null!");
        throw new Exception("The object is the wrong type");
        }
        if (!(ado instanceof Veterinar)) {
            System.out.println("Prosleđeni objekat nije instanca Veterinar! Tip: " + ado.getClass().getName());
            throw new Exception("The object is the wrong type");
        }
        System.out.println("Validacija je prošla: " + ado.toString());
    }

    @Override
    protected void execute(AbstractDomainObject ddo) throws Exception {
        Veterinar veterinar = (Veterinar) ddo;

        // Dohvatanje svih veterinara iz baze
        ArrayList<AbstractDomainObject> allVeterinarians = dbb.returnAll(veterinar);

        // Proverava da li postoji korisnik sa datim username-om i password-om
        for (AbstractDomainObject obj : allVeterinarians) {
            Veterinar dbVeterinar = (Veterinar) obj;
            if (dbVeterinar.getUsername().equals(veterinar.getUsername()) && 
                dbVeterinar.getPassword().equals(veterinar.getPassword())) {

                // Proverava da li je veterinar već ulogovan
                for (Veterinar logged : ServerController.getInstance().getLoggedInVeterinars()) {
                    if (logged.getUsername().equals(dbVeterinar.getUsername())) {
                        throw new Exception("Veterinar je već ulogovan!");
                    }
                }

                // Postavlja ulogovanog veterinara i dodaje ga na listu
                loggedIn = dbVeterinar;
                ServerController.getInstance().getLoggedInVeterinars().add(dbVeterinar);
                break;
            }
        }

        // Ako nije pronađen odgovarajući veterinar
        throw new Exception("Ne postoji veterinar sa zadatim vrednostima.");
    }

    public Veterinar getLoggedIn() {
        return loggedIn;
    }
    
    
}
