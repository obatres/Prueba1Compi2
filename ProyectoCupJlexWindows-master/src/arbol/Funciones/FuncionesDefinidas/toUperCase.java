/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones.FuncionesDefinidas;

import arbol.Expresion;
import arbol.Single;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class toUperCase extends Expresion{
    
    private Expresion exp;
    String cadena;

    public toUperCase(Expresion exp) {
        this.exp = exp;
    }
    
    

    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if(exp.GetTipo(ts).isString()){
            if(exp.ejecutar(ts) instanceof ArrayList){
                cadena=((Expresion)((ArrayList)exp.ejecutar(ts)).get(0)).ejecutar(ts).toString();
            }else if(exp.ejecutar(ts) instanceof Single){
                cadena=((Expresion)exp.ejecutar(ts)).ejecutar(ts).toString();
            }
            System.out.println(cadena.toUpperCase());
            return cadena.toUpperCase();
        }else{
            System.out.println("tipo de parametro invalido");
        }
        return null;    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        return exp.GetTipo(ts);
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return cont;
    }
}
