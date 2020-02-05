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
        DIVISION
    }
   
    private final Tipo_operacion tipo_operacion;
    
    private Operacion opderadorIzq;
    private Operacion operadorDer;
    public  Tipo tipo;
    private Object valor;

    public Operacion(Tipo_operacion tipo, Operacion opderadorIzq, Operacion operadorDer) {
        this.tipo_operacion = tipo;
        this.opderadorIzq = opderadorIzq;
        this.operadorDer = operadorDer;
    }
    
    public Operacion(Tipo_operacion tipo, Operacion opderadorIzq) {
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
        if(tipo_operacion==Tipo_operacion.SUMA){
           if (operadorDer.GetTipo(ts).isInt()){
                System.out.println(opderadorIzq.valor);
                System.out.println(operadorDer.valor);
                return (Double)opderadorIzq.ejecutar(ts)+(Double)operadorDer.ejecutar(ts);
            }else if(operadorDer.GetTipo(ts).isString()){
                return (String)opderadorIzq.ejecutar(ts)+(String)operadorDer.ejecutar(ts);
            }else{
                return null;
            }
        }else if (tipo_operacion==Tipo_operacion.NUMERO){
            return this.valor;        
        }else if(tipo_operacion==Tipo_operacion.RESTA){
            if(operadorDer.GetTipo(ts).isInt()){
                return (Double)opderadorIzq.ejecutar(ts)-(Double)operadorDer.ejecutar(ts);
            }else if(operadorDer.GetTipo(ts).isString()){
                //return (String)opderadorIzq.ejecutar(ts)-(String)operadorDer.ejecutar(ts);
            }
        }
        return null;
    }
    
        @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.tipo;
    }
        
}
