package Genetic;

public class Individual {
    private Integer[] chromosome;
    private Double fitness;
    private int size;

    Individual(Integer[] chromosome,int size){
        this.chromosome=chromosome;
        this.size=size;
    }

//    public void setGene(Integer[] chromosome){
//        this.chromosome=chromosome;
//    }


    public Integer[] getChromosome(){
        return this.chromosome;
    }

    public int getSize(){
        return this.size;
    }

    public void setFitness(Double fitness){
        this.fitness=fitness;
    }

    public Double getFitness(){
        return this.fitness;
    }
}
