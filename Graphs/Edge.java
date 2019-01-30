package Graphs;

public class Edge {
    int w;
    int v;
    private double weight;
    public Edge(int v,int w,double weight){
        if(v<w) {
            this.v=v;
            this.w=w;
        }else{
            this.v=w;
            this.w=v;
        }
        this.weight=weight;
    }
    
    public int either(){
        return v;
    }
    
    public int other(int v){
        if (v==this.v) return this.w;
        else return this.v;
    }

    public double weight(){
        return weight;
    }

    public String toString(int v){
        String str="| "+v+" --"+(int)weight+"--> "+other(v);
        return str;
    }

    public String toString(){
        return v+" -> "+w;
    }
}
