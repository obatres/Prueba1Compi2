/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

/**
 *
 * @author obatres_
 */
public class id extends Expresion{

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
        return ts.getValor(iden);
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
