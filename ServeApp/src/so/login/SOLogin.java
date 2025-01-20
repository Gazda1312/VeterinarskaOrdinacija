/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import abstractSO.AbstractSO;
import domen.AbstractDomainObject;
import domen.Veterinar;
import java.util.ArrayList;
import java.util.List;
import logic.ServerController;
import database.DatabaseBroker;
/**
 *
 * @author gazda
 */
public class SOLogin extends AbstractSO{

    private Veterinar currentVeterinar;
    
  

    


    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(ado instanceof Veterinar) {
            Veterinar v = (Veterinar) ado;
            for(Veterinar vet : ServerController.getInstance().getLoggedInVeterinars())
                if(v.getUsername().equals(vet.getUsername()))
                    throw new Exception("Vet je vec ulogovan");
        }else{
            throw new Exception("Objekat nije instanca Veterinar");
        }
    }

    

    public Veterinar getCurrentVeterinar() {
        return currentVeterinar;
    }

    
    protected void execute(AbstractDomainObject ado) throws Exception {
        Veterinar veterinar = (Veterinar) ado;
        if(DatabaseBroker.findRowAndReturn(veterinar)) {
            currentVeterinar = veterinar;
            ServerController.getInstance().getLoggedInVeterinars().add(veterinar);
            
            System.out.println("Uspesan login: " + veterinar.getIme() + veterinar.getPrezime());
            
            return;
        }
        throw new Exception("Ne postoji!");
    }

   
    
    
}
