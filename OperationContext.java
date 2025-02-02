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
public class OperationContext {
    private ATM atm;
    private UrjcBankServer bankServer;
    private String idiom;
    
    public OperationContext(ATM a, UrjcBankServer s, String i){ //Lo necesitamos para poder operar en el main
        atm = a;
        bankServer = s;
        idiom = i;
    }
    
    public UrjcBankServer getServer(){
        return bankServer;
    }
    
    public ATM getAtm(){
        return  atm;
    }
    
    public String getIdiom(){
        return idiom;
    }
    
    public void setIdiom(String s){
        idiom = s;
    }
}