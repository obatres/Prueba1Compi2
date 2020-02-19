/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;
import arbol.Tipo.tipo.*;
/**
 *
 * @author obatres_
 */
public class Operacion extends Expresion{

    public static enum Tipo_operacion{
        SUMA,
        NUMERO,
        RESTA,
        MULTIPLICACION,
        DIVISION,
        POTENCIA,
        IDENTIFICADOR
    }
   
    private final Tipo_operacion tipo_operacion;
    
    private Expresion opderadorIzq;
    private Expresion operadorDer;
    public  Tipo tipo;
    private Object valor;

    public Operacion(Tipo_operacion tipo, Expresion opderadorIzq, Expresion operadorDer) {
        this.tipo_operacion = tipo;
        this.opderadorIzq = opderadorIzq;
        this.operadorDer = operadorDer;
    }
    
    /*public Operacion(Tipo_operacion tipo, Expresion opderadorIzq) {
        this.tipo_operacion = tipo;
        this.opderadorIzq = opderadorIzq;
    }*/   
    
    public Operacion (String a, Tipo_operacion tipo){
        this.valor = a;
        this.tipo_operacion=tipo;
    }
    public Operacion(Object a, Tipo tipo, Tipo_operacion to) {
        this.valor=a;
        this.tipo = tipo;
        this.tipo_operacion= to;
    }
    

    
    @Override
    public Object ejecutar(TablaDeSimbolos ts){

/*------------------------------------------------------SUMA----------------------------------------------------------------------------*/    
        if(tipo_operacion==Tipo_operacion.SUMA){
            /*--------------------------SUMA DE ENTEROS Y DECIMALES--------------------------------------------------------------------*/
            if (opderadorIzq.GetTipo(ts).isInt()){ // EJEMPLO DE OPERADOR IZQUIERDO  1,546,100
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){               
                   return Double.parseDouble(opderadorIzq.ejecutar(ts).toString())+Double.parseDouble(operadorDer.ejecutar(ts).toString());
               }else if(operadorDer.GetTipo(ts).isString()){
                   return (opderadorIzq.ejecutar(ts).toString())+(operadorDer.ejecutar(ts).toString());
               }              
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return Double.parseDouble(opderadorIzq.ejecutar(ts).toString())+Double.parseDouble(operadorDer.ejecutar(ts).toString());
                }else if(operadorDer.GetTipo(ts).isString()){
                    return (opderadorIzq.ejecutar(ts).toString())+(operadorDer.ejecutar(ts).toString());
                }                  
            }else if(opderadorIzq.GetTipo(ts).isString()){
                    return (opderadorIzq.ejecutar(ts).toString())+(operadorDer.ejecutar(ts).toString());
            }else if(opderadorIzq.GetTipo(ts).isBoolean()){
                if(operadorDer.GetTipo(ts).isString()){
                    return (opderadorIzq.ejecutar(ts).toString())+(operadorDer.ejecutar(ts).toString());    
                }
            }else{
                //TODO reportar error de tipo
            }                      
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
        }else if(tipo_operacion==Tipo_operacion.POTENCIA){
            if(opderadorIzq.GetTipo(ts).isInt()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return Math.pow(Double.parseDouble(opderadorIzq.ejecutar(ts).toString()),Double.parseDouble(operadorDer.ejecutar(ts).toString()));
                    //return Double.parseDouble(opderadorIzq.ejecutar(ts).toString())/Double.parseDouble(operadorDer.ejecutar(ts).toString());
                }     
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return Math.pow(Double.parseDouble(opderadorIzq.ejecutar(ts).toString()),Double.parseDouble(operadorDer.ejecutar(ts).toString()));
                }
            }else{
                //TODO reportar error de tipo
            }              
        }else if(tipo_operacion==Tipo_operacion.IDENTIFICADOR){
            return ts.getValor(valor.toString());
        }else if(tipo_operacion==Tipo_operacion.NUMERO){
              return this.valor;  
        }
        return null;
    }
    
    
        @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        if (opderadorIzq.GetTipo(ts).isInt()){
            if(operadorDer.GetTipo(ts).isInt()){
                return new Tipo(Tipo.tipo.INT);  
            }else if(operadorDer.GetTipo(ts).isDouble()){
                return new Tipo(Tipo.tipo.DOUBLE);
            }else if(operadorDer.GetTipo(ts).isString()){
                return new Tipo(Tipo.tipo.STRING);
            }else{
                //TODO reportar error de tipo
            }
        }else if(opderadorIzq.GetTipo(ts).isDouble()){
            if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                return new Tipo(Tipo.tipo.DOUBLE);
            }else if(operadorDer.GetTipo(ts).isString()){
                return new Tipo(Tipo.tipo.STRING);
            }
        }else if(opderadorIzq.GetTipo(ts).isString()){
            return new Tipo(Tipo.tipo.STRING);
        }else if(opderadorIzq.GetTipo(ts).isBoolean()){
            if(operadorDer.GetTipo(ts).isString()){
                return new Tipo(Tipo.tipo.STRING);
            }
        }else{
            return this.tipo;
        }
        return this.tipo;
    }
        
}
