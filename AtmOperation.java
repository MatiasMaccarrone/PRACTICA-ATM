/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicacajeroautomatico;

/**
 *
 * @author c.garciarom.2023
 */
public abstract class AtmOperation {
    private OperationContext context;
    
    public AtmOperation(OperationContext c){
        context = c;
    }
    
    public OperationContext getContext(){ //Para poder usar el mismo que en el del main
        return context;
    }
    
    public abstract boolean doOperation();
}
