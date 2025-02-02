/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicacajeroautomatico;

/**
 *
 * @author c.garciarom.2023
 */
public abstract class TitledOperation extends AtmOperation{
    public TitledOperation(OperationContext c) {
        super(c);
    }
    public abstract String getTitle();
}
