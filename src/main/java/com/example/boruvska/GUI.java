package com.example.boruvska;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GUI extends Application {
    private Stage primaryStage;
    private Boruvskas b ;
    private ArrayList<Circle> nodes = new ArrayList<>();
    private int weight = 0;
    private int l = -1 ;
    private int p = -1 ;
    private Label sumWeight = new Label("0");
    static Color[] colors = {Color.GREEN, Color.BLUE, Color.RED , Color.YELLOW, Color.ORANGE, Color.PURPLE, Color.WHITE, Color.PINK, Color.BROWN};

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage ;
        draw();
    }

    public void drawMST(){
        if(p<0)
            p=0 ;
        if(l<0)
            l=0 ;
        if(p>= b.nodes.size())
            return ;
        if(l< b.cheapest.size()){
            for(int j=0 ; j<nodes.size() ; ++j){
                nodes.get(j).setFill(colors[b.sets[l][j]]);
            }
            Edge e = b.cheapest.get(l);
            if(e.u == b.nodes.get(p) || e.v == b.nodes.get(p)){
                e.line.setStroke(Color.GREEN);
                for(Edge edge : b.cheapest){
                    if(b.sets[l][edge.u.value] == b.sets[l][edge.v.value]){
                        edge.line.setStroke(colors[b.sets[l][edge.u.value]]);
                    }
                }
                calculateWeight(l);
                ++l ;
            }
        }
        ++p ;
        if(p==b.nodes.size()+1){
            resetMST();
        }
    }

    public void calculateWeight(int i){
        weight = 0 ;
        for(int k=0 ; k<=i ; ++k){
            weight+= b.cheapest.get(k).w;
        }
        sumWeight.setText(weight+ "");
    }

    public void resetMST(){
        for(Circle c : nodes){
            c.setFill(Color.RED);
        }
        for(Edge l : b.cheapest){
            l.line.setStroke(Color.BLACK);
        }
        p = l = -1 ;
        calculateWeight(l);
    }

    public void draw(){
        Group group = new Group();
        primaryStage.setTitle("Boruvska's");
        createGraph(group);
        b.boruvskasMST();
        Button next = new Button("NEXT STEP !!!");
        next.setOnAction(event -> drawMST());
        next.setLayoutX(1354);
        next.setLayoutY(0);
        Button reset = new Button("RESET MST!!!");
        reset.setOnAction(event -> resetMST());
        sumWeight.setLayoutX(0);
        sumWeight.setLayoutY(650);
        sumWeight.setFont(new Font("Arial", 50));
        sumWeight.setTextFill(Color.YELLOW);
        group.getChildren().addAll(next, reset, sumWeight);
        Scene scene = new Scene(group, 1440, 720 , Color.SKYBLUE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void createGraph(Group group){
        this.b = new Boruvskas(9);
        Node v0 = new Node(0);
        Node v1 = new Node(1);
        Node v2 = new Node(2);
        Node v3 = new Node(3);
        Node v4 = new Node(4);
        Node v5 = new Node(5);
        Node v6 = new Node(6);
        Node v7 = new Node(7);
        Node v8 = new Node(8);
        b.nodes.add(v0);
        b.nodes.add(v1);
        b.nodes.add(v2);
        b.nodes.add(v3);
        b.nodes.add(v4);
        b.nodes.add(v5);
        b.nodes.add(v6);
        b.nodes.add(v7);
        b.nodes.add(v8);
        b.addEdge(v0, v1, 4);
        b.addEdge(v0, v6, 7);
        b.addEdge(v1, v2, 4);
        b.addEdge(v1, v6, 11);
        b.addEdge(v1, v7, 20);
        b.addEdge(v2, v3, 6);
        b.addEdge(v2, v4, 2);
        b.addEdge(v3, v4, 10);
        b.addEdge(v3, v5, 4);
        b.addEdge(v4, v5, 15);
        b.addEdge(v4, v7, 6);
        b.addEdge(v4, v8, 5);
        b.addEdge(v5, v8, 12);
        b.addEdge(v6, v7, 7);
        b.addEdge(v7, v8, 3);

        for(Edge e: b.edges){
            Line line = new Line(e.u.x, e.u.y, e.v.x, e.v.y);
            line.setStrokeWidth(5.0f);
            line.setStroke(Color.BLACK);
            e.line = line ;
            int x = (e.u.x + e.v.x) / 2 ;
            int y = (e.u.y + e.v.y) / 2 ;
            Label weight = new Label(Integer.toString(e.w));
            weight.setLayoutX(x);
            weight.setLayoutY(y);
            weight.setFont(new Font("Arial", 35));
            weight.setTextFill(Color.YELLOW);
            group.getChildren().addAll(line, weight);
        }
        for(Node node : b.nodes){
            Circle circle = new Circle();
            circle.setCenterX(node.x);
            circle.setCenterY(node.y);
            circle.setRadius(40);
            circle.setFill(colors[node.set]);
            Label value = new Label("V"+node.value);
            value.setLayoutX(node.x-25);
            value.setLayoutY(node.y-22);
            value.setFont(new Font("Arial", 40));
            group.getChildren().addAll(circle , value);
            node.c = circle ;
            nodes.add(circle);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}