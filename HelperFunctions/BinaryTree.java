package HelperFunctions;

import Genetic.Individual;

public class BinaryTree {
    private Node root;
    private int size;
    public BinaryTree(){
        this.size=0;
    }

    private class Node{
        Individual individual;
        Node left;
        Node right;
        Node(Individual individual,Node left,Node right){
            this.individual=individual;
            this.left=left;
            this.right=right;
        }
    }


    public void put(Individual individual){
        Node temp=root;
        size++;
        if(size>10) return;
        if(root==null) {
            root=new Node(individual,null,null);
            return;
        }
        while(temp!=null){
            double cmp=individual.getFitness()-temp.individual.getFitness();
            if(cmp<=0.0){
                if(temp.left!=null){
                    Node x=new Node(individual,temp.left,temp);
                    temp.left.right=x;
                    temp.left=x;
                    return;
                }else{
                    Node x=new Node(individual,null,temp);
                    root=x;
                    temp.left=x;
                    return;
                }
            }else{
                if(temp.right!=null){
                    temp=temp.right;
                }else{
                    Node x=new Node(individual,temp,null);
                    temp.right=x;
                    return;
                }
            }
        }
    }

    public String toString(){
        String str="";
        Node temp=root;
        while(temp!=null) {
            str += temp.individual.getFitness() + ", ";
            temp = temp.right;
        }
        return str;
    }

//    private Node put(Node x,Individual individual){
//        if(x==null) {
//            this.size++;
//            return new Node(individual,null,null);
//        }
//        Double cmp=individual.getFitness()-x.individual.getFitness();
//        if(cmp<0) x.left=put(x.left,individual);
//        else if(cmp>=0) x.right=put(x.right,individual);
//        return x;
//    }

//    private Node deletedmin;

    public Individual deletemin(){
        System.out.println("items in the list: "+" | "+this);
        if(root!=null){
            Individual ind=root.individual;
            root=root.right;
            size--;
            System.out.println("deleted item fitness"+" | "+ind.getFitness()+"\n");
            return ind;
        }
        return null;
    }


//    private Node deletemin(Node x){
//        if(x.left==null) {
//            deletedmin=x;
//            return x.right;
//        }
//        x.left=deletemin(x.left);
//        return x;
//    }



    public int getSize(){
        return size;
    }
}
