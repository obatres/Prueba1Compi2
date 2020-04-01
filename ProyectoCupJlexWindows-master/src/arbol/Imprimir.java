/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;
 import InterfazGrafica.VentanaPrincipal;
import java.util.ArrayList;
/**
 *
 * @author obatres_
 */
public class Imprimir extends Instruccion{

    private final Expresion contenido;

    ArrayList<Object> Salida = new ArrayList<Object>();
    
    String sal="";
    public Imprimir(Expresion contenido) {
        this.contenido = contenido;
    }
    /**
     *
     * @param ts
     * @return
     */
    @Override
    public Object ejecutar( TablaDeSimbolos ts ){
        Object o = contenido.ejecutar(ts);
        if(contenido.GetTipo(ts).isList()){
            try {
                for (Object t : (ArrayList)(((ArrayList)o).get(((ArrayList)o).size()-1))) {
                    System.out.println(t);
                    sal+=t.toString()+"\n";
                }
            } catch (Exception e) {
                System.out.println(o);
                sal+=o+"\n";
            }
            
            VentanaPrincipal.consola +=sal; 
        }else{
            // <editor-fold desc="NO ES LISTA">> 
            if (o instanceof ArrayList){
            Salida  = (ArrayList<Object>) o;
            System.out.print("[");
            sal  += "[";    
            for (Object t : Salida) {
                if (t instanceof Expresion){
                    System.out.println(((Expresion)t).ejecutar(ts));
                    sal  += ((Expresion)t).ejecutar(ts).toString()+"\n";                    
                }else{
                    
                    System.out.print(t);
                    System.out.print(",");
                    sal  += t.toString()+",";                      
                }
            }
            System.out.print("]\n");
            sal  += "]";    
            sal  += "\n"; 
            VentanaPrincipal.consola +=sal; 
                    
            }else if(o!=null){
                //System.out.println("no se puede imprimir el valor, es desconocido");
                System.out.println(o.toString());
                VentanaPrincipal.consola += o.toString()+"\n";
            }else{
                System.out.println("nulo");
            }     
            // </editor-fold>
        }


        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Imprimir\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");
        //System.out.println(contenido.getClass()+"CLASE");
        cont = ((id)contenido).Dibujar(builder, nodo, cont);
        return cont;
    }
}
