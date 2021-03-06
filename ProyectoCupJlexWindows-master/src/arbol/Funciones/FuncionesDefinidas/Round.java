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
public class Round extends Expresion{
    private Expresion exp;
    Double numero;
    int sal;
    public Round(Expresion exp) {
        this.exp = exp;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if(exp.GetTipo(ts).isDouble()){
            System.out.println(exp.ejecutar(ts).getClass());
            if(exp.ejecutar(ts) instanceof ArrayList){
                if(((ArrayList)exp.ejecutar(ts)).get(0) instanceof String){
                    numero = Double.parseDouble(((ArrayList)exp.ejecutar(ts)).get(0).toString());
                }else{
                    numero=Double.parseDouble((((Expresion)((ArrayList)exp.ejecutar(ts)).get(0)).ejecutar(ts)).toString());   
                }
            }else if(exp.ejecutar(ts) instanceof Single){
                numero=Double.parseDouble(((Expresion)exp.ejecutar(ts)).ejecutar(ts).toString());
            }else if(exp.ejecutar(ts) instanceof String){
                numero=Double.parseDouble(exp.ejecutar(ts).toString());
            }else{
                numero=0.0;
                //ERROR
            }
            System.out.println(Math.round(numero));
            sal = (int) Math.round(numero);
            return sal;
        }else{
            System.out.println("tipo de parametro invalido");
        }
        return null; 
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
