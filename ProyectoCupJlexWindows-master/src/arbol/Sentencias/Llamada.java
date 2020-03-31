/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

import arbol.Expresion;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class Llamada {

    /**
     * @return the ParametrosLlamada
     */
    public ArrayList<Expresion> getParametrosLlamada() {
        return ParametrosLlamada;
    }
    private ArrayList<Expresion> ParametrosLlamada;

    public Llamada(ArrayList<Expresion> ParametrosLlamada) {
        this.ParametrosLlamada = ParametrosLlamada;
    }

    public Llamada() {
        this.ParametrosLlamada = null;
    }
    
    
}
