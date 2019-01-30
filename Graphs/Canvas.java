package Graphs;

import Genetic.App;
import HelperFunctions.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class Canvas extends JPanel implements MouseListener, Observer{
    private HashMap<Integer,Integer[]> nodes;
    private Dijkstra.Node stack;
    private HamiltonCycle.Node srcnode;
    ////////////////////
    Integer[] chromosome;
    ////////////////////
    private EdgeWeightedGraph G;
    private boolean mouseclicked;
    private Data data=Data.getinstance();

    Canvas(){
        addMouseListener(this);
    }

    public void reset(){
        this.G=data.getGraph();
        this.nodes=data.getcoordinates();
    }

    public void set(Dijkstra.Node stack,HamiltonCycle.Node srcnode){
        this.reset();
        this.stack=stack;
        this.srcnode=srcnode;
//        this.src=false;
//        this.desti=false;
    }

    public void setmouseclicked(boolean mouseclicked){
        this.mouseclicked=mouseclicked;
    }

    public void setblocked(int V){
        this.fill=new boolean[V];
    }

    public void paint(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        this.setBackground(Color.GRAY);
        if(nodes==null) return;
        for(Integer i:nodes.keySet()){
            g2d.setColor(Color.BLACK);
            int x1=nodes.get(i)[0];
            int y1=nodes.get(i)[1];
            if(fill!=null && fill[i]) continue;
            if(G.adj(i)==null) continue;
            for(Edge e: G.adj(i)){
                int w=e.other(i);
                int x2=nodes.get(w)[0];
                int y2=nodes.get(w)[1];
                g2d.setStroke(new BasicStroke(4));
                g2d.drawLine(x1,y1,x2,y2);
            }
            g2d.drawOval((x1-11),(y1-11),22,22);
        }

        g2d.setColor(Color.YELLOW);
        if(stack!=null){
            Dijkstra.Node temp=stack;
            int node1=temp.getver();
            temp=temp.next;
            int node2;
            int x1,y1,x2=0,y2=0;
            while(temp!=null){
                node2=temp.getver();
                temp=temp.next;
                x1=nodes.get(node1)[0];
                y1=nodes.get(node1)[1];
                x2=nodes.get(node2)[0];
                y2=nodes.get(node2)[1];
                g2d.drawLine(x1,y1,x2,y2);
                g2d.drawOval((x1-11),(y1-11),22,22);
                node1=node2;
            }
            g2d.drawOval((x2-11),(y2-11),22,22);
        }
        g2d.setColor(Color.YELLOW);
        if(srcnode!=null){
            int v=srcnode.ver;
            int x1,y1,x2=0,y2=0;
            while(srcnode!=null){
                x1=nodes.get(v)[0];
                y1=nodes.get(v)[1];
                if(srcnode.next==null) break;
                srcnode=srcnode.next;
                v=srcnode.ver;
                x2=nodes.get(v)[0];
                y2=nodes.get(v)[1];
                g2d.drawLine(x1,y1,x2,y2);
                g2d.drawOval((x1-11),(y1-11),22,22);
            }
            g2d.drawOval((x2-11),(y2-11),22,22);
        }
////////////////////////////////
        if(chromosome!=null){
            int v=srcdesti[0];
            int x1,y1,x2=0,y2=0;
            while(chromosome[v]!=v){
                x1=nodes.get(v)[0];
                y1=nodes.get(v)[1];
                v=chromosome[v];
                x2=nodes.get(v)[0];
                y2=nodes.get(v)[1];
                g2d.drawLine(x1,y1,x2,y2);
                g2d.drawOval((x1-11),(y1-11),22,22);
            }
            g2d.drawOval((x2-11),(y2-11),22,22);
        }
//////////////////////////////
        for(Integer i:nodes.keySet()){
            int x1=nodes.get(i)[0];
            int y1=nodes.get(i)[1];
            if(this.fill[i]==false) {
                g2d.setColor(Color.GRAY);
            }else{ g2d.setColor(Color.BLACK);}
            if(srcdesti[1]!=null && i.equals(srcdesti[1])) {
                g2d.setColor(Color.RED);
            }
            if(srcdesti[0]!=null && i.equals(srcdesti[0])){
                g2d.setColor(Color.GREEN);
            }
            g2d.fillOval((x1-9),(y1-9),18,18);
        }
    }

    boolean fill[];
    int option;
    Integer[] srcdesti=new Integer[2];

    public void setoption(int option){
        this.option=option;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(mouseclicked==false) return;
        Point p=e.getPoint();
        int x=p.x;
        int y=p.y;
        int xmod=x%50;
        int ymod=y%50;
        if(xmod!=0){
            if(xmod>25) x+=(50-xmod);
        }
        if(ymod!=0){
            if(ymod>25) y+=(50-ymod);
        }
        x=(x/50)-1;
        y=(y/50)-1;
        int columns=getSize().width/50-1;
        int rows=getSize().height/50-1;
        int node=(y*columns)+x;
        if(node<0 || node>=(columns*rows)) return;
        if(option==0){
            fill[node]=(!fill[node]);
//            if(fill[node] && !src && !desti) {
            if(fill[node]) {
                for(Edge ed: G.adj(node)){
                    int w=ed.other(node);
                    this.G.removeadjver(w,node);
                }
                this.G.delnode(node);
            }else{
                int nodex=node%columns;
                int nodey=node/columns;
                if(nodex>0){
                    if(!fill[node-1]) this.G.addEdge(new Edge((node-1),node,1));
                }
                if(nodex<(columns-1)){
                    if(!fill[node+1]) this.G.addEdge(new Edge((node+1),node,1));
                }
                if(nodey>0){
                    if(!fill[node-columns]) this.G.addEdge(new Edge((node-columns),node,1));
                }
                if(nodey<(rows-1)){
                    if(!fill[node+columns]) this.G.addEdge(new Edge((node+columns),node,1));
                }
            }
        }else if(option==1 && srcdesti[0]==null){
            srcdesti[0]=node;
            data.setsource(srcdesti[0]);
        }else if(option==2 && srcdesti[1]==null){
            srcdesti[1]=node;
            data.setdestination(srcdesti[1]);
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println("mouse released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("mouse exited");
    }

    @Override
    public void update(Observable o, Object arg) {
        chromosome=((App)o).getSolution();
        repaint();
    }
}
