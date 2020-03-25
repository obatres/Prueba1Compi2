/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import arbol.Funciones.FuncionesDefinidas.C;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class DeclaracionVariable extends Instruccion {
    private Expresion exp;
    private String identificador;
    
    public static ArrayList<Object> Vector = new ArrayList<>();
    ArrayList<Object> Valor = new ArrayList<>();
    public DeclaracionVariable(Expresion exp, String identificador) {
        this.exp = exp;
        this.identificador = identificador;
    }
    
    
            
           
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        exp.ejecutar(ts);
        if(exp instanceof C){
            if(exp.GetTipo(ts).isBoolean()){
                for (Object o : Vector) {
                    Valor.add(((Expresion)o).ejecutar(ts));
                }
            }else if(exp.GetTipo(ts).isInt()){
                for (Object o : Vector) {
                    if(((Expresion)o).GetTipo(ts).isBoolean()){
                        if(((Expresion)o).ejecutar(ts).equals("true")){
                            Valor.add(1);
                        }else if(((Expresion)o).ejecutar(ts).equals("false")){
                            Valor.add(0);
                        }
                    }else{
                        Valor.add(((Expresion)o).ejecutar(ts));
                    }
                }
            }else if(exp.GetTipo(ts).isDouble()){
                for (Object o : Vector) {
                    if(((Expresion)o).GetTipo(ts).isBoolean()){
                        if(((Expresion)o).ejecutar(ts).equals("true")){
                            Valor.add(1.0);
                        }else if(((Expresion)o).ejecutar(ts).equals("false")){
                            Valor.add(0.0);
                        }
                    }else if (((Expresion)o).GetTipo(ts).isInt()){
                        Valor.add(Double.parseDouble(((Expresion)o).ejecutar(ts).toString()));
                    }else{
                        Valor.add(((Expresion)o).ejecutar(ts));
                    }
                }                    
            }else if(exp.GetTipo(ts).isString()){
                for (Object o : Vector) {
                    if(((Expresion)o).GetTipo(ts).isBoolean()){
                        if(((Expresion)o).ejecutar(ts).equals("true")){
                            Valor.add("true");
                        }else if(((Expresion)o).ejecutar(ts).equals("false")){
                            Valor.add("false");
                        }
                    }else if(((Expresion)o).GetTipo(ts).isInt()){
                        Valor.add(((Expresion)o).ejecutar(ts).toString());
                    }else if(((Expresion)o).GetTipo(ts).isDouble()){
                        Valor.add(((Expresion)o).ejecutar(ts).toString());
                    }else if(((Expresion)o).GetTipo(ts).isString()){
                        Valor.add(((Expresion)o).ejecutar(ts));
                    }
                }                     
            }
        }else if(exp instanceof Single){
            Valor.add(0, exp.ejecutar(ts));
        }
        if(ts.Existe(identificador)){
            ts.setValor(identificador, Valor, exp.GetTipo(ts));            
        }else{
            ts.add(new Simbolo(identificador,exp.GetTipo(ts)));
            ts.setValor(identificador, Valor, exp.GetTipo(ts));
        }

        
        Vector.clear();
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return cont;
    }
    
}
