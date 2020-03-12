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
public class id extends Expresion{
    
    ArrayList<Object> Vector = new ArrayList<Object>();
    /**
     * @return the iden
     */
    public String getIden() {
        return iden;
    }

    /**
     * @param iden the iden to set
     */
    public void setIden(String iden) {
        this.iden = iden;
    }

    
    private String iden;

    public id(String iden) {
        this.iden = iden;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        
        //preguntar si existe el simbolo 
        // bool existencia=ts.Exist(iden);
        //if (existe) return ts.getValor(iden);
        //Simbolo s = (Simbolo) ts.getValor(iden);
        if (ts.getValor(iden) instanceof ArrayList){
            Vector = (ArrayList<Object>) ts.getValor(iden);
            if (Vector.size()>=2){
                return Vector;   
            }else if(Vector.size()==1){
                return Vector.get(0);
            }
        }
        //return ts.getValor(iden);
        return null;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        return (Tipo) ts.getTipo(iden);
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"id\"]; \n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp1 = "nodo" + ++cont;
        builder.append(nodoOp1).append(" [label=\""+ iden + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp1).append(";\n");
        return cont;
    }
    
}
