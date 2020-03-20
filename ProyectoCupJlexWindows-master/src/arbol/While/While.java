/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.While;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.Nodo;
import arbol.TablaDeSimbolos;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class While extends Instruccion{
    
    private Expresion exp;
    private LinkedList<Nodo> ListaDeInstrucciones;

    public While(Expresion exp, LinkedList<Nodo> ListaDeInstrucciones) {
        this.exp = exp;
        this.ListaDeInstrucciones = ListaDeInstrucciones;
    }

    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        while ((Boolean)exp.ejecutar(ts)) {
            TablaDeSimbolos tablalocal = new TablaDeSimbolos();
            tablalocal.addAll(ts);
            for (Nodo en : ListaDeInstrucciones) {
                if(en instanceof Instruccion){
                    ((Instruccion) en).ejecutar(tablalocal);
                }  
            } 
        }
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"While\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        for (Nodo n : ListaDeInstrucciones) {
            if(n instanceof Instruccion){
                cont = ((Instruccion) n).Dibujar(builder, nodo, cont);   
            }
        }
        return cont;
    }
}
