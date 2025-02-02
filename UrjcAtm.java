/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicacajeroautomatico;

import sienens.ATM;
import urjc.UrjcBankServer;

/**
 *
 * @author c.garciarom.2023
 */
public class UrjcAtm {
    public static void main(String[] args) {
        ATM simulator = new ATM();
        UrjcBankServer bankServer = new UrjcBankServer();
        OperationContext context = new OperationContext(simulator, bankServer, "Español");
        ClientManagement manager = new ClientManagement(context); //EL MANAGER HACE TODAS LAS OPERACIONES (FUNCIONA COMO MAIN) PORQUE ASI LO PONE EL DIAGRAMA DE SECUENCIA

        manager.doOperation();
    }
}
