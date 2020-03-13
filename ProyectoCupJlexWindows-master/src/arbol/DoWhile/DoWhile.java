/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.DoWhile;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.Nodo;
import arbol.TablaDeSimbolos;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class DoWhile extends Instruccion{

    private Expresion exp; 
    private LinkedList<Nodo> ListaDeInstrucciones;

    public DoWhile(Expresion exp, LinkedList<Nodo> ListaDeInstrucciones) {
        this.exp = exp;
        this.ListaDeInstrucciones = ListaDeInstrucciones;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        do {            
            TablaDeSimbolos tablalocal = new TablaDeSimbolos();
            tablalocal.addAll(ts);
            for (Nodo en : ListaDeInstrucciones) {
                if(en instanceof Instruccion){
                    ((Instruccion) en).ejecutar(ts);
                }  
            }             
        } while ((Boolean)exp.ejecutar(ts));
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Do While\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        for (Nodo n : ListaDeInstrucciones) {
            if(n instanceof Instruccion){
                cont = ((Instruccion) n).Dibujar(builder, nodo, cont);   
            }
        }
        return cont;    }
    
}
