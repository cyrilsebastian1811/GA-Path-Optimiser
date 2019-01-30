package Genetic;
import HelperFunctions.BinaryTree;
import HelperFunctions.Computation;
import HelperFunctions.Data;

public class Population {
    BinaryTree populas;
    int src;
    int desti;
    Data data;
    int size;

    Population(){
        data=Data.getinstance();
        this.src=data.getsource();
        this.desti=data.getdestination();
        this.populas=new BinaryTree();
    }

    public void InitialPopulation(int N){
        Computation temp;
        Individual ind;
        int count=0;
        while(populas.getSize()<N){
            temp=new Computation(src,desti);
            if(temp.getChromosome()!=null) {
                count+=1;
                Individual individual=new Individual(temp.getChromosome(),temp.getSize());
                individual.setFitness(data.getFiteval().FitnessFunction(individual,desti));
                System.out.print("Individual "+count+": "+temp+"\nFitness: "+individual.getFitness()+"\n");
//                System.out.println(individual.getFitness());
                populas.put(individual);
                size++;
            }
        }
    }

    public void put(Individual ind){
        populas.put(ind);
        size++;
    }

    public int getsize(){
        return size;
    }

    public Individual getmin(){
        size--;
        return populas.deletemin();
    }
}
