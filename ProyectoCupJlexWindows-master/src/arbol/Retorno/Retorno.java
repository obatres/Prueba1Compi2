/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Retorno;

import arbol.Expresion;
import arbol.TablaDeSimbolos;
import arbol.Tipo;

/**
 *
 * @author obatres_
 */
public class Retorno extends Expresion{
    
    private Expresion exp;

    public Retorno(Expresion exp) {
        this.exp = exp;
    }

    public Retorno() {
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        return exp.ejecutar(ts);
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        return exp.GetTipo(ts);
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return cont;
    }
    
}
