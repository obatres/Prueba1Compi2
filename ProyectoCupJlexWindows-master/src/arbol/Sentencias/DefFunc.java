/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

import arbol.Nodo;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class DefFunc {

    /**
     * @return the InstruccionesFuncion
     */
    public LinkedList<Nodo> getInstruccionesFuncion() {
        return InstruccionesFuncion;
    }
    
    private LinkedList<Nodo> InstruccionesFuncion;

    public DefFunc(LinkedList<Nodo> InstruccionesFuncion) {
        this.InstruccionesFuncion = InstruccionesFuncion;
    }

    
}
