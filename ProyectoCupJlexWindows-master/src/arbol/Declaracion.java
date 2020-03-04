/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class Declaracion extends Instruccion{

    private final String id;

    Tipo tipo; //Tipo del simbolo (primitivos)
    
    private Expresion exp;
    
    ArrayList<Object> Vector = new ArrayList<Object>();
    
    
    private int tipoDeclaracion ; //Tipo de Declaracion, vector, arreglo, lista y matriz
    
    
    public Declaracion(String id, Expresion exp, int tipoDeclaracion) {
        this.id = id;
        this.exp = exp;
        this.tipoDeclaracion = tipoDeclaracion;
    }
    
    public Declaracion(String a, Tipo t) {
        id = a;
        tipo = t;
    }
       
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
       if(tipoDeclaracion==1){
           Vector.add(exp.ejecutar(ts));
        if(ts.Existe(id)){
            ts.setValor(id, Vector, exp.GetTipo(ts));           
        }else{
            ts.add(new Simbolo(id, exp.GetTipo(ts)));
            
            ts.setValor(id, Vector,exp.GetTipo(ts));
            }
        }
       return null;
    }
    
}
