/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.If;

import arbol.Instruccion;
import arbol.Nodo;
import arbol.Operacion;
import arbol.TablaDeSimbolos;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class IfElse extends Instruccion{
    private final Operacion Operacion1;
    private final Operacion Operacion2;
    
    private final LinkedList<Nodo> ListaDeInstrucciones;
    
    private LinkedList<Nodo> ListaDeInstruccionesElse;

    public IfElse(Operacion Operacion1, Operacion Operacion2, LinkedList<Nodo> ListaDeInstrucciones, LinkedList<Nodo> ListaDeInstruccionesElse) {
        this.Operacion1 = Operacion1;
        this.Operacion2 = Operacion2;
        this.ListaDeInstrucciones = ListaDeInstrucciones;
        this.ListaDeInstruccionesElse = ListaDeInstruccionesElse;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if((Boolean)Operacion1.ejecutar(ts)){
            TablaDeSimbolos tablalocal = new TablaDeSimbolos();
            tablalocal.addAll(ts);
            for(Nodo in: ListaDeInstrucciones){
                if (in instanceof Instruccion){
                    ((Instruccion) in).ejecutar(ts);
                }
            }
        }else if((Boolean)Operacion2.ejecutar(ts)){
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
