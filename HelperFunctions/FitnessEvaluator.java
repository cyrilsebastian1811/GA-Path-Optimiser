package HelperFunctions;

import Genetic.Individual;
import Graphs.Edge;
import Graphs.EdgeWeightedGraph;

public class FitnessEvaluator {
    EdgeWeightedGraph G;
    FitnessEvaluator(EdgeWeightedGraph G){
        this.G=G;
//        this.destination=destination;
    }

    public Double FitnessFunction(Individual ind,int destination){
        Double distance=1.0;
        Integer[] chromosome=ind.getChromosome();
        int n1=Data.getinstance().getsource();
        int n2;
        int i=1;
        if(chromosome[destination]!=destination) return (double)(G.V()-ind.getSize());
        while(i<ind.getSize()){
            n2=chromosome[n1];
            if(n1<n2){
                for(Edge e: G.adj(n1)){
                    if(e.other(n1)==n2) {
                        distance+=e.weight();
                        break;
                    }
                }
            }else {
                for(Edge e: G.adj(n2)){
                    if(e.other(n2)==n1) {
                        distance+=e.weight();
                        break;
                    }
                }
            }
            n1=n2;
            i++;
        }
        if(distance==0.0) return 0.0;
        return distance;
    }
}

