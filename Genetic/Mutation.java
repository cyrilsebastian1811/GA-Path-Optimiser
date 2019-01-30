package Genetic;

import HelperFunctions.Computation;
import HelperFunctions.Data;
import java.util.Random;

public class Mutation {
    Random ran=new Random();
    Data data=Data.getinstance();


    public Individual mutate(Individual specimen,int N){
        System.out.println("In mutation***********************************************************");
        System.out.print("Playing with: ");

        int p1=ran.nextInt(specimen.getSize());
        int p2=ran.nextInt(specimen.getSize()-p1)+p1;
        Integer[] chromosome1=specimen.getChromosome();

        System.out.print(data.getsource()+"->");
        for(int ver=data.getsource();chromosome1[ver]!=ver;ver=chromosome1[ver]){
            System.out.print(chromosome1[ver]+"->");
        }
        System.out.println();
        System.out.println("p1: "+p1+", p2: "+p2);

        int count=0;
        int sub1=0;
        int sub2=0;
        int i=data.getsource();
        for(;chromosome1[i]!=i;i=chromosome1[i]){
            if(count==p1) sub1=i;
            if(count==p2) {
                sub2=i;
                break;
            }
            count++;
        }
        if(sub2==0) sub2=data.getdestination();
//        if(count<p2) sub2=i;
        Computation temp;
        while(true){
            System.out.println("source: "+sub1+", destination: "+sub2);
            temp=new Computation(sub1,sub2);
            if(temp.getChromosome()!=null) break;
        }

        Integer[] newspeci=new Integer[N];
        Integer[] chromosome3=new Integer[N];
        Integer[] chromosome2=temp.getChromosome();

        int ver=data.getsource();
        for(int j=0;j<=specimen.getSize();j++){
            newspeci[ver]=chromosome1[ver];
            if(j==p1){
                for(int k=sub1;chromosome2[k]!=k;k=chromosome2[k]){
                    newspeci[k]=chromosome2[k];
                }
            }
            ver=chromosome1[ver];
        }
        int size=0;
        int k=data.getsource();
        for(;newspeci[k]!=k;k=newspeci[k]){
            chromosome3[k]=newspeci[k];
            System.out.print(chromosome3[k]+"->");
            size++;
        }
        System.out.println();
        ////////////////////////////////
        chromosome3[k]=k;
        Individual child=new Individual(chromosome3,size);
        child.setFitness(data.getFiteval().FitnessFunction(child,k));
        System.out.println("*********************************************************");
        return child;
    }
}
