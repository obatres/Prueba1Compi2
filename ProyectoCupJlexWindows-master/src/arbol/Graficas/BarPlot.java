/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Graficas;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.Simbolo;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author obatres_
 */
public class BarPlot extends Instruccion{

    private ArrayList<Object> ParametrosBarPlot;

    ArrayList<Object> Numericos = new ArrayList<>();
    ArrayList<Object> Labels    = new ArrayList<>();
    String main;
    String xlab;
    String ylab;
    public BarPlot(ArrayList<Object> ParametrosBarPlot) {
        this.ParametrosBarPlot = ParametrosBarPlot;
    }  
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        //System.out.println(ParametrosBarPlot);
        if (ParametrosBarPlot.size()==5){
            //System.out.println("Valores correctos");
            //System.out.println(((Expresion)ParametrosBarPlot.get(0)).ejecutar(ts));
            if(((Expresion)ParametrosBarPlot.get(0)).ejecutar(ts) instanceof ArrayList){
                if(((Expresion)ParametrosBarPlot.get(0)).GetTipo(ts).tp.equals(Tipo.tipo.DOUBLE)||((Expresion)ParametrosBarPlot.get(0)).GetTipo(ts).tp.equals(Tipo.tipo.INT))
                    //System.out.println("listado de numeros");
                    Numericos = (ArrayList<Object>) ((Expresion)ParametrosBarPlot.get(0)).ejecutar(ts);
            }  else{
                //Parametro incorrecto, se esperaba un listado de numeros
            }
            if(((Expresion)ParametrosBarPlot.get(1)).ejecutar(ts) instanceof String){
                xlab = ((Expresion)ParametrosBarPlot.get(1)).ejecutar(ts).toString();
                //System.out.println("xlab correcto");
            }else{
                //SE ESPERABA UN LABEL PARA EL EJE X
            }
            if(((Expresion)ParametrosBarPlot.get(2)).ejecutar(ts) instanceof String){
                //System.out.println("ylab correcto");
                ylab=((Expresion)ParametrosBarPlot.get(2)).ejecutar(ts).toString();
            }else{
                //SE ESPERABA UN LABEL PARA EL EJE Y
            }
            if(((Expresion)ParametrosBarPlot.get(3)).ejecutar(ts) instanceof String){
                main = ((Expresion)ParametrosBarPlot.get(3)).ejecutar(ts).toString();
            }else{
                //SE ESPERABA EL NOMBRE DE LA GRAFICA
            }
            if(((Expresion)ParametrosBarPlot.get(4)).ejecutar(ts) instanceof ArrayList){
                if(((Expresion)ParametrosBarPlot.get(4)).GetTipo(ts).tp.equals(Tipo.tipo.STRING)){
                    //System.out.println("Lista de labels");
                    Labels = (ArrayList<Object>) ((Expresion)ParametrosBarPlot.get(4)).ejecutar(ts);
                    //sSystem.out.println(Labels);
                }
            }
        }else{
            //NUMERO DE PARAMETROS INCORRECTOS PARA HACER LA GRAFICA
        }
         
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if(Numericos.size()==Labels.size()){
            for (int i = 0; i < Numericos.size(); i++) {
                dataset.setValue(Double.parseDouble(((Expresion)Numericos.get(i)).ejecutar(ts).toString()), main,((Expresion)Labels.get(i)).ejecutar(ts).toString());
            }              
        }else if (Numericos.size()>Labels.size()){
            //REPORTAR ERROR DE TAMAÑO DE VECTORES
            for (int i = 0; i < Labels.size(); i++) {
                dataset.setValue(Double.parseDouble(((Expresion)Numericos.get(i)).ejecutar(ts).toString()), main,((Expresion)Labels.get(i)).ejecutar(ts).toString());
            }
            for (int i = Labels.size(); i < Numericos.size(); i++) {
                dataset.setValue(Double.parseDouble(((Expresion)Numericos.get(i)).ejecutar(ts).toString()), main,"Desconocido"+i);
            }
        }
  
        JFreeChart chart =  ChartFactory.createBarChart3D(main,xlab, ylab, dataset, PlotOrientation.VERTICAL,true,true, false);
        ChartPanel panel = new ChartPanel(chart);
        JFrame ventana = new JFrame("grafica");
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
    return 0;
        }
    
}
