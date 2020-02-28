/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class SwitchCase extends Instruccion{
    /**
     * ListaDeCase es una lista que contiene elementos de tipo NodoCase que cada uno contiene una expresion y una lista de instrucciones,
     * esta expresion antes mencionada es la encargada de ser comparada con la expresion del switch.
     */
    private LinkedList<NodoCase> ListaDeCase;
    /**
     * Parametro expComp es la expresion que trae el switch y es la que se va a comparar con cada caso
     */
    private Expresion expComp;

    public SwitchCase(LinkedList<NodoCase> ListaDeCase, Expresion expComp) {
        this.ListaDeCase = ListaDeCase;
        this.expComp = expComp;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        for(NodoCase nc:ListaDeCase){
            if(nc.getExp()!=null){
                if(expComp.ejecutar(ts).equals(nc.getExp().ejecutar(ts))){
                TablaDeSimbolos tablalocal = new TablaDeSimbolos();
                tablalocal.addAll(ts);
                for (Nodo in:nc.getInstruccionesDeCase()){
                    if(in instanceof Instruccion){
                        ((Instruccion) in).ejecutar(ts);
                        }
                    }
                }
            }else{
                TablaDeSimbolos tablalocal = new TablaDeSimbolos();
                tablalocal.addAll(ts);
                for (Nodo in:nc.getInstruccionesDeCase()){
                    if(in instanceof Instruccion){
                        ((Instruccion) in).ejecutar(ts);
                        }
                    }                
            }

        }
        return null;
    }
    
}
