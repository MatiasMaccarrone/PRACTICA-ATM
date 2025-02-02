/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicacajeroautomatico;

import java.util.List;
import sienens.ATM;

/**
 *
 * @author c.garciarom.2023
 */
public class OptionMenu extends AtmOperation{
    private List<AtmOperation> operationList;

    public OptionMenu(OperationContext c) {
        super(c);
    }
    
    @Override
    public boolean doOperation() {
        ClientGoodBye fin = new ClientGoodBye(getContext());
        WithdrawCash withdraw = new WithdrawCash(getContext());
        LastOperation list = new LastOperation(getContext());
        AccountBalance balance = new AccountBalance(getContext());
        DepositMoney deposit = new DepositMoney(getContext());
        
        ATM simulator = getContext().getAtm();
        
        char event;
        boolean done = true;
        //PRESENTAMOS LAS OPCIONES
        do{
            simulator.setTitle("Seleccione la opción que desee: ");
            simulator.setInputAreaText("");
            simulator.setOption(0, "Sacar dinero");
            simulator.setOption(1, "Últimas operaciones");
            simulator.setOption(2, null);
            simulator.setOption(3, "Consultar saldo");
            simulator.setOption(4, "Ingresar dinero");
            simulator.setOption(5, "Terminar");

            //ESPERAMOS A QUE SE PULSE UNA OPCIÓN
            event = simulator.waitEvent(30);
            simulator.setInputAreaText("");

            //SEGÚN QUé OPCIÓN SE HAYA ELEGIDO SE HARÁ UNA COSA U OTRA  
            // DENTRO DE CADA CASE HAY QUE LLAMAR AL doOperation DE LA CLASE DE LA OPERACION SELECCIONADA
            //EJEMPLO: event = A  ---> WithDrawCash.doOperation() (ESTE doOperation() HARIA LO MISMO QUE ANTES)
            switch(event){
                case 'N': //botón rojo
                    fin.doOperation();
                    break;
                    
                case 'A': //sacar dinero
                    done = withdraw.doOperation();
                    break;

                case 'B': //últimas operaciones
                    done = list.doOperation();
                    break;
                    
                case 'D': //consultar saldo
                    done = balance.doOperation();
                    break;
                    
                case 'E': //ingresar dinero
                    done = deposit.doOperation();
                    break;
                
                case 'F': //TERMINAR
                    //LO HACEMOS ASÍ PARA QUE TERMINE DIRECTO Y NO PREGUNTE SI QUIERE 
                    //REALIZAR MÁS OPERACIONES
                    simulator.setTitle("Sesión finalizada");
                    for(int i = 0; i < 6; i++){
                        simulator.setOption(i, null);
                    }
                    simulator.setInputAreaText("Recoja la Tarjeta");

                    simulator.expelCreditCard(30);
                    ClientManagement manager = new ClientManagement(getContext());
                    manager.doOperation();
                    break;
            }
            
            //PARA SABER SI QUIERE SEGUIR OPERANDO O QUIERE TERMINAR 
            
            if((event == 'A') || (event == 'B') || (event == 'C') || (event == 'D') || (event == 'E')){
                do{
                    if(done == true){
                        simulator.setTitle("Operación Realizada");
                    }else{
                        simulator.setTitle("Operación NO Realizada");
                    }
                    simulator.setOption(1, "Cerrar Sesión");
                    simulator.setOption(4, "Seguir Operando");
                    simulator.setOption(2, null);
                    simulator.setOption(3, null);
                    simulator.setOption(5, null);
                    simulator.setOption(0, null);
                    simulator.setInputAreaText("");
                    
                    event= simulator.waitEvent(30);
                    
                    if(event == 'B' || event == 'N' || event == 0){
                        fin.doOperation();
                    }
                }while (event == '0' || event == '1' || event == '2' || event == '3' || event == '4' || 
                        event == '5' || event == '6' || event == '7' || event == '9' || event == '-' ||
                        event == 'Y'); //SI NO PONEMOS ESTO Y SE PULSA UNA TECLA DE LOS NÚMEROS EL CAJERO DA UN FALLO
            }else if(event == 0){
                fin.doOperation();
            }
        }while(event == 'E' || event == '0' || event == '1' || event == '2' || event == '3' || event == '4' || 
                        event == '5' || event == '6' || event == '7' || event == '9' || event == '-' ||
                        event == 'Y');
        return true;
    } 
}
