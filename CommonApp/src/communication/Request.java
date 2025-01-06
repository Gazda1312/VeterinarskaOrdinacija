/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author gazda
 */
public class Request implements Serializable{
    private int operation;
    private Object argument;

    public Request(int operation, Object argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public Object getArgument() {
        return argument;
    }

    public int getOperation() {
        return operation;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
    
    
    
}
