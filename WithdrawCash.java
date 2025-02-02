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
public class WithdrawCash extends TitledOperation{
    public WithdrawCash(OperationContext c) {
        super(c);
    }
    @Override
    public boolean doOperation() {
        ATMNumberCapturer numberCapture = new ATMNumberCapturer(getContext());
        ATM simulator = getContext().getAtm();
        UrjcBankServer bankServer = getContext().getServer();
        
        //SOLICITAMOS LA CANTIDAD A RETIRAR
        int amount = numberCapture.captureAmount();       
        
        long cardNumber = simulator.getCardNumber(); //numero de la tarjeta
        
        if(simulator.hasAmount(amount)){ //Para saber si el cajero tiene el dinero suficiente
            try {
                int disponible = bankServer.avaiable(cardNumber); //dinero en la tarjeta
                
                LinkedList text = new LinkedList();
                if (disponible >= amount){ //SOLO SI TIENE DINERO SUFICIENTE EN LA TARJETA
                    if(simulator.expelAmount(amount, 30)){ // SI SE EXPULSA EL DINERO Y SE RECOGE
                        bankServer.doOperation(cardNumber, -amount); //restamos la cantidad a la tarjeta
                                        
                        //Imprimimos un ticket con el dinero expulsado
                        text.add("Se ha expulsado el dinero correctamente");
                        text.add("\nSALDO INICIAL: " + disponible);
                        text.add("\nDINERO EXPULSADO: " + amount);
                        text.add("\nSALDO DISPONIBLE: " + bankServer.avaiable(cardNumber));
                        text.add("\n                   URJC BANK");
                        simulator.print(text);
                        return true;
                    }else{
                        simulator.setTitle("Dinero no recogido");
                        simulator.setInputAreaText("Guardando...");
                        simulator.waitEvent(2);
                        return false;
                    }
                    
                    
                }else{
                    //Imprimimos un ticket diciendo que no se puede sacar dinero porque no hay saldo suficiente
                    text.add("No tiene saldo suficiente");
                    simulator.print(text);
                    return false;
                }
                
            } catch (CommunicationException ex) {
                Logger.getLogger(UrjcAtm.class.getName()).log(Level.SEVERE, null, ex);
                ErrorExit error = new ErrorExit(getContext());
                error.doOperation();
                return false;
            }
        }else{
            simulator.setTitle("ERROR");
            simulator.setInputAreaText("El cajero no tiene billetes");
            char event = simulator.waitEvent(2);
            
            return false;
        }
    }

    @Override
    public String getTitle() {
        return "WithdrawCash";
    }
}
