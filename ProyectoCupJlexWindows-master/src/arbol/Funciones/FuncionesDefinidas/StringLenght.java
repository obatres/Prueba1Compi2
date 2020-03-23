/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones.FuncionesDefinidas;

import InterfazGrafica.VentanaPrincipal;
import arbol.Expresion;
import arbol.Simbolo;
import arbol.Single;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class StringLenght extends Expresion{
    private Expresion exp;
    String cadena="";

    public StringLenght(Expresion exp) {
        this.exp = exp;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if (exp.GetTipo(ts).isString()){
            Object j;
            
            if (exp.ejecutar(ts) instanceof ArrayList){
                for (Object t : (ArrayList)exp.ejecutar(ts)) {
                    cadena+=((Expresion)t).ejecutar(ts);
                }
            }else if (exp.ejecutar(ts) instanceof Single){
                j=exp.ejecutar(ts);
                cadena+=((Expresion)j).ejecutar(ts);
            }else if (exp.ejecutar(ts) instanceof String){
                cadena+=exp.ejecutar(ts);
            }else{
                System.out.println("Parametro incorrecto");
            }
            System.out.println(cadena.length());
            //VentanaPrincipal.consola += cadena.length();
            return cadena.length();
        }else{
            System.out.println("Parametro incorrecto");
        }
        
        return null;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        if (exp.GetTipo(ts).isString()){
            return exp.GetTipo(ts);
        }else{
            return null;
        }   
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return cont;
    }
    
}
