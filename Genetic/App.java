package Genetic;

import HelperFunctions.Data;

import java.util.Observable;
public class App extends Observable implements Runnable{
//public class App extends Observable{
    Data data;
    CrossOver crosser;
    Mutation mutator;
    int N;
    Integer[] chromosome;
    Thread thr;
    public App(){
        data=Data.getinstance();
        addObserver(data.getCanvaspanel());
        crosser=new CrossOver();
        mutator=new Mutation();
        N=data.getGraph().V();
        thr=new Thread(this);
        thr.start();
//        run();
    }
    @Override
    public void run() {
        data=Data.getinstance();
        Population popo=new Population();
        Population temppopo;
        System.out.println("Source: "+data.getsource()+", Destination: "+data.getdestination());

        popo.InitialPopulation(10);
        System.out.println(popo.populas);
        int genspassed=1;
        Individual ind;
//        System.out.println("Im in App");
        while(genspassed<10){
            System.out.println("Generation: "+genspassed);
            temppopo=new Population();
            System.out.println("----------------------------");
            while(temppopo.getsize()<10) {
//                Individual ind1 = popo.getmin();
//                Individual ind2 = popo.getmin();
                if (Math.random() < 0.7) {
                    Individual ind1 = popo.getmin();
                    Individual ind2 = popo.getmin();
                    crosser.addparents(ind1);
                    crosser.addparents(ind2);
                    crosser.cross();

                    ind = crosser.getchild1();
                    if (Math.random() < 0.1) {
                        temppopo.put(mutator.mutate(ind, N));
                    }
                    temppopo.put(ind);

                    ind = crosser.getchild2();
                    if (Math.random() < 0.1) {
                        temppopo.put(mutator.mutate(ind, N));
                    }
                    temppopo.put(ind);
//                    if(temppopo.getsize()<10){
//                        temppopo.put(ind1);
//                        temppopo.put(ind2);
//                    }
                    temppopo.put(ind1);
                    temppopo.put(ind2);
                }
            }
            popo=temppopo;
//            ind=popo.getmin();
//            chromosome=ind.getChromosome();
//            popo.put(ind);
//            setChanged();
//            notifyObservers();
            genspassed++;
        }
        ind=popo.getmin();
        chromosome=ind.getChromosome();
        setChanged();
        notifyObservers();
    }

    public Integer[] getSolution(){
        return chromosome;
    }
}
