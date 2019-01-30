package Graphs;

public class HamiltonCycle {
    EdgeWeightedGraph G;
    HamiltonCycle(EdgeWeightedGraph G){
        this.G=G;
    }

    public class Node{
        int ver;
        double distance;
        Node next;
        Node(int ver,Node next,double distance){
            this.ver=ver;
            this.next=next;
            if(next!=null) {
                this.distance=distance+next.distance;
            }
            else {
                this.distance=distance;
            }
        }
    }

    private double contains(Iterable<Edge> adj,int v,int src){
        for(Edge e: adj){
            int w=e.other(src);
            if(w==v) return e.weight();
        }
        return 0;
    }

    private class Marked{
        private int no;
        private boolean[] visited;
        Marked(){
            this.no=0;
            this.visited=new boolean[G.V()];
        }
        public void add(int src){
            visited[src]=true;
            no++;
        }
        public void remove(int src){
            visited[src]=false;
            no--;
        }

        public boolean get(int src){
            return visited[src];
        }
    }

    public Node hamiltonCycle(){
        return HamiltonCycle(0,new Marked(),null,0.0);
    }

    public Node HamiltonCycle(int src, Marked m, Node x, double distance){
        x=new Node(src,x,distance);
        m.add(src);
        if(m.no==G.V()) return x;
        double dist=Double.POSITIVE_INFINITY;
        Node path=null;

        for(Edge e:G.adj(src)){
            int v=e.other(src);

            if((!m.get(v))){
                Node temp=HamiltonCycle(v,m,x,e.weight());
                m.remove(v);
                if(temp==null) continue;

                if(src==0){
                    double y=contains(G.adj(src),temp.ver,src);
                    if(y!=0) {
                        if((temp.distance+y)<dist){
                            dist=temp.distance;
                            path=new Node(src,temp,y);
                        }
                    }
                }else{
                    if(temp.distance<dist) {
                        dist=temp.distance;
                        path=temp;
                    }
                }
            }
        }
        return path;
    }
}
