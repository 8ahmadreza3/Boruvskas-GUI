package com.example.boruvska;
import java.util.ArrayList;

public class Node {
    public int value ;
    public String color;
    public int set ;
    public ArrayList<Edge> neighbors = new ArrayList<>();
    public int x ;
    public int y ;

    public Node(int value){
        this.value = value;
        this.color = "Red";
        this.set = value;
        int x = (int) (Math.random()*25);
        int y = (int) (Math.random()*13);
        this.y = y*50 +45 ;
        this.x = x*50 +50 ;
    }

    public String toString(){
        return value + " " + set ;
    }

    public Edge minNeighbor(Boruvskas b){
        Edge min = new Edge(null, null, Integer.MAX_VALUE);
        for(Edge n: this.neighbors){
            if(min.w >= n.w){
                min = n ;
            }
        }
        if(min.u == null){
            return min ;
        }
        this.neighbors.remove(min);
        if(min.v.set != min.u.set){
            int minSet = Math.min(min.v.set, min.u.set);
            for(Node node : b.nodes){
                if(node.set == min.v.set || node.set == min.u.set){
                    node.set = minSet ;
                }
            }
            return min ;
        }
        this.removeNeighbors();
        return new Edge(null, null, Integer.MAX_VALUE);
    }

    public void removeNeighbors(){
        for(Edge e : neighbors){
            if(e.v.set == e.u.set){
                neighbors.remove(e);
            }
        }
    }


    public Edge minNeighborSet(){
        Edge min = new Edge(null, null, Integer.MAX_VALUE);
        for(Edge n: this.neighbors){
            if(n.v.set != n.u.set){
                if(min.w >= n.w){
                    min = n ;
                }
            }
        }
        return min ;
    }
}
