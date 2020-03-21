/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones;


import arbol.Expresion;
import arbol.Nodo;
import arbol.TablaDeSimbolos;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class Funcion{

    /**
     * @return the identificadorFuncion
     */
    public String getIdentificadorFuncion() {
        return identificadorFuncion;
    }

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

    /**
     * @return the ValordeRetorno
     */
    public Expresion getValordeRetorno() {
        return ValordeRetorno;
    }
    
    private String identificadorFuncion;
    
    private ArrayList<Parametro> ParametrosFuncion;
    
    private LinkedList<Nodo> InstruccionesFuncion;
    
    private Expresion ValordeRetorno;

    public Funcion(String identificadorFuncion, ArrayList<Parametro> ParametrosFuncion, LinkedList<Nodo> InstruccionesFuncion) {
        this.identificadorFuncion = identificadorFuncion;
        this.ParametrosFuncion = ParametrosFuncion;
        this.InstruccionesFuncion = InstruccionesFuncion;
    }
    
    public Funcion(String identificadorFuncion, LinkedList<Nodo> InstruccionesFuncion) {
        this.identificadorFuncion = identificadorFuncion;
        this.InstruccionesFuncion = InstruccionesFuncion;
    }

}
