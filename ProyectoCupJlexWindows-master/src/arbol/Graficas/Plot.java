/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Graficas;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author obatres_
 */
public class Plot extends Instruccion{

    private ArrayList<Object> ParametrosBarPlot;

    // <editor-fold desc="PARAMETROS DE GRAFICA DE LINEAS">> 
    ArrayList<Object> VLinea = new ArrayList<>();
    String main;
    String xlab;
    String ylab;
    String Type;
    // </editor-fold>
    public Plot(ArrayList<Object> ParametrosBarPlot) {
        this.ParametrosBarPlot = ParametrosBarPlot;
    }



    @Override
    public Object ejecutar(TablaDeSimbolos ts) {

        if(ParametrosBarPlot.size()==5){
            
            if(((Expresion)ParametrosBarPlot.get(0)).GetTipo(ts).tp.equals(Tipo.tipo.LISTA)){
                //System.out.println(((Expresion)ParametrosBarPlot.get(0)).ejecutar(ts));
                VLinea =(ArrayList)((Expresion)ParametrosBarPlot.get(0)).ejecutar(ts);
            }else{
                System.out.println("se esperaba un conjunto de numeros");
            }
            
            if(((Expresion)ParametrosBarPlot.get(1)).ejecutar(ts)instanceof String){
                Type = ((Expresion)ParametrosBarPlot.get(1)).ejecutar(ts).toString();
            }else{
                System.out.println("Se esperaba un String para el tipo, se usara por default O");
                Type ="O";
            }
            
            if(((Expresion)ParametrosBarPlot.get(2)).ejecutar(ts)instanceof String){
                xlab = ((Expresion)ParametrosBarPlot.get(2)).ejecutar(ts).toString();
            }else{
                System.out.println("Se esperaba un String para el xlab");

            }            
            
            if(((Expresion)ParametrosBarPlot.get(3)).ejecutar(ts)instanceof String){
                ylab = ((Expresion)ParametrosBarPlot.get(3)).ejecutar(ts).toString();
            }else{
                System.out.println("Se esperaba un String para el ylab");
            }       
 
            if(((Expresion)ParametrosBarPlot.get(4)).ejecutar(ts)instanceof String){
                main = ((Expresion)ParametrosBarPlot.get(4)).ejecutar(ts).toString();
            }else{
                System.out.println("Se esperaba un String para el main");

            }  
        }else{
            System.out.println("CANTIDAD INCORRECTA DE PARAMETROS");
        }
    XYDataset datasetXY = createDataset();


    JFreeChart chart3 = ChartFactory.createXYLineChart(
        main, //titulo
        xlab, // label x
        ylab, //label Y
        datasetXY, //Vector con puntos
        PlotOrientation.VERTICAL,
        true, true, false);


      ChartFrame f = new ChartFrame("PLOT", chart3); 
      f.pack();
      f.setVisible(true);
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return cont;
    }
    
    
    public XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series = new XYSeries("Y");
        for (Object object : VLinea) {
            series.add(Integer.parseInt(((ArrayList)object).get(0).toString()),Integer.parseInt(((ArrayList)object).get(1).toString()));
        }
        //Add series to dataset
        dataset.addSeries(series);
    
        return dataset;
    }
    
}
