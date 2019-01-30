package Genetic;

import HelperFunctions.Data;

public class CrossOver {
    Individual[] parents;
    int count=0;
    Data data;
    Individual child1;
    Individual child2;
    int N;
    CrossOver(){
        parents=new Individual[2];
        data=Data.getinstance();
        N=data.getGraph().V();
    }

    public void addparents (Individual ind) {
        assert count<2: "Parents already exists";
        parents[count%2]=ind;
        count++;
    }

    public void cross(){
        Integer[] chromosome1=parents[0].getChromosome();
        System.out.print("Parent1:");
        int ve=data.getsource();
        for(;chromosome1[ve]!=ve;ve=chromosome1[ve]){
            System.out.print(ve+"->");
        }
        System.out.println(ve);

        Integer[] chromosome2=parents[1].getChromosome();

        System.out.print("Parent2:");
        ve=data.getsource();
        for(;chromosome2[ve]!=ve;ve=chromosome2[ve]){
            System.out.print(ve+"->");
        }
        System.out.println(ve);

        Integer[] temp1=new Integer[N];
        Integer[] temp2=new Integer[N];
        Integer[] xyz;
        int i=data.getsource();
        xyz=chromosome1;
        temp1[i]=xyz[i];
        boolean change=false;
        while(true) {
            if(xyz[i]!=null && xyz[i]==i) break;
            if(change) {
                xyz=chromosome2;
                if(chromosome1[xyz[i]]!=null){
                    i=xyz[i];
                    temp1[i]=chromosome1[i];
                    change=false;
                    continue;
                }
            }else {
                xyz=chromosome1;
                if(chromosome2[xyz[i]]!=null){
                    i=xyz[i];
                    temp1[i]=chromosome2[i];
                    change=true;
                    continue;
                }
            }
            i=xyz[i];
            temp1[i]=xyz[i];
        }
        temp1[i]= i;
        i=data.getsource();
        xyz=chromosome2;
        temp2[i]=xyz[i];
        change=true;
        while(true) {
            if(xyz[i]!=null && xyz[i]==i) break;
            if(change) {
                xyz=chromosome2;
                if(chromosome1[xyz[i]]!=null){
                    i=xyz[i];
                    temp2[i]=chromosome1[i];
                    change=false;
                    continue;
                }
            }else {
                xyz=chromosome1;
                if(chromosome2[xyz[i]]!=null){
                    i=xyz[i];
                    temp2[i]=chromosome2[i];
                    change=true;
                    continue;
                }
            }
            i=xyz[i];
            temp2[i]=xyz[i];
        }
        temp2[i]= i;

        int ver=data.getsource();
        int size=0;
        Integer[] child=new Integer[N];
        System.out.print("Child1: "+ver+"->");
        for(;temp1[ver]!=ver;ver=temp1[ver]){
            child[ver]=temp1[ver];
            System.out.print(child[ver]+"->");
            size++;
        }
        child[ver]=ver;
        size++;
        child1=new Individual(child,size);
        child1.setFitness(data.getFiteval().FitnessFunction(child1,ver));

        ver=data.getsource();
        child=new Integer[N];
        size=0;
        System.out.print("\nChild2: "+ver+"->");
        for(;temp2[ver]!=ver;ver=temp2[ver]){
            child[ver]=temp2[ver];
            System.out.print(child[ver]+"->");
            size++;
        }
        System.out.println();
        child[ver]=ver;
        size++;
        child2=new Individual(child,size);
        child2.setFitness(data.getFiteval().FitnessFunction(child2,ver));
    }

    public Individual getchild1(){
        return child1;
    }
    public Individual getchild2(){
        return child2;
    }
}
