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
public  class If extends Instruccion{
    
    private final Operacion condicion;
    
    private final LinkedList<Nodo> ListaDeInstrucciones;
    
    private LinkedList<Nodo> ListaDeInstruccionesElse;

    public If(Operacion condicion, LinkedList<Nodo> ListaDeInstrucciones) {
        this.condicion = condicion;
        this.ListaDeInstrucciones = ListaDeInstrucciones;
    }

    public If(Operacion condicion, LinkedList<Nodo> ListaDeInstrucciones, LinkedList<Nodo> ListaDeInstruccionesElse) {
        this.condicion = condicion;
        this.ListaDeInstrucciones = ListaDeInstrucciones;
        this.ListaDeInstruccionesElse = ListaDeInstruccionesElse;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if((Boolean)condicion.ejecutar(ts)){
            TablaDeSimbolos tablalocal = new TablaDeSimbolos();
            tablalocal.addAll(ts);
            for(Nodo in: ListaDeInstrucciones){
                if (in instanceof Instruccion){
                    ((Instruccion) in).ejecutar(ts);
                }
            }
        }else{
            if(ListaDeInstruccionesElse!=null){
                TablaDeSimbolos tablalocal = new TablaDeSimbolos();
                tablalocal.addAll(ts);
                for(Nodo in: ListaDeInstruccionesElse){
                    if(in instanceof Instruccion){
                        ((Instruccion)in).ejecutar(ts);
                    }
                }
            }
        }
        return null;
    }
    
    
}
