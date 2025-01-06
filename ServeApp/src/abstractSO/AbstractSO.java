/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstractSO;

import database.DatabaseBroker;
import domen.AbstractDomainObject;
import java.sql.SQLException;
import database.DatabaseBroker;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gazda
 */
public abstract class AbstractSO {
    
    protected DatabaseBroker dbb;

    public AbstractSO() {
        this.dbb = new DatabaseBroker();
    }
    
    
    
    protected abstract void validate(AbstractDomainObject ado) throws Exception;
    protected abstract void execute(AbstractDomainObject ado) throws Exception;

    public void executeSO(AbstractDomainObject ado) throws Exception {
        try {
            validate(ado);
            execute(ado);
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            throw e;
        }
    }

    private void commit() throws SQLException {
        try {
            dbb.commitTransaction();
        } catch (Exception ex) {
            Logger.getLogger(AbstractSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void rollback() throws SQLException{
        try {
            dbb.rollbackTransaction();
        } catch (Exception ex) {
            Logger.getLogger(AbstractSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
}
