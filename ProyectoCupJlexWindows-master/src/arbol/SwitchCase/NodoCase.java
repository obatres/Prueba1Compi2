/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.SwitchCase;

import arbol.Expresion;
import arbol.Nodo;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class NodoCase {


    
    private Expresion exp;
    private LinkedList<Nodo> InstruccionesDeCase;

    public NodoCase(Expresion exp, LinkedList<Nodo> InstruccionesDeCase) {
        this.exp = exp;
        this.InstruccionesDeCase = InstruccionesDeCase;
    }
    
    public NodoCase(LinkedList<Nodo> InstruccionesDeCase){
        this.InstruccionesDeCase = InstruccionesDeCase;
        this.exp=null;
    }
    
        /**
     * @return the InstruccionesDeCase
     */
    public LinkedList<Nodo> getInstruccionesDeCase() {
        return InstruccionesDeCase;
    }

    /**
     * @return the exp
     */
    public Expresion getExp() {
        return exp;
    }
       
}