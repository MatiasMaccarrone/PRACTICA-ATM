/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicacajeroautomatico;

import sienens.ATM;
import urjc.UrjcBankServer;

/**
 *
 * @author c.garciarom.2023
 */
public class ClientManagement extends AtmOperation{
    public ClientManagement(OperationContext c){
        super(c); //llamamos al constructor del padre
    }
    
    @Override
    public boolean doOperation(){
        //1. INTRODUCCIÓN DE TARJETA
        waitForClient();

        //2. ELECCIÓN DE IDIOMA
        IdiomSelection idiom = new IdiomSelection(getContext());
        idiom.doOperation();   //ELECCION DE IDIOMAS

        //3. INTRODUCIR CONTRASEÑA
        clientIdentification();
        
        //4. REALIZAR OPERACIONES
        //COMO SOLO SE REALIZAN SOLO SI LA CONTRASEÑA ES CORRECTA SE INSTANCIA ARRIBA
        return true;        
    }
    
    private void presentOptions(){
        OptionMenu options = new OptionMenu(getContext());
        options.doOperation();  //SE MUESTRAN LAS OPERACIONES DISPONIBLES
    }
    
    private void waitForClient(){ //ESPERAMOS A QUE EL CLIENTE INTRODUZCA LA TARJETA
        ATM simulator = getContext().getAtm();
        simulator.setTitle("Bienvenido al URJC Bank");
        simulator.setInputAreaText("Introduzca la tarjeta");
        
        for (int cont = 0; cont < 6; cont++){ //PARA QUE NO APAREZCAN LAS OPCIONES
            simulator.setOption(cont, null);
        }

        char event =  simulator.waitEvent(30);
        while (event != 1){ //HASTA QUE SE DETECTE LA TARJETA
            event = simulator.waitEvent(30);
        }
        simulator.retainCreditCard(false); //PARA QUE NO SE QUEDE LA TARJETA
    }
    
    private void clientIdentification(){ //SE SOLICITA LA CONTRASEÑA
        ClientIdentification identification = new ClientIdentification(getContext());
        boolean valid = identification.doOperation();    //VALIDAR CONTRASEÑA
        
        if(valid == true){ //CONTRASEÑA ACERTADA
            presentOptions();
        }
    }
}
