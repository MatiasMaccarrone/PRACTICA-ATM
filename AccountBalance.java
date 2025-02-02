/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicacajeroautomatico;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import sienens.ATM;
import urjc.UrjcBankServer;

/**
 *
 * @author c.garciarom.2023
 */
public class AccountBalance extends TitledOperation{
    public AccountBalance(OperationContext c) {
        super(c);
    }
    
    @Override
    public boolean doOperation() {
        ATM simulator = getContext().getAtm();
        UrjcBankServer bankServer = getContext().getServer();
        
        long cardNumber = simulator.getCardNumber(); //numero de la tarjeta
        
        try{
            LinkedList text = new LinkedList();
            //Imprimimos un ticket con el saldo
            text.add("\nSALDO DISPONIBLE: " + bankServer.avaiable(cardNumber));
            text.add("\n                   URJC BANK");
            simulator.print(text);
            return true;
        }catch(CommunicationException ex){
            Logger.getLogger(UrjcAtm.class.getName()).log(Level.SEVERE, null, ex);
            ErrorExit error = new ErrorExit(getContext());
            error.doOperation();
            return false;
        }
    }
    
    @Override
    public String getTitle() {
        return "AccountBalance";
    } 
}
