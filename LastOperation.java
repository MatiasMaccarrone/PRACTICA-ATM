/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicacajeroautomatico;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.naming.CommunicationException;
import sienens.ATM;
import urjc.Operation;
import urjc.UrjcBankServer;

/**
 *
 * @author c.garciarom.2023
 */
public class LastOperation extends TitledOperation{
    public LastOperation(OperationContext c) {
        super(c);
    }
    
    @Override
    public boolean doOperation() {
        ATM simulator = getContext().getAtm();
        UrjcBankServer bankServer = getContext().getServer();
        
        long cardNumber = simulator.getCardNumber();
        try{
            List <Operation> operationsList = bankServer.getLastOperations(cardNumber); //CREAMOS UNA LISTA DE OPERACIONES Y LE ASIGNAMOS LA QUE TIENE EL BANCO
            
            //CREAMOS UNA LISTA DE STRINGS PARA PASAR LAS OPERACIONES A STRINGS
            List <String> operationsStringList = operationsList.stream().map(Operation -> "\nDia: " + Operation.getDate() + "\nOperación: " + 
                    Operation.getDetail()+ "\nCantidad: " + Operation.getAmount()).collect(Collectors.toList());
            
            simulator.print(operationsStringList);
            return true;
        }catch(CommunicationException e){
            Logger.getLogger(UrjcAtm.class.getName()).log(Level.SEVERE, null, e);
            ErrorExit error = new ErrorExit(getContext());
            error.doOperation();    //PANTALLA DE ERROR 
            return false;
        }
    }
    
    @Override
    public String getTitle() {
        return "LastOperation";
    }  
    
}
