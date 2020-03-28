/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Retorno;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.TablaDeSimbolos;
import arbol.Tipo;

/**
 *
 * @author obatres_
 */
public class Retorno extends Instruccion{
    
    private Expresion exp;

    public Retorno(Expresion exp) {
        this.exp = exp;
    }

    public Retorno() {
        this.exp=null;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if(exp!=null){
            return exp.ejecutar(ts);            
        }else{
            return 0;
        }

    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return cont;
    }


    public Tipo GetTipo(TablaDeSimbolos ts) {
        if(exp!=null){
            return exp.GetTipo(ts);
        }else{
            return null;
        }
    }
    
}
