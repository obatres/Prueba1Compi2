/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

import arbol.DeclaracionVariable;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.TablaDeSimbolos;

/**
 *
 * @author obatres_
 */
public class Sentencia extends Instruccion{

    
    public Object Sentencia;
    public int tipo;

    /*
    *
    *   Tipo = 1; es una declaracion de variable
    *
    */
    public Sentencia(Object Sentencia, int tipo) {
        this.Sentencia = Sentencia;
        this.tipo = tipo;
    }

    

    
    
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
            if(tipo==1){   
                // <editor-fold desc="DECLARACION DE VARIABLE">> 
                DeclaracionVariable d = new DeclaracionVariable(((Asigna)Sentencia).getExp(), ((Asigna)Sentencia).getIdentificador());
                return d.ejecutar(ts);
                // </editor-fold>
            }else{
                return null;                
            }

    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return cont;
    }
    
}
