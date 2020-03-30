/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

import arbol.DeclaracionVariable;
import arbol.Expresion;
import arbol.Funciones.LlamadaFuncion;
import arbol.Instruccion;
import arbol.TablaDeSimbolos;

/**
 *
 * @author obatres_
 */
public class Sentencia extends Instruccion{

    
    public Object Sentencia;
    //public int tipo;

    /*
    *
    *   Tipo = 1; es una declaracion de variable
    *
    */
    public Sentencia(Object Sentencia) {
        this.Sentencia = Sentencia;
        
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
            if(Sentencia instanceof Asigna){   
                // <editor-fold desc="DECLARACION DE VARIABLE">> 
                DeclaracionVariable d = new DeclaracionVariable(((Asigna)Sentencia).getExp(), ((Asigna)Sentencia).getIdentificador());
                return d.ejecutar(ts);
                // </editor-fold>
            }else if(Sentencia instanceof LlamadaAMetodo){
                // <editor-fold desc="LLAMADA A METODO">> 
                LlamadaFuncion f;
                if(((Llamada)((LlamadaAMetodo)Sentencia).getLlamada()).getParametrosLlamada()==(null)){
                    // <editor-fold desc="SIN PARAMETROS">> 
                    f = new LlamadaFuncion (((LlamadaAMetodo)Sentencia).getIdentificador());
                    return f.ejecutar(ts);
                    // </editor-fold>
                }else{
                    // <editor-fold desc="CON PARAMETROS">> 
                    f = new LlamadaFuncion(((Llamada)((LlamadaAMetodo)Sentencia).getLlamada()).getParametrosLlamada(),((LlamadaAMetodo)Sentencia).getIdentificador());
                    return f.ejecutar(ts);
                    // </editor-fold>
                }
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
