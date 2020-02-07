/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;
import arbol.Tipo.tipo;
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
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts){

/*------------------------------------------------------SUMA----------------------------------------------------------------------------*/    
        if(tipo_operacion==Tipo_operacion.SUMA){
            /*--------------------------SUMA DE ENTEROS Y DECIMALES--------------------------------------------------------------------*/
            
           if (opderadorIzq.GetTipo(ts).isInt()){ // EJEMPLO DE OPERADOR IZQUIERDO  1,546,100
               if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){ //EJEMPLO DE OPERADOR DERECHO 1.1321,0,544564
                    return Double.parseDouble((String) opderadorIzq.ejecutar(ts))+Double.parseDouble((String) operadorDer.ejecutar(ts));                  
               }
            /*--------------------------SUMA DE CADENAS-------------------------------------------------------------------------------*/  
            
            }else if(operadorDer.GetTipo(ts).isString()||opderadorIzq.GetTipo(ts).isString()){ //EJEMPLO "ASD"+54 o 65465+"ASa"
                return (String)opderadorIzq.ejecutar(ts)+(String)operadorDer.ejecutar(ts);
            /*--------------------------SUMA DE ENTEROS Y DECIMALES--------------------------------------------------------------------*/
            
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){                
                    return Double.parseDouble((String) opderadorIzq.ejecutar(ts))+Double.parseDouble((String) operadorDer.ejecutar(ts));                    
                }              
            }else{
                return null;//VALOR NO OPERABLE
            }
/*------------------------------------------------------RESTA----------------------------------------------------------------------------*/           
        }else if(tipo_operacion==Tipo_operacion.RESTA){
            /*--------------------------RESTA DE ENTEROS Y DECIMALES--------------------------------------------------------------------*/
            
           if (opderadorIzq.GetTipo(ts).isInt()){ // EJEMPLO DE OPERADOR IZQUIERDO  1,546,100
               if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){ //EJEMPLO DE OPERADOR DERECHO 1.1321,0,544564
                    return Double.parseDouble((String) opderadorIzq.ejecutar(ts))-Double.parseDouble((String) operadorDer.ejecutar(ts));                  
               }
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
               if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){ //EJEMPLO DE OPERADOR DERECHO 1.1321,0,544564
                    return Double.parseDouble((String) opderadorIzq.ejecutar(ts))-Double.parseDouble((String) operadorDer.ejecutar(ts));                  
               }               
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.tipo;
    }
        
}
