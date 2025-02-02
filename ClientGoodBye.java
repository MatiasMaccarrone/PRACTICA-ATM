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
public class ClientGoodBye extends AtmOperation{
    public ClientGoodBye(OperationContext c){ //necesitamos el constructor para poder acceder al context del main
        super(c);
    }
    
    @Override
    public boolean doOperation() {
        ATM simulator = getContext().getAtm();
        
        simulator.setTitle("Sesión finalizada");
        
        for(int i = 0; i < 6; i++){
            simulator.setOption(i, null);
        }
        
        simulator.setInputAreaText("Recoja la Tarjeta");
        
        if(!simulator.expelCreditCard(30)){ //SI NO SE RECOGE LA TARJETA EN 30 SEGUNDOS
            simulator.setTitle("Tarjeta retenida");
            simulator.setInputAreaText("Contacte con el banco");
            simulator.retainCreditCard(true);
            char event = simulator.waitEvent(1); 
        }
        String[] a ={""}; //AUNQUE SE QUEDE LA TARJETA HACEMOS QUE VUELVA AL PRINCIPIO COMO EN EL DIAGRAMA DE ACTIVIDAD
        UrjcAtm.main(a);
        return true;
    }
}
