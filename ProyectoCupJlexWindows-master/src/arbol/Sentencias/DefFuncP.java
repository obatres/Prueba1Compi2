/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

import arbol.Funciones.Parametro;
import arbol.Nodo;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class DefFuncP {

    /**
     * @return the ParametrosFuncion
     */
    public ArrayList<Parametro> getParametrosFuncion() {
        return ParametrosFuncion;
    }

    /**
     * @return the InstruccionesFuncion
     */
    public LinkedList<Nodo> getInstruccionesFuncion() {
        return InstruccionesFuncion;
    }
        private ArrayList<Parametro> ParametrosFuncion;
    
        private LinkedList<Nodo> InstruccionesFuncion;

    public DefFuncP(ArrayList<Parametro> ParametrosFuncion, LinkedList<Nodo> InstruccionesFuncion) {
        this.ParametrosFuncion = ParametrosFuncion;
        this.InstruccionesFuncion = InstruccionesFuncion;
    }
        
        
}
