package com.example.boruvska;

import javafx.scene.shape.Line;

public class Edge {
    public Node u;
    public Node v;
    public int w ;
    public String color ;
    public Line line ;

    public Edge(Node u, Node v, int w){
        this.v = v;
        this.u = u;
        this.w = w;
        this.color = "Red";
    }

    public String toString(){
        if(u==null || v== null){
            return "Is NULL";
        }
        return this.u.value +" to "+ this.v.value +" with "+ this.w;
    }

    public void insertMST(){
        this.color = "Green";
        this.u.color = "Green" ;
        this.v.color = "Green" ;
    }
}
