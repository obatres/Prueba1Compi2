/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones.FuncionesDefinidas;

import arbol.DeclaracionVariable;
import arbol.Expresion;
import arbol.Operacion;
import arbol.Simbolo;
import arbol.Single;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class C  extends Expresion{
    private ArrayList<Object> ListaExp;

    ArrayList<Object> salidatotal = new ArrayList<>();
    int NumeroDeTipo;
    public C(ArrayList<Object> ListaExp) {
        this.ListaExp = ListaExp;
    }
    
    public void retorna (Object o){
        salidatotal.add(o);
    }
        
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        for (Object object : ListaExp) {
            if(object instanceof Single){
                DeclaracionVariable.Vector.add(object);
            }else if(object instanceof C){
                ((C) object).ejecutar(ts);
            }else if(object instanceof Operacion){
                DeclaracionVariable.Vector.add(new Single(((Operacion) object).ejecutar(ts),((Operacion) object).GetTipo(ts)) );
            }
        }
        
        return salidatotal;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        for (Object t : ListaExp) {
           if (((Expresion) t).GetTipo(ts).isInt()&&NumeroDeTipo<=0){
               NumeroDeTipo=1;
           }else if(((Expresion) t).GetTipo(ts).isDouble()&&NumeroDeTipo<=1){
               NumeroDeTipo=2;
           }else if(((Expresion) t).GetTipo(ts).isString()&&NumeroDeTipo<=2){
               NumeroDeTipo=3;
           }             
        }
       
        if(NumeroDeTipo==0){
            return new Tipo(Tipo.tipo.BOOLEAN);
        }else if(NumeroDeTipo==1){
            return new Tipo(Tipo.tipo.INT);
        }else if(NumeroDeTipo==2){
            return new Tipo(Tipo.tipo.DOUBLE);
        }else if(NumeroDeTipo==3){
            return new Tipo(Tipo.tipo.STRING);
        }else{
            return null;
        }
        
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return cont;
    }
    
}
