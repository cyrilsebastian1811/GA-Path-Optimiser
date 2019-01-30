package Graphs;

import java.util.HashSet;

public class EdgeWeightedGraph {
    private int V;
    private HashSet<Edge>[] adj;

    EdgeWeightedGraph(int V){
        this.V=V;
        adj=new HashSet[V];
        for(int i=0;i<V;i++){
            adj[i]=new HashSet<Edge>();
        }
    }

    public void addEdge(Edge e){
        int v=e.either();
        int w=e.other(v);
        if(!contains(adj[v],v,w)) {
            if(adj[v]==null) { adj[v]=new HashSet<Edge>(); }
            adj[v].add(e);
        }
        if(!contains(adj[w],w,v)) {
            if(adj[w]==null) { adj[w]=new HashSet<Edge>(); }
            adj[w].add(e);
        }
    }

    public boolean contains(HashSet<Edge> adj,int v,int w){
        if(adj==null) { return false; }
        for(Edge e: adj){
            int tempw=e.other(v);
            if(w==tempw) return true;
        }
        return false;
    }

//    public synchronized void removever(int v){
//        adj[v]=null;
//    }

    public void  removeadjver(int v,int w){
        HashSet<Edge> tempadj=new HashSet<Edge>();
        for(Edge e: adj[v]){
            int k=e.other(v);
            if(k!=w){
                tempadj.add(e);
            }
        }
        adj[v]=tempadj;

        tempadj=new HashSet<Edge>();
        for(Edge e: adj[w]){
            int k=e.other(w);
            if(k!=v){
                tempadj.add(e);
            }
        }
        adj[w]=tempadj;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public void delnode(int node){
        adj[node]=null;
    }

    public int adjsize(int v){
        return adj[v].size();
    }

//    public int size(int v){
//        return adj[v].size();
//    }

    public int V(){
        return V;
    }
}
