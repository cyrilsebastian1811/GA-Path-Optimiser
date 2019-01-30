package Graphs;
import HelperFunctions.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Display implements ActionListener {
    private EdgeWeightedGraph G;
    private Integer V=null;
    private Dijkstra di;
    private HamiltonCycle hami;
//    private Random ran=new Random();

    private JFrame frame;
    private JPanel northpanel;
    private Canvas Canvaspanel;

    private JButton start=new JButton("Start");

    private JComboBox fscroll;
    private JComboBox gscroll;
    private JComboBox scroll3;

    private Data data=Data.getinstance();


    Display(){ GUI(); }

    private void GUI(){
        frame=new JFrame("Graph Concepts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        Dimension d=new Dimension(1000,1000);
        frame.setSize(d);
        frame.setResizable(true);

        frame.add(getnorthpanel(),BorderLayout.NORTH);
        getcenterpanel();
        frame.add(this.Canvaspanel,BorderLayout.CENTER);
    }

    private JPanel getnorthpanel(){
        northpanel=new JPanel();
        JLabel functionality=new JLabel("Function");
        JLabel choice=new JLabel("Graph");
        JLabel src=new JLabel("S->D");

        this.start.addActionListener(this);

        String[] str1={"","Graphs.Dijkstra", "Graphs.HamiltonCycle","GA Path"};
        fscroll=new JComboBox<>(str1);
        fscroll.addActionListener(this);

//        String[] str2={"","graph1", "graph2"};
        String[] str2={"", "graph2"};
        gscroll=new JComboBox<>(str2);
        gscroll.addActionListener(this);

        String[] str3={"blocks","source","destination"};
        scroll3=new JComboBox<>(str3);
        scroll3.addActionListener(this);

        northpanel.add(start);
        northpanel.add(choice);
        northpanel.add(gscroll);
        northpanel.add(functionality);
        northpanel.add(fscroll);
        northpanel.add(src);
        northpanel.add(scroll3);

        northpanel.setBackground(Color.CYAN);
        return northpanel;
    }

    public void getcenterpanel(){
        this.Canvaspanel=new Canvas();
        data.setCanvaspanel(Canvaspanel);
    }

    int function=0;
    private HashMap<Integer,Integer[]> coordinates=new HashMap<>();
    private Dijkstra.Node stack;
    private HamiltonCycle.Node node;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==gscroll) {
            int index = gscroll.getSelectedIndex();
            if (index == 1) {
                Graph2();
                Canvaspanel.setmouseclicked(true);
            }
            Canvaspanel.set(stack,node);
        }else if(e.getSource()==fscroll){
            function=fscroll.getSelectedIndex();
        }else if(e.getSource()==scroll3){
            Canvaspanel.setoption(scroll3.getSelectedIndex());
        }else if(e.getSource()==start){
            if(function!=0 && G!=null){
                Thread thr=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(function==1 && data.getsource()!=null && data.getdestination()!=null){
                            di=new Dijkstra(G,data.getsource());
                            stack=di.path(data.getdestination(),data.getsource());
                            function=0;
                        }else if(function==2){
                            hami=new HamiltonCycle(G);
                            node=hami.hamiltonCycle();
                            function=0;
                        }
                        Canvaspanel.set(stack,node);
                    }
                });
                thr.start();
                if(function==3 && data.getsource()!=null && data.getdestination()!=null) {
                    System.out.println("Blah");
                    Genetic.App app=new Genetic.App();
                }
            }else{
                this.stack=null;
                this.node=null;
//                this.G=null;
//                this.V=null;

                data.setcoordinates(null);
                data.setGraph(null);
                data.setsource(null);
                data.setdestination(null);
                Canvaspanel.set(stack,node);

                Canvaspanel.srcdesti[0]=null;
                Canvaspanel.srcdesti[1]=null;
                fscroll.setSelectedIndex(0);
                gscroll.setSelectedIndex(0);
                scroll3.setSelectedIndex(0);
                function=0;
            }
        }
        Canvaspanel.repaint();
    }

    public void columnsandrows(){
        Dimension size=Canvaspanel.getSize();
        int columns=(size.width/50)-1;
        int rows=(size.height/50)-1;
        data.setcolumns(columns);
        data.setrows(rows);
    }

    private void Graph2(){
        coordinates=new HashMap<>();
        columnsandrows();
        int rows=data.getrows();
        int columns=data.getcolumns();
        this.V=columns*rows;
        if(this.G==null) this.G=new EdgeWeightedGraph(V);
        Canvaspanel.setblocked(this.V);
        for (int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                int v=(i*columns)+j;
                int xcor=(j*50)+50;
                int ycor=(i*50)+50;
                Integer[] point={xcor,ycor};
                this.coordinates.put(v,point);
                if((j-1)>=0){
                    G.addEdge(new Edge(v,(v-1),1));
                }
                if((i-1)>=0){
                    G.addEdge(new Edge(v,(v-columns),1));
                }
                if((j+1)<columns){
                    G.addEdge(new Edge(v,(v+1),1));
                }
                if((i+1)<rows){
                    G.addEdge(new Edge(v,(v+columns),1));
                }
            }
        }
        data.setGraph(this.G);
        data.setcoordinates(this.coordinates);
    }

//    private void createnodes(int v){
//        if(this.coordinates.get(v)==null){
//            Dimension size=Canvaspanel.getSize();
//            int x=ran.nextInt((int)(size.width*0.8)-30);
//            int y=ran.nextInt((int)(size.height*0.8)-30);
//            Integer[] temp={x,y};
//            this.coordinates.put(v,temp);
//        }
//    }

//    private void Graph1(){
//        this.V=8;
//        Canvaspanel.setblocked(this.V);
//        if(G==null) G=new EdgeWeightedGraph(V);
//        G.addEdge(new Edge(0,1,5));
//        G.addEdge(new Edge(0,7,8));
//        G.addEdge(new Edge(0,4,9));
//        G.addEdge(new Edge(1,3,15));
//        G.addEdge(new Edge(1,2,12));
//        G.addEdge(new Edge(1,7,4));
//        G.addEdge(new Edge(7,2,7));
//        G.addEdge(new Edge(7,5,6));
//        G.addEdge(new Edge(7,4,5));
//        G.addEdge(new Edge(4,5,4));
//        G.addEdge(new Edge(4,6,20));
//
//        G.addEdge(new Edge(3,6,9));
//        G.addEdge(new Edge(3,2,3));
//        G.addEdge(new Edge(2,6,11));
//        G.addEdge(new Edge(2,5,1));
//        G.addEdge(new Edge(5,6,13));
//
//        createnodes(0);
//        createnodes(1);
//        createnodes(2);
//        createnodes(3);
//        createnodes(4);
//        createnodes(5);
//        createnodes(6);
//        createnodes(7);
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Display();
            }
        });

    }
}
