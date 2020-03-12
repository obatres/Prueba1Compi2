/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import arbol.Variables.NodoVector;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class Declaracion extends Instruccion{

    
    int NumeroDeTipo=0;
    private final String id;

    Tipo tipo; //Tipo del simbolo (primitivos)
    
    private Expresion exp;
    
    ArrayList<Object> Vector ;//= new ArrayList<Object>(); //ArrayList que almacena los valores de una variable de tipo Vector
    
    ArrayList<Object> ValoresVariableVector; // variable para recibir los valores de una variable del lenguaje ARIT
    
    int TipoDeVariable;  //Tipo de variable para distinguir entre Vector, arreglo, lista y matriz
    
    
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



    public Declaracion(String id, ArrayList<Object> ValoresVariableVector,  int tipoDeclaracion, int TipoDeVariable) {
        this.id = id;
        this.ValoresVariableVector = ValoresVariableVector;
        this.TipoDeVariable = TipoDeVariable;
        this.tipoDeclaracion = tipoDeclaracion;
    }
    
    /*
    Tipos de declaracion                                                Tipos de variables
    
     1      |  Identificador = Expresion                            
     2      |  Identificador = C( lista de expresiones);                1       |   Vector
    
    */
       
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
       if(tipoDeclaracion==1){
           Vector = new ArrayList<>();
           Vector.add(0, exp.ejecutar(ts));
        if(ts.Existe(id)){
            ts.setValor(id, Vector, exp.GetTipo(ts));           
        }else{
            ts.add(new Simbolo(id, exp.GetTipo(ts)));            
            ts.setValor(id, Vector,exp.GetTipo(ts));
            }
        }else if(tipoDeclaracion==2) {
           if(ts.Existe(id)){
               for (Object t : ValoresVariableVector) {
                   if (t instanceof Expresion){
                       
                       if (((Expresion) t).GetTipo(ts).equals(Tipo.tipo.INT)&&NumeroDeTipo<=0){
                           NumeroDeTipo=1;
                       }else if(((Expresion) t).GetTipo(ts).equals(Tipo.tipo.DOUBLE)&&NumeroDeTipo<=1){
                           NumeroDeTipo=2;
                       }else if(((Expresion) t).GetTipo(ts).equals(Tipo.tipo.STRING)&&NumeroDeTipo<=2){
                           NumeroDeTipo=3;
                       }
                   }
               }
               if(NumeroDeTipo==0){
                   this.tipo = new Tipo(Tipo.tipo.BOOLEAN);
               }else if(NumeroDeTipo==1){
                   this.tipo = new Tipo(Tipo.tipo.INT);
               }else if(NumeroDeTipo==2){
                   this.tipo = new Tipo(Tipo.tipo.DOUBLE);
               }else if(NumeroDeTipo==3){
                   this.tipo = new Tipo(Tipo.tipo.STRING);
               }
               ts.setValor(id, ValoresVariableVector, tipo);
           }else{
               for (Object t : ValoresVariableVector) {
                   if (t instanceof Expresion){
                       
                       if (((Expresion) t).GetTipo(ts).equals(Tipo.tipo.INT)&&NumeroDeTipo<=0){
                           NumeroDeTipo=1;
                       }else if(((Expresion) t).GetTipo(ts).equals(Tipo.tipo.DOUBLE)&&NumeroDeTipo<=1){
                           NumeroDeTipo=2;
                       }else if(((Expresion) t).GetTipo(ts).equals(Tipo.tipo.STRING)&&NumeroDeTipo<=2){
                           NumeroDeTipo=3;
                       }
                   }
               }
               if(NumeroDeTipo==0){
                   this.tipo = new Tipo(Tipo.tipo.BOOLEAN);
               }else if(NumeroDeTipo==1){
                   this.tipo = new Tipo(Tipo.tipo.INT);
               }else if(NumeroDeTipo==2){
                   this.tipo = new Tipo(Tipo.tipo.DOUBLE);
               }else if(NumeroDeTipo==3){
                   this.tipo = new Tipo(Tipo.tipo.STRING);
               }
               ts.add(new Simbolo(id, this.tipo));
               //System.out.println(ValoresVariableVector);
               ts.setValor(id, ValoresVariableVector, tipo);
//               for (Object t : ValoresVariableVector) {
//                   System.out.println(((Expresion)t).ejecutar(ts));
//               }
           }
        }
       return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        if(tipoDeclaracion==1){
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Asignacion\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + id + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");
        

        String nodoVal = "nodo"+ ++cont;
        builder.append(nodoVal).append(" [label=\"Valor\"];\n");
        builder.append(nodo).append(" -> ").append(nodoVal).append(";\n");           
        
        cont = exp.Dibujar(builder, nodoVal, cont);            
        }


        return cont;        
    }




}
