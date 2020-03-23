/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones.FuncionesDefinidas;

import InterfazGrafica.VentanaPrincipal;
import arbol.Expresion;
import arbol.Imprimir;
import arbol.Instruccion;

import arbol.TablaDeSimbolos;
import arbol.Tipo;

/**
 *
 * @author obatres_
 */
public class TypeOf extends Expresion{
    private Expresion exp;
    Tipo t;
    public TypeOf(Expresion exp) {
        this.exp = exp;
    }
    
    


    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        t = exp.GetTipo(ts);
        System.out.println(t.tp);
        //VentanaPrincipal.consola += t.tp;
        return t.tp;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return 0;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
       return exp.GetTipo(ts);
    }
    
}
