package HelperFunctions;

import Graphs.Canvas;
import Graphs.EdgeWeightedGraph;

import java.util.HashMap;

public class Data {
    private Integer src=null;
    private Integer desti=null;
    private HashMap<Integer,Integer[]> coordinates=null;
    private static Data instance=null;
    private int columns;
    private int rows;
    private EdgeWeightedGraph G=null;
    private FitnessEvaluator fiteval=null;
    private Canvas Canvaspanel;

    private Data(){
        System.out.println("Factory Class Created");
    }

    public void setGraph(EdgeWeightedGraph G){
        this.G=G;
        this.fiteval=new FitnessEvaluator(G);
    }
    public EdgeWeightedGraph getGraph(){
        return this.G;
    }


    public void setcoordinates(HashMap<Integer,Integer[]> coordinates){
        this.coordinates=coordinates;
    }
    public HashMap<Integer,Integer[]> getcoordinates(){
        return this.coordinates;
    }


    public void setsource(Integer src){
        this.src=src;
    }
    public Integer getsource(){
        return this.src;
    }


    public void setdestination(Integer desti){
        this.desti=desti;
    }
    public Integer getdestination(){
        return this.desti;
    }


    public void setcolumns(int columns){
        this.columns=columns;
    }
    public int getcolumns(){
        return this.columns;
    }


    public void setrows(int rows){
        this.rows=rows;
    }
    public int getrows(){
        return this.rows;
    }


    public void setCanvaspanel(Canvas Canvaspanel){
        this.Canvaspanel=Canvaspanel;
    }
    public Canvas getCanvaspanel(){return this.Canvaspanel;}


    public FitnessEvaluator getFiteval(){ return this.fiteval;}

    public static Data getinstance(){
        if(instance==null) instance=new Data();
        return instance;
    }
}
