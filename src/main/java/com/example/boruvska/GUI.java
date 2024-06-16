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
    private Boruvskas b = new Boruvskas();
    private ArrayList<Circle> nodes = new ArrayList<>();
    private int weight = 0;
    private int l = 0 ;
    private int p = 0 ;
    private Label sumWeight = new Label("0");
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage ;
        draw();
    }

    public void drawMST(){
        if(p<0)
            p=0 ;
        if(l<0){
            l=0 ;
            weight=0;
        }
        if(p>= b.nodes.size())
            p-- ;
        if(l>= b.cheapest.size()){
            weight-= b.cheapest.get(l-1).w ;;
            l-- ;
        }
        Edge e = b.cheapest.get(l);
        if(e.u == b.nodes.get(p) || e.v == b.nodes.get(p)){
            b.cheapest.get(l).line.setStroke(Color.GREEN);
            this.weight+= b.cheapest.get(l).w ;
            sumWeight.setText(weight+ "");
            System.out.println(weight);
            ++l ;
        }
        nodes.get(p).setFill(Color.GREEN);
        ++p ;
    }

    public void backMST(){
        if(--p<0)
            p=0 ;
        if(--l<0){
            l=0 ;
            weight=0 ;
        }
        if(p>= b.nodes.size())
            p-- ;
        if(l>= b.cheapest.size()){
            weight+= b.cheapest.get(l).w ;
            l-- ;
        }
        Edge e = b.cheapest.get(l);
        if(e.u == b.nodes.get(p) || e.v == b.nodes.get(p)){
            b.cheapest.get(l).line.setStroke(Color.BLACK);
            this.weight-= b.cheapest.get(l+1).w ;
            System.out.println(weight);
            sumWeight.setText(weight+ "");
        }
        nodes.get(p).setFill(Color.RED);
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
        Button prev = new Button("PREV STEP !!!");
        prev.setOnAction(event -> backMST());
        sumWeight.setLayoutX(0);
        sumWeight.setLayoutY(650);
        sumWeight.setFont(new Font("Arial", 50));
        sumWeight.setTextFill(Color.YELLOW);
        group.getChildren().addAll(next, prev, sumWeight);
        Scene scene = new Scene(group, 1440, 720 , Color.SKYBLUE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void createGraph(Group group){
        Node v0 = new Node(0);
        Node v1 = new Node(1);
        Node v2 = new Node(2);
        Node v3 = new Node(3);
        Node v4 = new Node(4);
        Node v5 = new Node(5);
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
            circle.setFill(Color.RED);
            Label value = new Label("V"+node.value);
            value.setLayoutX(node.x-25);
            value.setLayoutY(node.y-22);
            value.setFont(new Font("Arial", 40));
            group.getChildren().addAll(circle , value);
            nodes.add(circle);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}