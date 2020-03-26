/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;
import arbol.Tipo.tipo.*;
import java.util.ArrayList;
/**
 *
 * @author obatres_
 */
public class Operacion extends Expresion{

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Expresion Binaria\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");
        
        if(opderadorIzq!=null){
            cont = opderadorIzq.Dibujar(builder, nodo, cont);            
        }


        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + tipo_operacion + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");

        if(operadorDer!=null){
            cont = operadorDer.Dibujar(builder, nodo, cont);           
        }


        return cont;    }

    public static enum Tipo_operacion{
        SUMA,
        NUMERO,
        RESTA,
        MULTIPLICACION,
        DIVISION,
        POTENCIA,
        MODULO,
        UMENOS,
        IGUALIGUAL,
        DESIGUAL,
        MAYOR,
        MENOR,
        MAYORIGUAL,
        MENORIGUAL,
        AND,
        OR,
        NOT,
        TERNARIO,
        IDENTIFICADOR
    }
   
    private final Tipo_operacion tipo_operacion;
    
    private Expresion opderadorIzq;
    private Expresion operadorDer;
    private Expresion operadorTer;
    public  Tipo tipo;
    private Object valor;

    public Operacion(Tipo_operacion tipo, Expresion opderadorIzq, Expresion operadorDer) {
        this.tipo_operacion = tipo;
        this.opderadorIzq = opderadorIzq;
        this.operadorDer = operadorDer;
    }
    
    public Operacion(Tipo_operacion tipo, Expresion opderadorIzq) {
        this.tipo_operacion = tipo;
        this.opderadorIzq = opderadorIzq;
    } 
    
    public Operacion (String a, Tipo_operacion tipo){
        this.valor = a;
        this.tipo_operacion=tipo;
    }
    public Operacion(Object a, Tipo tipo, Tipo_operacion to) {
        this.valor=a;
        this.tipo = tipo;
        this.tipo_operacion= to;
    }

    public Operacion(Tipo_operacion tipo,Expresion operadorTer , Expresion opderadorIzq,Expresion operadorDer ) {
        this.tipo_operacion = tipo;
        this.operadorTer = operadorTer;
        this.opderadorIzq=opderadorIzq;
        this.operadorDer = operadorDer;
    }
    
    
    

    
    @Override
    public Object ejecutar(TablaDeSimbolos ts){

/*------------------------------------------------------SUMA----------------------------------------------------------------------------*/    
        if(tipo_operacion==Tipo_operacion.SUMA){
            if (opderadorIzq.GetTipo(ts).isInt()){ // EJEMPLO DE OPERADOR IZQUIERDO  1,546,100
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){  
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1+ val2; 
                    //return Double.parseDouble(opderadorIzq.ejecutar(ts).toString())+Double.parseDouble(operadorDer.ejecutar(ts).toString());
               }else if(operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1+val2;                  
                   //return (opderadorIzq.ejecutar(ts).toString())+(operadorDer.ejecutar(ts).toString());
               }              
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1+ val2;
                }else if(operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1+val2; 
                }                  
            }else if(opderadorIzq.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1+val2; 
            }else if(opderadorIzq.GetTipo(ts).isBoolean()){
                if(operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1+val2;    
                }
            }else{
                //TODO reportar error de tipo
            } 
/*------------------------------------------------------RESTA----------------------------------------------------------------------------*/              
        }else if(tipo_operacion==Tipo_operacion.RESTA){
            if(opderadorIzq.GetTipo(ts).isInt()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return Double.parseDouble(opderadorIzq.ejecutar(ts).toString())-Double.parseDouble(operadorDer.ejecutar(ts).toString());
                }     
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return Double.parseDouble(opderadorIzq.ejecutar(ts).toString())-Double.parseDouble(operadorDer.ejecutar(ts).toString());
                }
            }else{
                //TODO reportar error de tipo
            }
/*------------------------------------------------------MULTIPLICACION----------------------------------------------------------------------------*/              
        }else if(tipo_operacion==Tipo_operacion.MULTIPLICACION){
            if(opderadorIzq.GetTipo(ts).isInt()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return Double.parseDouble(opderadorIzq.ejecutar(ts).toString())*Double.parseDouble(operadorDer.ejecutar(ts).toString());
                }     
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return Double.parseDouble(opderadorIzq.ejecutar(ts).toString())*Double.parseDouble(operadorDer.ejecutar(ts).toString());
                }
            }else{
                //TODO reportar error de tipo
            }  
/*------------------------------------------------------DIVISION----------------------------------------------------------------------------*/              
        }else if(tipo_operacion==Tipo_operacion.DIVISION){
            if(opderadorIzq.GetTipo(ts).isInt()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return Double.parseDouble(opderadorIzq.ejecutar(ts).toString())/Double.parseDouble(operadorDer.ejecutar(ts).toString());
                }     
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return Double.parseDouble(opderadorIzq.ejecutar(ts).toString())/Double.parseDouble(operadorDer.ejecutar(ts).toString());
                }
            }else{
                //TODO reportar error de tipo
            } 
/*------------------------------------------------------POTENCIA----------------------------------------------------------------------------*/              
        }else if(tipo_operacion==Tipo_operacion.POTENCIA){
            if(opderadorIzq.GetTipo(ts).isInt()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return Math.pow(val1, val2);                       
                }     
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return Math.pow(val1, val2);   
                }
            }else{
                //TODO reportar error de tipo
            }
/*------------------------------------------------------MODULO----------------------------------------------------------------------------*/              
        }else if(tipo_operacion==Tipo_operacion.MODULO){
            if(opderadorIzq.GetTipo(ts).isInt()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1%val2;                    
                }     
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1%val2;  
                }
            }else{
                //TODO reportar error de tipo
            } 
/*------------------------------------------------------UNARIO MENOS----------------------------------------------------------------------------*/              
        }else if(tipo_operacion==Tipo_operacion.UMENOS){
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                    Double val1;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double)((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }                
                return -val1;
            }else{
                //TODO reportar error de tipo
            }
/*------------------------------------------------------IGUAL IGUAL----------------------------------------------------------------------------*/              
        }else if(tipo_operacion==Tipo_operacion.IGUALIGUAL){
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1.equals(val2);
                }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return (val1.toString().equals( val2.toString()));   
            }
/*------------------------------------------------------NO IGUAL----------------------------------------------------------------------------*/                 
        }else if(tipo_operacion==Tipo_operacion.DESIGUAL){
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return !val1.equals(val2);
                }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return !(val1.toString().equals( val2.toString()));                
            }            
/*------------------------------------------------------MAYOR QUE----------------------------------------------------------------------------*/  
        }else if(tipo_operacion==Tipo_operacion.MAYOR){
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1>val2;
                }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1.length()>val2.length();
            }      
/*------------------------------------------------------MENOR QUE----------------------------------------------------------------------------*/ 
        }else if(tipo_operacion==Tipo_operacion.MENOR){
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){ 
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1<val2;
                }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1.length()<val2.length();               
            }   
/*------------------------------------------------------MAYOR IGUAL QUE----------------------------------------------------------------------------*/  
        }else if(tipo_operacion==Tipo_operacion.MAYORIGUAL){
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1>=val2;
                }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1.length()>=val2.length();   
            }   
/*------------------------------------------------------MENOR IGUAL QUE----------------------------------------------------------------------------*/ 
        }else if(tipo_operacion==Tipo_operacion.MENORIGUAL){
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1<=val2;
                }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1.length()<=val2.length();   
            }  
/*-------------------------------------------------------------AND----------------------------------------------------------------------------*/             
        }else if(tipo_operacion==Tipo_operacion.AND){
            try {
                return opderadorIzq.ejecutar(ts).equals(operadorDer.ejecutar(ts));               
            } catch (Exception e) {
                //TODO Reportar error de tipos
            }
/*-------------------------------------------------------------OR----------------------------------------------------------------------------*/             
        }else if(tipo_operacion==Tipo_operacion.OR){
            try {
                return (boolean)opderadorIzq.ejecutar(ts)||(boolean)operadorDer.ejecutar(ts);
            } catch (Exception e) {
                //TODO Reportar error de tipos
            }
/*-------------------------------------------------------------NOT----------------------------------------------------------------------------*/               
        }else if(tipo_operacion == Tipo_operacion.NOT){
            try {
                return !(boolean) opderadorIzq.ejecutar(ts);
            } catch (Exception e) {
            }
 /*-------------------------------------------------------------TERNARIO----------------------------------------------------------------------------*/               
        }else if(tipo_operacion==Tipo_operacion.TERNARIO){
            if(operadorTer.ejecutar(ts).equals(true)){
                if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                    return Double.parseDouble(opderadorIzq.ejecutar(ts).toString());
                }else if(opderadorIzq.GetTipo(ts).isString()){
                    return (opderadorIzq.ejecutar(ts).toString());
                }
            }else {
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return Double.parseDouble(operadorDer.ejecutar(ts).toString());
                }else if(operadorDer.GetTipo(ts).isString()){
                    return (operadorDer.ejecutar(ts).toString());
                }               
            }
            
/*------------------------------------------------------IDENTIFICADOR / VARIABLE----------------------------------------------------------------------------*/           
        }else if(tipo_operacion==Tipo_operacion.IDENTIFICADOR){
            return ts.getValor(valor.toString());
/*------------------------------------------------------VALOR DE EXPRESION----------------------------------------------------------------------------*/            
        }else if(tipo_operacion==Tipo_operacion.NUMERO){
              return this.valor;  
        }
        return null;
    }
    
    
        @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        if (opderadorIzq.GetTipo(ts).isInt()){
            if(operadorDer!=null){
                if(operadorDer.GetTipo(ts).isInt()){
                    return new Tipo(Tipo.tipo.INT);  
                }else if(operadorDer.GetTipo(ts).isDouble()){
                    return new Tipo(Tipo.tipo.DOUBLE);
                }else if(operadorDer.GetTipo(ts).isString()){
                    return new Tipo(Tipo.tipo.STRING);
                }else{
                    //TODO reportar error de tipo
                }                
            }else{
                return new Tipo(Tipo.tipo.INT); 
            }
        }else if(opderadorIzq.GetTipo(ts).isDouble()){
            if(operadorDer!=null){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return new Tipo(Tipo.tipo.DOUBLE);
                }else if(operadorDer.GetTipo(ts).isString()){
                    return new Tipo(Tipo.tipo.STRING);
                }else{

                }                
            }else{
                return new Tipo(Tipo.tipo.DOUBLE); 
            }

        }else if(opderadorIzq.GetTipo(ts).isString()){
            return new Tipo(Tipo.tipo.STRING);
        }else if(opderadorIzq.GetTipo(ts).isBoolean()){
            if(operadorDer.GetTipo(ts).isString()){
                return new Tipo(Tipo.tipo.STRING);
            }
        }else if(opderadorIzq.GetTipo(ts).isBoolean()){
            if(operadorDer.GetTipo(ts).isBoolean()){
                return new Tipo(Tipo.tipo.BOOLEAN);
            }else if(operadorDer.GetTipo(ts).isString()){
                return new Tipo(Tipo.tipo.STRING);
            }
        }else{
            return this.tipo;
        }
        return this.tipo;
    }
        
}
