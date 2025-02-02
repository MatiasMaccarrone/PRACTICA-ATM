/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicacajeroautomatico;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import sienens.ATM;
import urjc.UrjcBankServer;

/**
 *
 * @author c.garciarom.2023
 */
public class ClientIdentification extends AtmOperation{
    public ClientIdentification(OperationContext c) {
        super(c);
    }
    
    @Override
    public boolean doOperation() {
        ATM simulator = getContext().getAtm();
        UrjcBankServer bankServer = getContext().getServer();
        boolean valido = false;
        int password;
        int intento = 3;
        
        //PANTALLA
        simulator.setTitle("Ingresar Contraseña");

        for (int i = 0; i < 6; i++){
            simulator.setOption(i, null);
        }

        simulator.setInputAreaText("");
        
        //INTRODUCE LA CONTRASEÑA Y LA CONVIERTE A ENTERO
        do{
            ATMNumberCapturer capture = new ATMNumberCapturer(getContext());
            password = capture.capturePassword();
            
            try{
                valido = bankServer.testPassword(password, simulator.getCardNumber()); //COMPROBAMOS QUE LA CONTRASEÑA SEA LA CORRECTA
                if(valido == false){ //SI CONTRASEÑA INCORRECTA INTENTO - 1 
                    simulator.setTitle("Contraseña Incorrecta");
                    simulator.setInputAreaText("Quedan " + --intento + " intentos.");
                }else if(valido == true){ //SI CONTRASEÑA CORRECTA SE REINICIAN LOS INTENTOS
                    intento = 3;
                    simulator.setTitle("Procesando...");
                    simulator.setInputAreaText("Contraseña Valida");
                    char event = simulator.waitEvent(1);
                    return true;
                }
            }catch (CommunicationException e){
                Logger.getLogger(UrjcAtm.class.getName()).log(Level.SEVERE, null, e);
                intento = 3;
                ErrorExit error = new ErrorExit(getContext());
                error.doOperation();
            } 
        }while(intento > 0); //MIENTRAS SEA ERRONEA Y HAYA MáS INTENTOS

        //SOLO PASA SI NO HAY MAS INTENTOS
        simulator.setTitle("Tarjeta Bloqueada");
        simulator.setInputAreaText("Tarjeta Guardada en Cajero");
        simulator.retainCreditCard(true);   //RETIENE LA TARJETA
        String[] a = {""}; //VUELVE AL MAIN PARA QUE REGENERE LAS TARJETAS Y NO TENER QUE DEVOLVER UNA TARJETA QUE YA HA SIDO RETENIDA
        UrjcAtm.main(a);
        return false;
    }
}
