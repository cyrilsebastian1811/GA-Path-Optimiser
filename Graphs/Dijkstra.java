package Graphs;

public class Dijkstra {
    public double[] distTo;
    public Edge edgeTo[];

    public Dijkstra(EdgeWeightedGraph G,int s){
        int V=G.V();
        edgeTo=new Edge[V];
        distTo=new double[V];
        for(int v=0;v<V;v++){
            distTo[v]=Double.POSITIVE_INFINITY;
        }
        QueueS<Integer> q=new QueueS<Integer>();
        q.enqueue(s);
        distTo[s]=0.0;
        while(!q.isEmpty()){
            int v=q.dequeue();
            for(Edge e: G.adj(v)){
                int w=e.other(v);
                if(distTo[w]>distTo[v]+e.weight()){
                    distTo[w]=distTo[v]+e.weight();
                    edgeTo[w]=e;
                    q.enqueue(w);
                }
            }
        }
    }

    public class Node{
        int ver;
        Node next;
        Node(int ver, Node next){
            this.ver=ver;
            this.next=next;
        }
        public int getver(){
            return ver;
        }
    }

//    public Stack<Integer> path(int v, int s) {
//        Stack<Integer> path=new Stack<Integer>();
//        for(int w=v;w!=s;w=edgeTo[w].other(w)) {
//            path.push(w);
//        }
//        path.push(s);
//        return path;
//    }

    public Node path(int v, int s) {
//        Stack<Integer> path=new Stack<Integer>();
        Node path=null;
        if(edgeTo[v]==null) return null;
        for(int w=v;w!=s;w=edgeTo[w].other(w)) {
//            path.push(w);
            path=new Node(w,path);
        }
//        path.push(s);
        path=new Node(s,path);
        return path;
    }

}
