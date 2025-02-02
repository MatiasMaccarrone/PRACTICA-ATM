/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicacajeroautomatico;

import sienens.ATM;

/**
 *
 * @author c.garciarom.2023
 */
public class ErrorExit extends AtmOperation{
    public ErrorExit(OperationContext c) {
        super(c);
    }
    
    @Override
    public boolean doOperation() {
        ATM simulator = getContext().getAtm();
        
        simulator.setTitle("Error en la Conexion con el Banco");
        
        for(int i = 0; i < 6; i++){
            simulator.setOption(i, null);
        }

        simulator.setInputAreaText("Cajero Fuera de Servicio");
        
        if(simulator.waitEvent(3) == 0){
            simulator.setInputAreaText("Recoja su tarjeta");
        }
        
        if(!simulator.expelCreditCard(30)){ //SI NO SE RECOGE LA TARJETA EN 30 SEGUNDOS SE LA TRAGA EL CAJERO
            simulator.setTitle("Tarjeta retenida");
            simulator.setInputAreaText("Contacte con el banco");
            simulator.retainCreditCard(true);
            char event = simulator.waitEvent(1); 
        }
        
        String[] a ={""};
        UrjcAtm.main(a);
        return true;
    }
}
