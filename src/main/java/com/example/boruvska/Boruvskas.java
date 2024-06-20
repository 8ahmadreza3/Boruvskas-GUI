package com.example.boruvska;

import java.util.*;

public class Boruvskas {
    public ArrayList<Node> nodes = new ArrayList<>();
    public ArrayList<Edge> cheapest = new ArrayList<>() ;
    public ArrayList<Edge> edges = new ArrayList<>();
    public int weight = 0 ;
    public int[][] sets ;
    public int n = 0 ;

    public Boruvskas( int nodes){
        this.sets = new int[2*nodes][nodes];
    }

    public int calculateWeight(){
        for(Edge e: this.cheapest){
            this.weight += e.w ;
        }
        return this.weight;
    }

    public void addCheapest(Edge e){
        int j = 0 ;
        for(Node node : nodes){
            sets[n][j] = node.set ;
            j++ ;
        }
        ++n ;
        cheapest.add(e) ;
    }

    public void boruvskasMST(){
        for(Node node: this.nodes){
            Edge e = node.minNeighbor(this) ;
            if(e.v != null || e.u != null){
                e.insertMST() ;
                addCheapest(e);
            }
        }
        int n = nodes.size();
        for (int i=0 ; i<n ; ++i){
            Edge min = new Edge(null, null, Integer.MAX_VALUE);
            for(Node node : nodes){
                if(node.set == 0){
                    Edge e = node.minNeighborSet();
                    if(min.w > e.w){
                        min = e ;
                    }
                }
            }
            if(min.v != null || min.u != null){
                insertEdge(min);
            }
        }
        System.out.println(this.calculateWeight());
    }

    public void addEdge(Node u, Node v, int w){
        Edge e = new Edge(u, v, w);
        edges.add(e);
        v.neighbors.add(e);
        u.neighbors.add(e);
    }

    public void insertEdge(Edge e){
        e.color = "Green" ;
        int minSet = Math.min(e.u.set, e.v.set);
        int setU = e.u.set ,
                setV = e.v.set ;
        for(Node node : this.nodes){
            if(node.set == setU || node.set == setV){
                node.set = minSet ;
            }
        }
        e.v.neighbors.remove(e);
        e.u.neighbors.remove(e);
        addCheapest(e);
    }

    public static void main(String[] args){
        Node v0 = new Node(0);
        Node v1 = new Node(1);
        Node v2 = new Node(2);
        Node v3 = new Node(3);
        Node v4 = new Node(4);
        Node v5 = new Node(5);
        Boruvskas b = new Boruvskas(6);
        b.nodes.add(v0);
        b.nodes.add(v1);
        b.nodes.add(v2);
        b.nodes.add(v3);
        b.nodes.add(v4);
        b.nodes.add(v5);
        b.addEdge(v0, v1, 4);
        b.addEdge(v0, v2, 2);
        b.addEdge(v0, v3, 1);
        b.addEdge(v1, v4, 3);
        b.addEdge(v1, v5, 2);
        b.addEdge(v2, v3, 7);
        b.addEdge(v3, v4, 1);
        b.addEdge(v5, v4, 3);
        b.boruvskasMST();
        System.out.println(b.calculateWeight());
        System.out.println(Arrays.toString(b.cheapest.toArray()));
    }
}
