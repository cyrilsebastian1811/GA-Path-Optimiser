package HelperFunctions;

import Graphs.Edge;
import Graphs.EdgeWeightedGraph;
import java.util.Random;

public class Computation {
    int[] position1;
    int[] position2;
    boolean check[];
    Integer[] chromosome;
    int size=0;

    private int src;
    private int desti;
    private int previousnode;
    private int lastnodevis;

    EdgeWeightedGraph G;
    Data data;
    Random ran=new Random();

    public Computation(int src,int desti){
        data=Data.getinstance();
        this.G=data.getGraph();
        this.src=src;
        this.desti=desti;

        check=new boolean[G.V()];
        this.position1=new int[G.V()];
        this.position2=new int[G.V()];
        chromosome=new Integer[G.V()];

        fillrandom();
    }

    public void fillrandom(){
        if(src==desti) {
            System.out.println("****************************");
            chromosome[src]=desti;
            return;
        }
        int N=G.V();
        int way1[]=new int[N];
        way1[src]=src;
        previousnode=src;
        int way2[]=new int[N];
        way2[desti]=desti;
        lastnodevis=desti;

        for(int i=0;i<N;i++){
            position1[i]=previousnode;
            position2[(N-1)-i]=lastnodevis;
            check[lastnodevis]=true;

            int k=0;
            int randomno=ran.nextInt(G.adjsize(previousnode));
            for(Edge e:G.adj(previousnode)){
                if(randomno==k++){
                    int w=e.other(previousnode);
                    way1[previousnode]=w;
                    way1[w]=w;
                    previousnode=w;
                    break;
                }
            }

            k=0;
            randomno=ran.nextInt(G.adjsize(lastnodevis));
            for(Edge e:G.adj(lastnodevis)){
                if(randomno==k++){
                    int w=e.other(lastnodevis);
                    way2[lastnodevis]=w;
                    way2[w]=w;
                    lastnodevis=w;
                    break;
                }
            }

            if(previousnode==desti) {
                int ver=way1[src];
                chromosome[src]=ver;
//                System.out.println("from src:");
//                System.out.print(chromosome[src]+"->");
                size++;
                while(way1[ver]!=ver){
                    chromosome[ver]=way1[ver];
//                    System.out.print(chromosome[ver]+"->");
                    size++;
                    ver=way1[ver];
                }
                size++;
                chromosome[ver]=way1[ver];
                System.out.println("SRC: "+chromosome[data.getdestination()]);
                return;
            }


            if(lastnodevis==src) {
                int ver=desti;
                int index=N-1;
                int[] temp=new int[N];
                while(way2[ver]!=ver){
                    temp[index]=ver;
                    index--;
                    ver=way2[ver];
                }
                temp[index]=ver;
                chromosome[temp[index]]=temp[index+1];

//                System.out.println("from desti:");
//                System.out.print(chromosome[temp[index]]+"->");
                size++;
                index++;
                for(;index<(N-1);index++) {
                    size++;
                    chromosome[temp[index]]=temp[index+1];
//                    System.out.print(chromosome[temp[index]]+"->");
                }
                chromosome[temp[index]]=temp[index];
//                System.out.println();
                size++;
                System.out.println("DESTI: "+chromosome[data.getdestination()]);
                return;
            }
        }

        int intersection=0;
        int[] temp1=new int[N];
        int[] temp2=new int[N];
        int y=src;
        for(int i=0;i<N;i++) {
            temp1[y]=position1[i];
            temp1[position1[i]]=position1[i];
            y=position1[i];
            if(check[position1[i]]) {
                intersection=position1[i];
                break;
            }
        }
        size++;

//        System.out.println("from both:");
        for(int i=src;temp1[i]!=i;i=temp1[i]) {
            chromosome[i]=temp1[i];
//            System.out.print(chromosome[i]+"->");
            size++;
        }

        y=0;
        int start=-1;
        boolean proceed=false;
        for(int i=0;i<N;i++) {
            if(!proceed && position2[i]==intersection) {
                y=position2[i];
                start=y;
                proceed=true;
            }
            else if(proceed){
                temp2[y]=position2[i];
                temp2[position2[i]]=position2[i];
                y=position2[i];
            }
        }
        int i=start;
        if(start==-1) {
            chromosome=null;
            return;
        }

        for(;temp2[i]!=i;i=temp2[i]) {
            size++;
            chromosome[i]=temp2[i];
//            System.out.print(chromosome[i]+"->");
        }
        chromosome[i]=i;
//        System.out.println();
        System.out.println("BOTH: "+chromosome[data.getdestination()]);
    }

    public Integer[] getChromosome(){
        return this.chromosome;
    }

    public int getSize(){
        return size;
    }

    public String toString(){
        if(chromosome==null) return "";
        String str=""+data.getsource()+"->";
        for(int i=data.getsource();chromosome[i]!=i;i=chromosome[i]){
            str+=(chromosome[i])+"->";
        }
//        String str=""+chromosome[data.getdestination()];
        return str;
    }
}