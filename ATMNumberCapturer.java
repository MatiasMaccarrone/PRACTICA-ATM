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
public class ATMNumberCapturer extends AtmOperation{//si no pongo que es hijo de atmOperation no podré usar el atm y ujrc bank server del main
    public ATMNumberCapturer(OperationContext c) {
        super(c);
    }
    
    public int captureAmount(){
        ATM simulator = getContext().getAtm();
        
        simulator.setTitle("Teclee la cantidad que desea");
        
        for (int cont = 0; cont < 6; cont++) //PARA QUE NO APAREZCAN LAS OPCIONES
            simulator.setOption(cont, null);
        
        simulator.setInputAreaText("");
        
        char event = simulator.waitEvent(30);
        String cadena = "";
        
        if(event == 'N' || (event == 0)){ //SI PULSA EL BOTÓN ROJO O NO PULSA NADA
            ClientGoodBye fin = new ClientGoodBye(getContext());
            fin.doOperation();
        }
        
        while ((event >= '0' && event <= '9') || event == '-' || event == 'N'){ //ESCRIBE LA CANTIDAD QUE SE TECLEA EN PANTALLA
            if(event != '-'){
                cadena += event;
                simulator.setInputAreaText(cadena + " €");
                
                if((cadena.length() == 1) && (event == '0')){
                    cadena = cadena.substring(0, cadena.length() - 1);
                }
            }
            event = simulator.waitEvent(30);
            if((event == '-') && (cadena.length() > 0)){ //SOLO SE PUEDE BORRAR SI LA CADENA NO ESTÁ VACÍA
                cadena = cadena.substring(0, cadena.length()-1);
                simulator.setInputAreaText(cadena + " €");
            }else if(event == 0){ //SI NO PULSA NADA
                ClientGoodBye fin = new ClientGoodBye(getContext());
                fin.doOperation();
            }
            
            if(event == 'N'){ //BOTON ROJO HAY QUE VOLVER A PONERLO PORQUE SI NO SOLO FUNCIONA AL PRINCIPIO
                ClientGoodBye fin = new ClientGoodBye(getContext());
                fin.doOperation();
            }else if((event == 'Y') && (cadena.length() > 0)){ //BOTON VERDE
                try{ //SI SE ESCRIBE UN NÚMERO MUY GRANDE NO SE PUEDE PASAR A ENTERO
                    int amount = Integer.parseInt(cadena); //PASO DE STRING A INT
                    return amount;
                }catch(NumberFormatException e){
                    simulator.setTitle("ERROR");
                    
                    for(int i = 0; i < 6; i++){
                        simulator.setOption(i, null);
                    }
                    
                    simulator.setInputAreaText("Se excede el limite de dinero");
                    event = simulator.waitEvent(2);
                }
            }
            
        }
        return captureAmount(); //NO SE LLEGA NUNCA AQUÍ PERO TENEMOS QUE PONERLO PARA QUE NO HAYA ERROR
    }
    
    public int capturePassword(){
        ATM simulator = getContext().getAtm();
        String password = "";
        String mostrar = "";
        char event= simulator.waitEvent(30);
  
        if(event != 0){
            while(event >= '0' && event <= '9' || event == '-'){ //ESCRIBE LA CONTRASEÑA QUE SE TECLEE EN PANTALLA
                if(event != '-'){
                    simulator.setInputAreaText(mostrar + event);
                    password += event;
                    mostrar += "*";
                }
                event = simulator.waitEvent(30);
                if(event == '-'){
                    if(password.length() > 0){ 
                        password = password.substring(0, password.length() - 1);
                        mostrar = mostrar.substring(0, mostrar.length() - 1);
                        simulator.setInputAreaText(mostrar);
                    }
                }else if(event == 0){ //HAY QUE CONTEMPLAR QUE NO HAGA NADA
                    ClientGoodBye fin = new ClientGoodBye(getContext());
                    fin.doOperation();
                }
            }
        }else if(event == 0 || event == 'N'){ // VOLVEMOS A CONTEMPLAR SI NO SE HACE NADA O SI SE PULSA EL BOTÓN ROJO
            ClientGoodBye fin = new ClientGoodBye(getContext());
            fin.doOperation();
        }
        
        if(event == 'N'){//SI NO VOLVEMOS A CONTEMPLARLO NO SE PUEDE USAR EL BOTON ROJO
            ClientGoodBye fin = new ClientGoodBye(getContext());
            fin.doOperation();

        }else if((event == 'Y') && (password.length() > 0)){ //BOTON VERDE
            int prueba = Integer.parseInt(password); //SE CONVIERTE A ENTERO LA PASSWORD INGRESADA
            return prueba;
        }
        
        return capturePassword(); //IGUAL QUE ANTES, NO LLEGA, PERO HAY QUE PONERLO PARA QUE NO HAYA FALLO
    }

    @Override
    public boolean doOperation() { //COMO NO SE USA NO LO IMPLEMENTAMOS
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
