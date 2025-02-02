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


public class DepositMoney extends TitledOperation{
    public DepositMoney(OperationContext c) {
        super(c);
    }
    
    @Override
    public boolean doOperation() {
        ATMNumberCapturer numberCapture = new ATMNumberCapturer(getContext());
        
        ATM simulator = getContext().getAtm();
        UrjcBankServer bankServer = getContext().getServer();
        
        int amount = numberCapture.captureAmount(); //CANTIDAD A RETIRAR 
        long cardNumber = simulator.getCardNumber();
        
        try {
            int disponible = bankServer.avaiable(cardNumber); //CANTIDAD DISPONIBLE EN LA TARJETA
            LinkedList text = new LinkedList();

            bankServer.doOperation(cardNumber, amount); 

            //Imprimimos un ticket con el dinero expulsado
            text.add("Se ha introducido el dinero correctamente");
            text.add("\nSALDO INICIAL: " + disponible);
            text.add("\nDINERO INGRESADO: " + amount);
            text.add("\nSALDO DISPONIBLE: " + bankServer.avaiable(cardNumber));
            text.add("\n                   URJC BANK");
            simulator.print(text);
            return true;
        } catch (CommunicationException ex) {
            Logger.getLogger(UrjcAtm.class.getName()).log(Level.SEVERE, null, ex);
            ErrorExit error = new ErrorExit(getContext());
            error.doOperation();
            return false;
        }
    }
    
    @Override
    public String getTitle() {
        return "DepositMoney";
    }
    
}
