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
public class IdiomSelection extends AtmOperation{ //AUNQUE NO TENGAMOS QUE IMPLIMENTARLO YA LO HABÍAMOS EMPEZADO POR LO QUE LO HEMOS DEJADO
    public IdiomSelection(OperationContext c) {
        super(c);
    }
    @Override
    public boolean doOperation() {
        ATM simulator = getContext().getAtm();
        
        simulator.setTitle("Elija el idioma que desea");
        simulator.setInputAreaText("");
        simulator.setOption(0, "Español");
        simulator.setOption(1, "English");
        simulator.setOption(3, "Català");
        simulator.setOption(4, "Euskara");
        
        char event = simulator.waitEvent(30);
        
        if(event == 'N'){
            ClientGoodBye fin = new ClientGoodBye(getContext());
            fin.doOperation();
            return false;
        }
        
        if(event != 0){
            while((event != 'A') && (event != 'B') && (event != 'D') && (event != 'E')){
                if(event != 0){
                    event = simulator.waitEvent(30);
                }else{
                    ClientGoodBye fin = new ClientGoodBye(getContext());
                    fin.doOperation();
                }
            }
            
            //REALMENTE NO CAMBIA EL IDIOMA PORQUE NO HEMOS AVERIGÜADO COMO HACERLO
            switch(event){
                case 'A': //Español
                    simulator.setInputAreaText("Español");
                    event = simulator.waitEvent(1);
                    break;

                case 'B': //Inglés
                    simulator.setInputAreaText("English");
                    event = simulator.waitEvent(1);
                    break;

                case 'D': //Catalán
                    simulator.setInputAreaText("Català");
                    event = simulator.waitEvent(1);
                    break;

                case 'E': //Euskera
                    simulator.setInputAreaText("Euskara");
                    event = simulator.waitEvent(1);
                    break;
            }
            return true;
        }else{
            ClientGoodBye fin = new ClientGoodBye(getContext());
            fin.doOperation();
            return false;
        }
    }   
}
